package modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelo.ConectorBaseDatos;
import modelo.pojo.Actividad;
import utilidades.Utilidades;

public class ActividadDAO {

    // Método Deprecado pero guardado por retrocompatibilidad
    public static ArrayList<Actividad> consultarActividades() {

        ArrayList<Actividad> actividades = new ArrayList<Actividad>();

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "SELECT idActividad, titulo, " +
                        "a.idEstudiante, CONCAT(e.nombre, ' ', " +
                        "e.apellidoPaterno, ' ', e.apellidoMaterno) AS" +
                        " Estudiante, fechaInicio FROM actividad a INNER JOIN" +
                        " estudiante e ON a.idEstudiante = e.idEstudiante " +
                        "WHERE fechaFin IS NULL ORDER BY fechaInicio ASC;";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);

                ResultSet resultadoConsulta = sentencia.executeQuery();

                while (resultadoConsulta.next()) {

                    Actividad actividad = new Actividad();
                    actividad.setIdActividad(resultadoConsulta.getInt("IdActividad"));
                    actividad.setTitulo(resultadoConsulta.getString("Titulo"));
                    actividad.setIdEstudiante(resultadoConsulta.getInt("IdEstudiante"));
                    actividad.setEstudiante(resultadoConsulta.getString("Estudiante"));
                    actividad.setFechaInicio(resultadoConsulta.getString("FechaInicio"));

                    actividades.add(actividad);

                }

            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }
        }

        return actividades;

    }

    // Modificacion que marcara error en el CU-07
    public static HashMap<String, Object> obtenerActividadesProyecto(int idProyecto) {

        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("error", true);
        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "SELECT "
                        + "idActividad, "
                        + "titulo, "
                        + "a.descripcion, "
                        + "esfuerzoMinutos, "
                        + "fechaInicio, "
                        + "fechaFin, "
                        + "a.idEstadoActividad, "
                        + "ea.estado AS estadoActividad, "
                        + "a.idTipoActividad, "
                        + "ta.tipo AS tipo, "
                        + "a.idEstudiante, "
                        + "CONCAT(e.nombre, ' ', e.apellidoMaterno, ' ', e.apellidoPaterno) AS estudiante, "
                        + "a.idResponsable, "
                        + "CONCAT(rp.nombre, ' ', rp.apellidoMaterno, ' ', rp.apellidoPaterno) AS responsable, "
                        + "a.idProyecto, "
                        + "p.nombre AS proyecto "
                        + "FROM actividad a "
                        + "INNER JOIN estadoactividad ea ON a.idEstadoActividad = ea.idEstadoActividad "
                        + "INNER JOIN tipoactividad ta ON a.idTipoActividad = ta.idTipoActividad "
                        + "LEFT JOIN estudiante e ON a.idEstudiante = e.idEstudiante "
                        + "INNER JOIN responsableproyecto rp ON a.idResponsable = rp.idResponsableProyecto "
                        + "INNER JOIN proyecto p ON a.idProyecto = p.idProyecto "
                        + "WHERE a.idProyecto = ? "
                        + "ORDER BY fechaInicio DESC;";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);
                sentencia.setInt(1, idProyecto);
                ResultSet resultadoConsulta = sentencia.executeQuery();
                ArrayList<Actividad> actividades = new ArrayList<>();

                while (resultadoConsulta.next()) {
                    // Tal vez sea necesario manejar Integer en lugar de Int, para poder manejar
                    // valores que vienen nulos
                    Actividad actividad = new Actividad();
                    actividad.setIdActividad(resultadoConsulta.getInt("idActividad"));
                    actividad.setTitulo(resultadoConsulta.getString("titulo"));
                    actividad.setDescripcion(resultadoConsulta.getString("descripcion"));
                    actividad.setEsfuerzoMinutos(resultadoConsulta.getInt("esfuerzoMinutos"));
                    actividad.setFechaInicio(resultadoConsulta.getString("fechaInicio"));
                    actividad.setFechaFin(resultadoConsulta.getString("fechaFin"));
                    actividad.setIdEstadoActividad(resultadoConsulta.getInt("idEstadoActividad"));
                    actividad.setEstadoActividad(resultadoConsulta.getString("estadoActividad"));
                    actividad.setIdTipo(resultadoConsulta.getInt("idTipoActividad"));
                    actividad.setTipo(resultadoConsulta.getString("tipo"));
                    actividad.setIdEstudiante(resultadoConsulta.getInt("idEstudiante"));
                    actividad.setEstudiante(resultadoConsulta.getString("estudiante"));
                    actividad.setIdResponsable(resultadoConsulta.getInt("idResponsable"));
                    actividad.setResponsable(resultadoConsulta.getString("responsable"));
                    actividad.setIdProyecto(resultadoConsulta.getInt("idProyecto"));
                    actividad.setProyecto(resultadoConsulta.getString("proyecto"));
                    actividades.add(actividad);

                }
                conexion.close();
                respuesta.put("error", false);
                respuesta.put("actividades", actividades);

            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
            }

        } else {
            respuesta.put("mensaje", "No se pudo conectar a la base de datos, inténtelo más tarde");
        }

        return respuesta;

    }

    public static HashMap<String, Object> obtenerActividadesSinAsignar(Integer idProyecto) {

        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("error", true);
        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "SELECT "
                        + "idActividad, "
                        + "titulo, "
                        + "a.descripcion, "
                        + "esfuerzoMinutos, "
                        + "fechaInicio, "
                        + "fechaFin, "
                        + "a.idEstadoActividad, "
                        + "ea.estado AS estadoActividad, "
                        + "a.idTipoActividad, "
                        + "ta.tipo AS tipo, "
                        + "a.idResponsable, "
                        + "CONCAT(rp.nombre, ' ', rp.apellidoMaterno, ' ', rp.apellidoPaterno) AS responsable, "
                        + "a.idProyecto, "
                        + "p.nombre AS proyecto "
                        + "FROM actividad a "
                        + "LEFT JOIN estadoactividad ea ON a.idEstadoActividad = ea.idEstadoActividad "
                        + "LEFT JOIN tipoactividad ta ON a.idTipoActividad = ta.idTipoActividad "
                        + "LEFT JOIN responsableproyecto rp ON a.idResponsable = rp.idResponsableProyecto "
                        + "LEFT JOIN proyecto p ON a.idProyecto = p.idProyecto "
                        + "WHERE a.idProyecto = ? AND idEstudiante IS NULL "
                        + "ORDER BY fechaInicio DESC;";

                PreparedStatement sentencia = conexion.prepareStatement(consulta);
                sentencia.setInt(1, idProyecto);
                ResultSet resultadoConsulta = sentencia.executeQuery();
                ArrayList<Actividad> actividades = new ArrayList<>();

                while (resultadoConsulta.next()) {

                    Actividad actividad = new Actividad();
                    actividad.setIdActividad(resultadoConsulta.getInt("idActividad"));
                    actividad.setTitulo(resultadoConsulta.getString("titulo"));
                    actividad.setDescripcion(resultadoConsulta.getString("descripcion"));
                    actividad.setEsfuerzoMinutos(resultadoConsulta.getInt("esfuerzoMinutos"));
                    actividad.setFechaInicio(resultadoConsulta.getString("fechaInicio"));
                    actividad.setFechaFin(resultadoConsulta.getString("fechaFin"));
                    actividad.setIdEstadoActividad(resultadoConsulta.getInt("idEstadoActividad"));
                    actividad.setEstadoActividad(resultadoConsulta.getString("estadoActividad"));
                    actividad.setIdTipo(resultadoConsulta.getInt("idTipoActividad"));
                    actividad.setTipo(resultadoConsulta.getString("tipo"));
                    actividad.setIdResponsable(resultadoConsulta.getInt("idResponsable"));
                    actividad.setResponsable(resultadoConsulta.getString("responsable"));
                    actividad.setIdProyecto(resultadoConsulta.getInt("idProyecto"));
                    actividad.setProyecto(resultadoConsulta.getString("proyecto"));
                    actividades.add(actividad);

                }

                conexion.close();
                respuesta.put("error", false);
                respuesta.put("actividades", actividades);

            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
            } 
        } else {
            respuesta.put("mensaje", "No se pudo conectar a la base de datos, inténtelo más tarde");
        }

        return respuesta;
    }

    public static HashMap<String, Object> consultarActividadesProyecto(Integer idProyecto) {

        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("error", true);
        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "SELECT "
                        + "idActividad, "
                        + "titulo, "
                        + "a.descripcion, "
                        + "esfuerzoMinutos, "
                        + "fechaInicio, "
                        + "fechaFin, "
                        + "a.idEstadoActividad, "
                        + "ea.estado AS estadoActividad, "
                        + "a.idTipoActividad, "
                        + "ta.tipo AS tipo, "
                        + "a.idEstudiante, "
                        + "CONCAT(e.nombre, ' ', e.apellidoMaterno, ' ', e.apellidoPaterno) AS estudiante, "
                        + "a.idResponsable, "
                        + "CONCAT(rp.nombre, ' ', rp.apellidoMaterno, ' ', rp.apellidoPaterno) AS responsable, "
                        + "a.idProyecto, "
                        + "p.nombre AS proyecto "
                        + "FROM actividad a "
                        + "LEFT JOIN estadoactividad ea ON a.idEstadoActividad = ea.idEstadoActividad "
                        + "LEFT JOIN tipoactividad ta ON a.idTipoActividad = ta.idTipoActividad "
                        + "LEFT JOIN estudiante e ON a.idEstudiante = e.idEstudiante "
                        + "LEFT JOIN responsableproyecto rp ON a.idResponsable = rp.idResponsableProyecto "
                        + "LEFT JOIN proyecto p ON a.idProyecto = p.idProyecto "
                        + "WHERE a.idProyecto = ? "
                        + "ORDER BY fechaInicio DESC;";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);

                sentencia.setInt(1, idProyecto);

                ResultSet resultadoConsulta = sentencia.executeQuery();
                ArrayList<Actividad> actividades = new ArrayList<>();

                while (resultadoConsulta.next()) {

                    Actividad actividad = new Actividad();
                    actividad.setIdActividad(resultadoConsulta.getInt("idActividad"));
                    actividad.setTitulo(resultadoConsulta.getString("titulo"));
                    actividad.setDescripcion(resultadoConsulta.getString("descripcion"));
                    actividad.setEsfuerzoMinutos(resultadoConsulta.getInt("esfuerzoMinutos"));
                    actividad.setFechaInicio(resultadoConsulta.getString("fechaInicio"));
                    actividad.setFechaFin(resultadoConsulta.getString("fechaFin"));
                    actividad.setIdEstadoActividad(resultadoConsulta.getInt("idEstadoActividad"));
                    actividad.setEstadoActividad(resultadoConsulta.getString("estadoActividad"));
                    actividad.setIdTipo(resultadoConsulta.getInt("idTipoActividad"));
                    actividad.setTipo(resultadoConsulta.getString("tipo"));
                    actividad.setIdEstudiante(resultadoConsulta.getInt("idEstudiante"));
                    actividad.setEstudiante(resultadoConsulta.getString("estudiante"));
                    actividad.setIdResponsable(resultadoConsulta.getInt("idResponsable"));
                    actividad.setResponsable(resultadoConsulta.getString("responsable"));
                    actividad.setIdProyecto(resultadoConsulta.getInt("idProyecto"));
                    actividad.setProyecto(resultadoConsulta.getString("proyecto"));
                    actividades.add(actividad);

                }

                respuesta.put("error", false);
                respuesta.put("actividades", actividades);

            } catch (SQLException sqlE) {
                respuesta.put("mensaje", "Error: " + sqlE.getMessage());
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
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

                String consulta = "SELECT tipo FROM tipoactividad;";
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
            respuesta.put("mensaje", "No se pudo conectar a la base de datos, inténtelo de nuevo más tarde");
        }

        return respuesta;
    }

    public static boolean reasignarActividad(int idActividad, int idEstudiante) {

        boolean reasignacionExitosa = false;

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "UPDATE actividad SET IdEstudiante = ? WHERE IdActividad = ?;";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);
                sentencia.setInt(1, idEstudiante);
                sentencia.setInt(2, idActividad);

                int resultadoConsulta = sentencia.executeUpdate();

                if (resultadoConsulta > 0) {
                    reasignacionExitosa = true;
                }

            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }
        }

        return reasignacionExitosa;

    }

    // Este metodo puede usarse para asignar y reasignar
    public static HashMap<String, Object> asignarActividad(int idActividad, int idEstudiante) {

        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("error", true);
        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "UPDATE actividad "
                        + "SET IdEstudiante = ? WHERE IdActividad = ?;";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);
                sentencia.setInt(1, idEstudiante);
                sentencia.setInt(2, idActividad);
                int resultadoConsulta = sentencia.executeUpdate();
                conexion.close();

                if (resultadoConsulta > 0) {

                    respuesta.put("error", false);
                    respuesta.put("mensaje", "La actividad fue asignada con éxito");

                } else {
                    respuesta.put("mensaje", "No se pudo asignar la actividad");
                }

            } catch (SQLException sqlE) {
                respuesta.put("mensaje", "Error: " + sqlE.getMessage());
            }
        } else {
            respuesta.put("mensaje", "No se pudo conectar a la base de datos, inténtelo de nuevo más tarde");
        }

        return respuesta;

    }

    public static HashMap<String, Object> registrarActividad(Actividad actividad) throws SQLException {

        HashMap<String, Object> respuesta = new HashMap<>();

        respuesta.put("error", true);

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        String fechaServidor = Utilidades.obtenerFechaServidor();

        Date fechaServidorDate = Date.valueOf(fechaServidor);
        Date fechaInicioDate = Date.valueOf(actividad.getFechaInicio());

        if (fechaInicioDate.before(fechaServidorDate)) {
            throw new SQLException(
                    "Error en la base de datos: La fecha de inicio no puede ser menor a la fecha actual");
        }

        if (conexion != null) {

            try {

                String consulta = "INSERT INTO actividad (titulo, descripcion, " +
                        "idProyecto, idResponsable, idTipoActividad, fechaInicio, " +
                        "idEstadoActividad) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);

                sentencia.setString(1, actividad.getTitulo());
                sentencia.setString(2, actividad.getDescripcion());
                sentencia.setInt(3, actividad.getIdProyecto());
                sentencia.setInt(4, actividad.getIdResponsable());
                sentencia.setInt(5, actividad.getIdTipo());
                sentencia.setString(6, actividad.getFechaInicio());
                sentencia.setInt(7, actividad.getIdEstadoActividad());

                int resultadoConsulta = sentencia.executeUpdate();

                if (resultadoConsulta > 0) {

                    respuesta.put("error", false);
                    respuesta.put("mensaje", "La actividad fue registrada con éxito");

                } else {
                    respuesta.put("mensaje", "No se pudo registrar la actividad");
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

    public static HashMap<String, Object> finalizarActividad(int idActividad) {

        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("error", true);
        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "UPDATE actividad SET fechaFin = NOW(), idEstadoActividad = 1 WHERE idActividad = ?;";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);
                sentencia.setInt(1, idActividad);
                int resultadoConsulta = sentencia.executeUpdate();
                conexion.close();

                if (resultadoConsulta > 0) {

                    respuesta.put("error", false);
                    respuesta.put("mensaje", "La actividad fue finalizada con éxito");

                } else {
                    respuesta.put("mensaje", "No se pudo finalizar la actividad");
                }

            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
                respuesta.put("mensaje", "Error: " + sqlE.getMessage());
            }
        } else {
            respuesta.put("mensaje", "No se pudo conectar a la base de datos, inténtelo más tarde");
        }

        return respuesta;

    }

    public static HashMap<String, Object> eliminarActividad(int idActividad) {

        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("error", true);
        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "DELETE FROM actividad WHERE idActividad = ?;";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);
                sentencia.setInt(1, idActividad);
                int resultadoConsulta = sentencia.executeUpdate();
                conexion.close();

                if (resultadoConsulta > 0) {

                    respuesta.put("error", false);
                    respuesta.put("mensaje", "La actividad se ha eliminado correctamente");

                } else {
                    respuesta.put("mensaje", "No se pudo eliminar la actividad");
                }

            } catch (SQLException sqlE) {
                respuesta.put("mensaje", "Error: " + sqlE.getMessage());
            }
        } else {
            respuesta.put("mensaje", "No se pudo conectar a la base de datos, inténtelo más tarde");
        }

        return respuesta;

    }

    public static HashMap<String, Object> obtenerActividadesSinFinalizar(int idProyecto) {

        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("error", true);
        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {
                
                String consulta = "SELECT "
                        + "a.idActividad, "
                        + "a.titulo, "
                        + "a.descripcion, "
                        + "a.esfuerzoMinutos, "
                        + "DATE_FORMAT(a.fechaInicio, '%d/%m/%Y') AS fechaInicio, "
                        + "ea.estado, "
                        + "ta.tipo, "
                        + "CONCAT(e.nombre, ' ', e.apellidoMaterno, ' ', e.apellidoPaterno) AS estudiante "
                        + "FROM actividad a "
                        + "INNER JOIN estadoactividad ea ON a.idEstadoActividad = ea.idEstadoActividad "
                        + "INNER JOIN tipoactividad ta ON a.idTipoActividad = ta.idTipoActividad "
                        + "LEFT JOIN estudiante e ON a.idEstudiante = e.idEstudiante "
                        + "WHERE a.idProyecto = ? AND a.idEstadoActividad != 'Realizada';";

                PreparedStatement sentencia = conexion.prepareStatement(consulta);
                sentencia.setInt(1, idProyecto);
                ResultSet resultado = sentencia.executeQuery();
                ArrayList<Actividad> actividades = new ArrayList<>();

                while(resultado.next()) {

                    Actividad actividad = new Actividad();
                    actividad.setIdActividad(resultado.getInt("idActividad"));
                    actividad.setTitulo(resultado.getString("titulo"));
                    actividad.setDescripcion(resultado.getString("descripcion"));
                    actividad.setEsfuerzoMinutos(resultado.getInt("esfuerzoMinutos"));
                    actividad.setFechaInicio(resultado.getString("fechaInicio"));
                    actividad.setEstadoActividad(resultado.getString("estado"));
                    actividad.setTipo(resultado.getString("tipo"));
                    actividad.setEstudiante(resultado.getString("estudiante"));
                    actividades.add(actividad);

                }

                conexion.close();
                respuesta.put("error", false);
                respuesta.put("actividades", actividades);

            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
                respuesta.put("mensaje", "Error: " + sqlE.getMessage());
            }

        } else {
            respuesta.put("mensaje", "No se pudo conectar a la base de datos, inténtelo más tarde");
        }

        return respuesta;
    }

}