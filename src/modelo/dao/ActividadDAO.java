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
import utilidades.Constantes;
import utilidades.Utilidades;

public class ActividadDAO {

    public static HashMap<String, Object> consultarActividades() {

        HashMap<String, Object> respuesta = new HashMap<>();

        respuesta.put("error", true);

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {
            try {
                String consulta = "SELECT idActividad, titulo, a.idEstudiante, "
                        + "CONCAT(e.nombre, ' ', e.apellidoPaterno, "
                        + "' ', e.apellidoMaterno) AS Estudiante, "
                        + "fechaInicio FROM actividad a INNER JOIN estudiante e"
                        + " ON a.idEstudiante = "
                        + "e.idEstudiante WHERE fechaFin IS NULL "
                        + "ORDER BY fechaInicio ASC;";
                PreparedStatement sentencia = conexion.prepareStatement(
                        consulta);

                ResultSet resultadoConsulta = sentencia.executeQuery();

                ArrayList<HashMap<String, Object>> listaActividades;
                listaActividades = new ArrayList<>();

                while (resultadoConsulta.next()) {
                    HashMap<String, Object> actividadMap = new HashMap<>();
                    actividadMap.put(
                            "idActividad",
                            resultadoConsulta.getInt(
                                    "idActividad"));
                    actividadMap.put(
                            "titulo",
                            resultadoConsulta.getString(
                                    "titulo"));
                    actividadMap.put(
                            "idEstudiante",
                            resultadoConsulta.getInt(
                                    "idEstudiante"));
                    actividadMap.put(
                            "estudiante",
                            resultadoConsulta.getString(
                                    "Estudiante"));
                    actividadMap.put(
                            "fechaInicio",
                            resultadoConsulta.getString(
                                    "fechaInicio"));

                    listaActividades.add(actividadMap);
                }

                respuesta.put("error", false);
                respuesta.put("actividades", listaActividades);

            } catch (SQLException se) {
                se.printStackTrace();
                respuesta.put("mensaje", "Error en la base de datos: "
                        + se.getMessage());
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }
        } else {
            respuesta.put("mensaje",
                    "No se pudo conectar a la base de datos, "
                            + "inténtelo más tarde");
        }

        return respuesta;
    }

    public static HashMap<String, Object> obtenerTodasActividadesProyecto(
            Integer idProyecto) {

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
                        + "CONCAT(e.nombre, ' ', e.apellidoMaterno, "
                        + "' ', e.apellidoPaterno) AS estudiante, "
                        + "a.idResponsable, "
                        + "CONCAT(rp.nombre, ' ', rp.apellidoMaterno, "
                        + "' ', rp.apellidoPaterno) AS responsable, "
                        + "a.idProyecto, "
                        + "p.nombre AS proyecto "
                        + "FROM actividad a "
                        + "INNER JOIN estadoactividad ea "
                        + "ON a.idEstadoActividad = ea.idEstadoActividad "
                        + "INNER JOIN tipoactividad ta "
                        + "ON a.idTipoActividad = ta.idTipoActividad "
                        + "LEFT JOIN estudiante e "
                        + "ON a.idEstudiante = e.idEstudiante "
                        + "INNER JOIN responsableproyecto rp "
                        + "ON a.idResponsable = rp.idResponsableProyecto "
                        + "INNER JOIN proyecto p "
                        + "ON a.idProyecto = p.idProyecto "
                        + "WHERE a.idProyecto = ? "
                        + "ORDER BY fechaInicio DESC;";
                PreparedStatement sentencia = conexion.prepareStatement(
                        consulta);
                sentencia.setInt(1, idProyecto);
                ResultSet resultadoConsulta = sentencia.executeQuery();
                ArrayList<Actividad> actividades = new ArrayList<>();

                while (resultadoConsulta.next()) {
                        
                    Actividad actividad = new Actividad();
                    actividad.setIdActividad(resultadoConsulta.getInt(
                            "idActividad"));
                    actividad.setTitulo(resultadoConsulta.getString(
                            "titulo"));
                    actividad.setDescripcion(resultadoConsulta.getString(
                            "descripcion"));
                    actividad.setEsfuerzoMinutos(resultadoConsulta.getInt(
                            "esfuerzoMinutos"));
                    actividad.setFechaInicio(resultadoConsulta.getString(
                            "fechaInicio"));
                    actividad.setFechaFin(resultadoConsulta.getString(
                            "fechaFin"));
                    actividad.setIdEstadoActividad(resultadoConsulta.getInt(
                            "idEstadoActividad"));
                    actividad.setEstadoActividad(resultadoConsulta.getString(
                            "estadoActividad"));
                    actividad.setIdTipo(resultadoConsulta.getInt(
                            "idTipoActividad"));
                    actividad.setTipo(resultadoConsulta.getString(
                            "tipo"));
                    actividad.setIdEstudiante(resultadoConsulta.getInt(
                            "idEstudiante"));
                    actividad.setEstudiante(resultadoConsulta.getString(
                            "estudiante"));
                    actividad.setIdResponsable(resultadoConsulta.getInt(
                            "idResponsable"));
                    actividad.setResponsable(resultadoConsulta.getString(
                            "responsable"));
                    actividad.setIdProyecto(resultadoConsulta.getInt(
                            "idProyecto"));
                    actividad.setProyecto(resultadoConsulta.getString(
                            "proyecto"));
                    actividades.add(actividad);

                }

                respuesta.put("error", false);
                respuesta.put("actividades", actividades);

            } catch (SQLException sqlE) {

                sqlE.printStackTrace();
                respuesta.put("mensaje", Constantes.MENSAJE_ERROR_REGISTRO);

            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }

        } else {
            respuesta.put("mensaje", Constantes.MENSAJE_ERROR_DE_CONEXION);
        }

        return respuesta;

    }

    public static HashMap<String, Object> obtenerActividadesSinAsignar(
            Integer idProyecto) {

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
                        + "CONCAT(rp.nombre, ' ', rp.apellidoMaterno, "
                        + "' ', rp.apellidoPaterno) AS responsable, "
                        + "a.idProyecto, "
                        + "p.nombre AS proyecto "
                        + "FROM actividad a "
                        + "LEFT JOIN estadoactividad ea "
                        + "ON a.idEstadoActividad = ea.idEstadoActividad "
                        + "LEFT JOIN tipoactividad ta "
                        + "ON a.idTipoActividad = ta.idTipoActividad "
                        + "LEFT JOIN responsableproyecto rp "
                        + "ON a.idResponsable = rp.idResponsableProyecto "
                        + "LEFT JOIN proyecto p "
                        + "ON a.idProyecto = p.idProyecto "
                        + "WHERE a.idProyecto = ? AND idEstudiante IS NULL "
                        + "ORDER BY fechaInicio DESC;";

                PreparedStatement sentencia = conexion.prepareStatement(
                        consulta);
                sentencia.setInt(1, idProyecto);
                ResultSet resultadoConsulta = sentencia.executeQuery();
                ArrayList<Actividad> actividades = new ArrayList<>();

                while (resultadoConsulta.next()) {

                    Actividad actividad = new Actividad();
                    actividad.setIdActividad(resultadoConsulta.getInt(
                            "idActividad"));
                    actividad.setTitulo(resultadoConsulta.getString(
                            "titulo"));
                    actividad.setDescripcion(resultadoConsulta.getString(
                            "descripcion"));
                    actividad.setEsfuerzoMinutos(resultadoConsulta.getInt(
                            "esfuerzoMinutos"));
                    actividad.setFechaInicio(resultadoConsulta.getString(
                            "fechaInicio"));
                    actividad.setFechaFin(resultadoConsulta.getString(
                            "fechaFin"));
                    actividad.setIdEstadoActividad(resultadoConsulta.getInt(
                            "idEstadoActividad"));
                    actividad.setEstadoActividad(resultadoConsulta.getString(
                            "estadoActividad"));
                    actividad.setIdTipo(resultadoConsulta.getInt(
                            "idTipoActividad"));
                    actividad.setTipo(resultadoConsulta.getString(
                            "tipo"));
                    actividad.setIdResponsable(resultadoConsulta.getInt(
                            "idResponsable"));
                    actividad.setResponsable(resultadoConsulta.getString(
                            "responsable"));
                    actividad.setIdProyecto(resultadoConsulta.getInt(
                            "idProyecto"));
                    actividad.setProyecto(resultadoConsulta.getString(
                            "proyecto"));
                    actividades.add(actividad);

                }

                conexion.close();
                respuesta.put("error", false);
                respuesta.put("actividades", actividades);

            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
                respuesta.put("mensaje", Constantes.MENSAJE_ERROR_REGISTRO);
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }
        } else {
            respuesta.put("mensaje", Constantes.MENSAJE_ERROR_DE_CONEXION);
        }

        return respuesta;
    }

    public static HashMap<String, Object> consultarActividadesProyecto(
            Integer idProyecto) {

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
                        + "CONCAT(e.nombre, ' ', e.apellidoMaterno, "
                        + "' ', e.apellidoPaterno) AS estudiante, "
                        + "a.idResponsable, "
                        + "CONCAT(rp.nombre, ' ', rp.apellidoMaterno, "
                        + "' ', rp.apellidoPaterno) AS responsable, "
                        + "a.idProyecto, "
                        + "p.nombre AS proyecto "
                        + "FROM actividad a "
                        + "LEFT JOIN estadoactividad ea "
                        + "ON a.idEstadoActividad = ea.idEstadoActividad "
                        + "LEFT JOIN tipoactividad ta "
                        + "ON a.idTipoActividad = ta.idTipoActividad "
                        + "LEFT JOIN estudiante e "
                        + "ON a.idEstudiante = e.idEstudiante "
                        + "LEFT JOIN responsableproyecto rp "
                        + "ON a.idResponsable = rp.idResponsableProyecto "
                        + "LEFT JOIN proyecto p "
                        + "ON a.idProyecto = p.idProyecto "
                        + "WHERE a.idProyecto = ? "
                        + "ORDER BY fechaInicio DESC;";
                PreparedStatement sentencia = conexion.prepareStatement(
                        consulta);

                sentencia.setInt(1, idProyecto);

                ResultSet resultadoConsulta = sentencia.executeQuery();
                ArrayList<Actividad> actividades = new ArrayList<>();

                while (resultadoConsulta.next()) {

                    Actividad actividad = new Actividad();
                    actividad.setIdActividad(resultadoConsulta.getInt(
                            "idActividad"));
                    actividad.setTitulo(resultadoConsulta.getString(
                            "titulo"));
                    actividad.setDescripcion(resultadoConsulta.getString(
                            "descripcion"));
                    actividad.setEsfuerzoMinutos(resultadoConsulta.getInt(
                            "esfuerzoMinutos"));
                    actividad.setFechaInicio(resultadoConsulta.getString(
                            "fechaInicio"));
                    actividad.setFechaFin(resultadoConsulta.getString(
                            "fechaFin"));
                    actividad.setIdEstadoActividad(resultadoConsulta.getInt(
                            "idEstadoActividad"));
                    actividad.setEstadoActividad(resultadoConsulta.getString(
                            "estadoActividad"));
                    actividad.setIdTipo(resultadoConsulta.getInt(
                            "idTipoActividad"));
                    actividad.setTipo(resultadoConsulta.getString(
                            "tipo"));
                    actividad.setIdEstudiante(resultadoConsulta.getInt(
                            "idEstudiante"));
                    actividad.setEstudiante(resultadoConsulta.getString(
                            "estudiante"));
                    actividad.setIdResponsable(resultadoConsulta.getInt(
                            "idResponsable"));
                    actividad.setResponsable(resultadoConsulta.getString(
                            "responsable"));
                    actividad.setIdProyecto(resultadoConsulta.getInt(
                            "idProyecto"));
                    actividad.setProyecto(resultadoConsulta.getString(
                            "proyecto"));
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
            respuesta.put("mensaje",
                    "No se pudo conectar a la base de datos, "
                            + "inténtelo más tarde");
        }

        return respuesta;

    }

    public static HashMap<String, Object> consultarActividadesEstudiante(
            Integer idEstudiante,
            Integer idProyecto) {

        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("error", true);
        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "SELECT "
                        + "a.idActividad, a.titulo, a.descripcion, "
                        + "a.esfuerzoMinutos, a.fechaInicio, a.fechaFin, "
                        + "a.idEstadoActividad, "
                        + "ea.estado AS estadoActividad, a.idTipoActividad, "
                        + "ta.tipo AS tipo, "
                        + "a.idEstudiante, "
                        + "CONCAT(e.nombre, ' ', e.apellidoMaterno, "
                        + "' ', e.apellidoPaterno) AS estudiante, "
                        + "a.idResponsable, "
                        + "CONCAT(rp.nombre, ' ', rp.apellidoMaterno, "
                        + "' ', rp.apellidoPaterno) AS responsable, "
                        + "a.idProyecto, "
                        + "p.nombre AS proyecto "
                        + "FROM actividad a "
                        + "JOIN estadoactividad ea "
                        + "ON a.idEstadoActividad = ea.idEstadoActividad "
                        + "JOIN tipoactividad ta "
                        + "ON a.idTipoActividad = ta.idTipoActividad "
                        + "JOIN estudiante e "
                        + "ON a.idEstudiante = e.idEstudiante "
                        + "JOIN responsableproyecto rp "
                        + "ON a.idResponsable = rp.idResponsableProyecto "
                        + "JOIN proyecto p "
                        + "ON a.idProyecto = p.idProyecto "
                        + "WHERE a.idProyecto = ? AND a.idEstudiante = ? "
                        + "ORDER BY fechaInicio DESC;";
                PreparedStatement sentencia = conexion.prepareStatement(
                        consulta);

                sentencia.setInt(1, idProyecto);
                sentencia.setInt(2, idEstudiante);

                ResultSet resultadoConsulta = sentencia.executeQuery();
                ArrayList<Actividad> actividades = new ArrayList<>();

                while (resultadoConsulta.next()) {

                    Actividad actividad = new Actividad();
                    actividad.setIdActividad(resultadoConsulta.getInt(
                            "idActividad"));
                    actividad.setTitulo(resultadoConsulta.getString(
                            "titulo"));
                    actividad.setDescripcion(resultadoConsulta.getString(
                            "descripcion"));
                    actividad.setEsfuerzoMinutos(resultadoConsulta.getInt(
                            "esfuerzoMinutos"));
                    actividad.setFechaInicio(resultadoConsulta.getString(
                            "fechaInicio"));
                    actividad.setFechaFin(resultadoConsulta.getString(
                            "fechaFin"));
                    actividad.setIdEstadoActividad(resultadoConsulta.getInt(
                            "idEstadoActividad"));
                    actividad.setEstadoActividad(resultadoConsulta.getString(
                            "estadoActividad"));
                    actividad.setIdTipo(resultadoConsulta.getInt(
                            "idTipoActividad"));
                    actividad.setTipo(resultadoConsulta.getString(
                            "tipo"));
                    actividad.setIdEstudiante(resultadoConsulta.getInt(
                            "idEstudiante"));
                    actividad.setEstudiante(resultadoConsulta.getString(
                            "estudiante"));
                    actividad.setIdResponsable(resultadoConsulta.getInt(
                            "idResponsable"));
                    actividad.setResponsable(resultadoConsulta.getString(
                            "responsable"));
                    actividad.setIdProyecto(resultadoConsulta.getInt(
                            "idProyecto"));
                    actividad.setProyecto(resultadoConsulta.getString(
                            "proyecto"));
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
            respuesta.put("mensaje",
                    "No se pudo conectar a la base de datos, "
                            + "inténtelo más tarde");
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
                PreparedStatement sentencia = conexion.prepareStatement(
                        consulta);
                ResultSet resultadoConsulta = sentencia.executeQuery();
                ArrayList<String> tiposActividades = new ArrayList<>();

                respuesta.put("error", false);

                while (resultadoConsulta.next()) {
                    tiposActividades.add(resultadoConsulta.getString(
                            "tipo"));
                }

                respuesta.put("error", false);
                respuesta.put("tiposActividades", tiposActividades);

            } catch (SQLException se) {
                respuesta.put("mensaje", "Error: " + se.getMessage());
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }

        } else {
            respuesta.put("mensaje",
                    "No se pudo conectar a la base de datos, "
                            + "inténtelo de nuevo más tarde");
        }

        return respuesta;
    }

    public static boolean reasignarActividad(int idActividad,
            int idEstudiante) {

        boolean reasignacionExitosa = false;

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "UPDATE actividad SET IdEstudiante = ? "
                        + "WHERE IdActividad = ?;";
                PreparedStatement sentencia = conexion.prepareStatement(
                        consulta);
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

    public static HashMap<String, Object> asignarActividad(int idActividad,
            int idEstudiante) {

        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("error", true);
        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "UPDATE actividad "
                        + "SET IdEstudiante = ? WHERE IdActividad = ?;";
                PreparedStatement sentencia = conexion.prepareStatement(
                        consulta);
                sentencia.setInt(1, idEstudiante);
                sentencia.setInt(2, idActividad);
                int resultadoConsulta = sentencia.executeUpdate();

                if (resultadoConsulta > 0) {

                    respuesta.put("error", false);
                    respuesta.put("mensaje",
                            "La actividad fue asignada con éxito");

                } else {
                    respuesta.put("mensaje",
                            "No se pudo asignar la actividad");
                }

            } catch (SQLException sqlE) {
                respuesta.put("mensaje", "Error: " + sqlE.getMessage());
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }
        } else {
            respuesta.put("mensaje",
                    "No se pudo conectar a la base de datos, "
                            + "inténtelo de nuevo más tarde");
        }

        return respuesta;

    }

    public static HashMap<String, Object> registrarActividad(
            Actividad actividad) throws SQLException {

        HashMap<String, Object> respuesta = new HashMap<>();

        respuesta.put("error", true);

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        String fechaServidor = Utilidades.obtenerFechaServidor();

        Date fechaServidorDate = Date.valueOf(fechaServidor);
        Date fechaInicioDate = Date.valueOf(actividad.getFechaInicio());

        if (fechaInicioDate.before(fechaServidorDate)) {
            throw new SQLException(
                    "Error en la base de datos: "
                            + "La fecha de inicio no puede ser menor "
                            + "a la fecha actual");
        }

        if (conexion != null) {

            try {

                String consulta = "INSERT INTO actividad "
                        + "(titulo, descripcion, "
                        + "idProyecto, idResponsable, idTipoActividad, "
                        + "fechaInicio, "
                        + "idEstadoActividad) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement sentencia = conexion.prepareStatement(
                        consulta);

                sentencia.setString(1, actividad.getTitulo());
                sentencia.setString(2,
                        actividad.getDescripcion());
                sentencia.setInt(3, actividad.getIdProyecto());
                sentencia.setInt(4,
                        actividad.getIdResponsable());
                sentencia.setInt(5,
                        actividad.getIdTipo());
                sentencia.setString(6,
                        actividad.getFechaInicio());
                sentencia.setInt(7,
                        actividad.getIdEstadoActividad());

                int resultadoConsulta = sentencia.executeUpdate();

                if (resultadoConsulta > 0) {

                    respuesta.put("error", false);
                    respuesta.put("mensaje",
                            "La actividad fue registrada con éxito");

                } else {
                    respuesta.put("mensaje",
                            "No se pudo registrar la actividad");
                }

            } catch (SQLException se) {
                respuesta.put("mensaje", "Error: " + se.getMessage());
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }

        } else {
            respuesta.put("mensaje",
                    "No se pudo conectar a la base de datos, "
                            + "inténtelo más tarde");
        }

        return respuesta;

    }

    public static HashMap<String, Object> finalizarActividad(int idActividad, int esfuerzoMinutos) {

        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("error", true);
        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "UPDATE actividad SET fechaFin = NOW(), "
                        + "esfuerzoMinutos = ?, "
                        + "idEstadoActividad = 1 WHERE idActividad = ?;";
                PreparedStatement sentencia = conexion.prepareStatement(
                        consulta);
                sentencia.setInt(1, esfuerzoMinutos);
                sentencia.setInt(2, idActividad);
                int resultadoConsulta = sentencia.executeUpdate();
                conexion.close();

                if (resultadoConsulta > 0) {

                    respuesta.put("error", false);
                    respuesta.put("mensaje",
                            "La actividad fue finalizada con éxito");

                } else {
                    respuesta.put("mensaje",
                            "No se pudo finalizar la actividad");
                }

            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
                respuesta.put("mensaje", "Error: " + sqlE.getMessage());
            }
        } else {
            respuesta.put("mensaje",
                    "No se pudo conectar a la base de datos, "
                            + "inténtelo más tarde");
        }

        return respuesta;

    }

    public static HashMap<String, Object> eliminarActividad(int idActividad) {

        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("error", true);
        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "DELETE FROM actividad WHERE idActividad = ?";
                PreparedStatement sentencia = conexion.prepareStatement(
                        consulta);
                sentencia.setInt(1, idActividad);
                int resultadoConsulta = sentencia.executeUpdate();
                conexion.close();

                if (resultadoConsulta > 0) {

                    respuesta.put("error", false);
                    respuesta.put("mensaje",
                            "La actividad se ha eliminado correctamente");

                } else {
                    respuesta.put("mensaje",
                            "No se pudo eliminar la actividad");
                }

            } catch (SQLException sqlE) {
                respuesta.put("mensaje", "Error: " + sqlE.getMessage());
            }
        } else {
            respuesta.put("mensaje",
                    "No se pudo conectar a la base de datos, "
                            + "inténtelo más tarde");
        }

        return respuesta;

    }

}