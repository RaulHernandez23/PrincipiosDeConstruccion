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
    public static ArrayList<Cambio> consultarCambios() {
        ArrayList<Cambio> cambios = new ArrayList<Cambio>();

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "SELECT * FROM cambio ORDER BY Nombre ASC";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);

                ResultSet resultadoConsulta = sentencia.executeQuery();

                while (resultadoConsulta.next()) {

                    Cambio cambio = new Cambio();
                    cambio.setIdCambio(resultadoConsulta.getInt("IdCambio"));
                    cambio.setTitulo(resultadoConsulta.getString("Nombre"));
                    cambio.setDescripcion(resultadoConsulta.getString("Descripcion"));
                    cambio.setIdEstadoCambio(resultadoConsulta.getInt("IdEstadoCambio"));
                    cambio.setIdSolicitud(resultadoConsulta.getInt("IdSolicitud"));

                    cambios.add(cambio);

                }

            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }

        }

        return cambios;

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

                String consulta = "INSERT INTO cambio (idSolicitudDeCambio, titulo, descripcion, esfuerzoMinutos, idTipoActividad, idEstadoCambio, fechaInicio) VALUES (?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);

                sentencia.setInt(1, cambio.getIdSolicitud());
                sentencia.setString(2, cambio.getTitulo());
                sentencia.setString(3, cambio.getDescripcion());
                sentencia.setInt(4, cambio.getEsfuerzoMinutos());
                sentencia.setInt(5, cambio.getIdTipoActividad());
                sentencia.setInt(6, cambio.getIdEstadoCambio());
                sentencia.setString(7, cambio.getFechaInicio());

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

}
