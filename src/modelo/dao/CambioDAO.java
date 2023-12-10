package modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import modelo.ConectorBaseDatos;
import modelo.pojo.Cambio;
import utilidades.Utilidades;

public class CambioDAO {

    public static HashMap<String, Object> consultarCambios() {
        HashMap<String, Object> respuesta = new HashMap<>();
        respuesta.put("error", true);

        ArrayList<Cambio> cambios = new ArrayList<Cambio>();
        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {
            try {
                String consulta = "SELECT c.idCambio, c.titulo, c.descripcion, " +
                        "fechaInicio, ta.tipo FROM cambio c INNER JOIN " + 
                        "tipoactividad ta ON ta.idTipoActividad = c.idTipoActividad " + 
                        "INNER JOIN" + 
                        " solicituddecambio sc ON sc.idSolicitudDeCambio = " + 
                        "c.idSolicitudDeCambio INNER JOIN" + 
                        " proyecto p ON sc.idProyecto = p.idProyecto WHERE" + 
                        " p.idProyecto = 1 " + 
                        "ORDER BY titulo ASC;";

                PreparedStatement sentencia = conexion.prepareStatement(consulta);
                ResultSet resultadoConsulta = sentencia.executeQuery();

                while (resultadoConsulta.next()) {
                    Cambio cambio = new Cambio();
                    cambio.setIdCambio(resultadoConsulta.getInt("idCambio"));
                    cambio.setTitulo(resultadoConsulta.getString("titulo"));
                    cambio.setDescripcion(resultadoConsulta.getString("descripcion"));
                    cambio.setFechaInicio(resultadoConsulta.getString("fechaInicio"));
                    cambio.setTipoActividad(resultadoConsulta.getString("tipo"));

                    cambios.add(cambio);
                }

                respuesta.put("error", false);
                respuesta.put("cambios", cambios);

            } catch (SQLException se) {
                respuesta.put("mensaje", "Error: " + se.getMessage());
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }
        } else {
            respuesta.put("mensaje", "No se pudo conectar a la base de datos, inténtelo más tarde");
        }

        return respuesta;
    }

    public static HashMap<String, Object> consultarCambiosProyecto(Integer idProyecto) {

        HashMap<String, Object> respuesta = new HashMap<String, Object>();

        respuesta.put("error", true);

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "SELECT c.idCambio, c.titulo, c.descripcion, " +
                        "c.esfuerzoMinutos, c.idTipoActividad, c.idEstadoCambio, " +
                        "c.fechaInicio, c.idProyecto AS i, tc.tipo, ec.estado " +
                        "FROM cambio c INNER JOIN tipoactividad tc " +
                        "ON c.idTipoActividad = tc.idTipoActividad " +
                        "INNER JOIN estadocambio ec " +
                        "ON c.idEstadoCambio = ec.idEstadoCambio " +
                        "WHERE c.idProyecto = ? ORDER BY c.fechaInicio DESC";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);

                sentencia.setInt(1, idProyecto);

                ResultSet resultadoConsulta = sentencia.executeQuery();

                ArrayList<Cambio> cambios = new ArrayList<Cambio>();

                while (resultadoConsulta.next()) {

                    Cambio cambio = new Cambio();
                    cambio.setIdCambio(resultadoConsulta.getInt("idCambio"));
                    cambio.setTitulo(resultadoConsulta.getString("titulo"));
                    cambio.setDescripcion(resultadoConsulta.getString("descripcion"));
                    cambio.setEsfuerzoMinutos(resultadoConsulta.getInt("esfuerzoMinutos"));
                    cambio.setIdTipoActividad(resultadoConsulta.getInt("idTipoActividad"));
                    cambio.setIdEstadoCambio(resultadoConsulta.getInt("idEstadoCambio"));
                    cambio.setFechaInicio(resultadoConsulta.getString("fechaInicio"));
                    cambio.setIdProyecto(resultadoConsulta.getInt("i"));
                    cambio.setTipoActividad(resultadoConsulta.getString("tipo"));
                    cambio.setEstadoCambio(resultadoConsulta.getString("estado"));

                    cambios.add(cambio);

                }

                respuesta.put("error", false);
                respuesta.put("cambios", cambios);

            } catch (SQLException se) {
                respuesta.put("mensaje", "Error: " + se.getMessage());
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }

        } else {
            respuesta.put("mensaje", "No se pudo conectar a la base de datos, inténtelo más tarde");
        }

        return respuesta;

    }

    public static HashMap<String, Object> registrarCambio(Cambio cambio) throws SQLException {

        HashMap<String, Object> respuesta = new LinkedHashMap<String, Object>();

        respuesta.put("error", true);

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        String fechaServidor = Utilidades.obtenerFechaServidor();

        Date fechaServidorDate = Date.valueOf(fechaServidor);
        Date fechaInicioDate = Date.valueOf(cambio.getFechaInicio());

        if (fechaInicioDate.before(fechaServidorDate)) {
            throw new SQLException(
                    "Error en la base de datos: La fecha de inicio no puede ser menor a la fecha actual");
        }

        if (conexion != null) {

            try {

                String consulta = "INSERT INTO cambio (idSolicitudDeCambio, titulo, descripcion, esfuerzoMinutos, idTipoActividad, idEstadoCambio, fechaInicio, idProyecto) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);

                sentencia.setInt(1, cambio.getIdSolicitud());
                sentencia.setString(2, cambio.getTitulo());
                sentencia.setString(3, cambio.getDescripcion());
                sentencia.setInt(4, cambio.getEsfuerzoMinutos());
                sentencia.setInt(5, cambio.getIdTipoActividad());
                sentencia.setInt(6, cambio.getIdEstadoCambio());
                sentencia.setString(7, cambio.getFechaInicio());
                sentencia.setInt(8, cambio.getIdProyecto());

                int resultado = sentencia.executeUpdate();

                if (resultado > 0) {

                    respuesta.put("error", false);
                    respuesta.put("mensaje", "El cambio se registró correctamente");

                } else {
                    respuesta.put("mensaje", "No se pudo registrar el cambio");
                }

            } catch (SQLException se) {
                respuesta.put("mensaje", "Error: " + se.getMessage());
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }

            conexion = ConectorBaseDatos.obtenerConexion();

            try {
                String consulta = "INSERT INTO estudiante_cambio (idEstudiante, idCambio) VALUES (?, ?)";

                PreparedStatement sentencia = conexion.prepareStatement(consulta);

                sentencia.setInt(1, cambio.getIdEstudiante());
                sentencia.setInt(2, cambio.getIdCambio());

                int resultado = sentencia.executeUpdate();

                if (resultado > 0) {

                    respuesta.put("error", false);
                    respuesta.put("mensaje", "El cambio se registró correctamente");

                } else {
                    respuesta.put("mensaje", "No se pudo registrar el cambio");
                }

            } catch (SQLException e) {
                respuesta.put("mensaje", "Error: " + e.getMessage());
            }

        } else {
            respuesta.put("mensaje", "No se pudo conectar a la base de datos, inténtelo más tarde");
        }

        return respuesta;
    }

    public static HashMap<String, Object> consultarEstados() {
        HashMap<String, Object> respuesta = new LinkedHashMap<String, Object>();

        respuesta.put("error", true);

        Connection conexionBD = ConectorBaseDatos.obtenerConexion();

        if (conexionBD != null) {
            try {

                String consulta = "SELECT estado FROM EstadoCambio";
                PreparedStatement sentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = sentencia.executeQuery();
                ArrayList<String> estados = new ArrayList<>();

                while (resultadoConsulta.next()) {

                    String estado = resultadoConsulta.getString("estado");

                    estados.add(estado);

                }

                respuesta.put("error", false);
                respuesta.put("estados", estados);

            } catch (SQLException se) {
                respuesta.put("mensaje", "Error: " + se.getMessage());
            } finally {
                ConectorBaseDatos.cerrarConexion(conexionBD);
            }
        } else {
            respuesta.put("mensaje", "No se pudo conectar a la base de datos, inténtelo más tarde");
        }

        return respuesta;
    }

    public static HashMap<String, Object> consultarTiposActividades() {

        HashMap<String, Object> respuesta = new LinkedHashMap<>();

        respuesta.put("error", true);

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "SELECT tipo FROM TipoActividad;";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);
                ResultSet resultadoConsulta = sentencia.executeQuery();
                ArrayList<String> tiposActividades = new ArrayList<>();

                respuesta.put("error", false);

                while (resultadoConsulta.next()) {
                    tiposActividades.add(resultadoConsulta.getString("tipo"));
                }

                respuesta.put("error", false);
                respuesta.put("tiposActividades", tiposActividades);

            } catch (SQLException se) {
                respuesta.put("mensaje", "Error: " + se.getMessage());
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }

        } else {
            respuesta.put("mensaje", "No se pudo conectar a la base de datos, inténtelo más tarde");
        }

        return respuesta;
    }

    public static HashMap<String, Object> finalizarCambio(int idCambio, String fechaFin) {

        HashMap<String, Object> respuesta = new LinkedHashMap<>();

        respuesta.put("error", true);

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "UPDATE cambio SET idEstadoCambio = 1, fechaFin= ? WHERE idCambio = ?;";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);

                sentencia.setString(1, fechaFin);
                sentencia.setInt(2, idCambio);

                int resultado = sentencia.executeUpdate();

                if (resultado > 0) {

                    respuesta.put("error", false);
                    respuesta.put("mensaje", "El cambio se finalizó correctamente");

                } else {
                    respuesta.put("mensaje", "No se pudo finalizar el cambio");
                }

            } catch (SQLException se) {
                respuesta.put("mensaje", "Error: " + se.getMessage());
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }

        } else {
            respuesta.put("mensaje", "No se pudo conectar a la base de datos, inténtelo más tarde");
        }

        return respuesta;

    }
}
