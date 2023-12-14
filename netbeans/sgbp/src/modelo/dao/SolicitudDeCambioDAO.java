/*
 * Nombre del archivo: SolicitudDeCambioDAO.java
 * Autor: Miguel Angel Morales Cruz
 * Paquete: modelo.dao
 * Fecha de creación: 28/11/2023
 * Fecha de modificación: 13/12/2023
 * Descripción: Clase que contiene todos los metodos para registrar, consultar, 
 * evaluar una solicitud de cambio.
 */

package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import modelo.ConectorBaseDatos;
import modelo.pojo.EstadoSolicitud;
import modelo.pojo.SolicitudDeCambio;
import utilidades.Constantes;

public class SolicitudDeCambioDAO {

    public static ArrayList<SolicitudDeCambio> consultarSolicitudesCambio() {

        ArrayList<SolicitudDeCambio> solicitudes = new ArrayList<>();
        Connection conexionBD = ConectorBaseDatos.obtenerConexion();

        if (conexionBD != null) {

            try {

                String consulta = "SELECT Nombre, Descripcion, Razon, "
                        + "Impacto, Propuesta, IdEstadoSolicitud, "
                        + "IdEstudiante, FechaSolicitud "
                        + "FROM solicitudcambios ORDER BY FechaSolicitud DESC";
                PreparedStatement sentencia = conexionBD.prepareStatement(
                        consulta);

                ResultSet resultadoConsulta = sentencia.executeQuery();

                while (resultadoConsulta.next()) {

                    SolicitudDeCambio solicitud = new SolicitudDeCambio();

                    solicitud.setTitulo(resultadoConsulta.getString(
                            "Nombre"));
                    solicitud.setDescripcion(
                            resultadoConsulta.getString(
                            "Descripcion"));
                    solicitud.setRazon(resultadoConsulta.getString(
                            "Razon"));
                    solicitud.setImpacto(resultadoConsulta.getString(
                            "Impacto"));
                    solicitud.setAccionPropuesta(
                            resultadoConsulta.getString(
                            "Propuesta"));
                    solicitud.setIdEstadoSolicitud(
                            resultadoConsulta.getInt(
                            "IdEstadoSolicitud"));
                    solicitud.setIdEstudiante(
                            resultadoConsulta.getInt(
                            "IdEstudiante"));
                    solicitud.setFechaCreacion(
                            resultadoConsulta.getString(
                            "FechaCreacion"));

                    solicitudes.add(solicitud);
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                ConectorBaseDatos.cerrarConexion(conexionBD);
            }
        }
        return solicitudes;
    }

    public static HashMap<String, Object> consultarSolicitudes(
            Integer idProyecto) {

        HashMap<String, Object> respuesta = new HashMap<String, Object>();
        respuesta.put("error", true);
        Connection conexionBD = ConectorBaseDatos.obtenerConexion();

        if (conexionBD != null) {

            try {

                String consulta = "SELECT s.idSolicitudDeCambio, "
                        + "s.titulo, s.descripcion, s.razon, "
                        + "s.impacto, s.accionPropuesta, "
                        + "DATE_FORMAT(s.fechaCreacion, '%d-%m-%Y') "
                        + "AS fechaCreacion, s.fechaEvaluacion, "
                        + "s.idEstudiante, s.idEstadoSolicitud, "
                        + "s.idProyecto, s.idResponsableProyecto, "
                        + "s.idDefecto, CONCAT(e.nombre, ' ', "
                        + "e.apellidoPaterno, ' ', e.apellidoMaterno)"
                        + " AS estudiante "
                        + "FROM SolicitudDeCambio s "
                        + "JOIN Estudiante e ON s.idEstudiante = e.idEstudiante"
                        + " WHERE s.idProyecto = ? "
                        + "ORDER BY s.fechaCreacion DESC";
                PreparedStatement sentencia = conexionBD.prepareStatement(
                        consulta);
                sentencia.setInt(1, idProyecto);

                ResultSet resultadoConsulta = sentencia.executeQuery();

                ArrayList<SolicitudDeCambio> solicitudes = new ArrayList<>();

                while (resultadoConsulta.next()) {

                    SolicitudDeCambio solicitud = new SolicitudDeCambio();

                    solicitud.setIdSolicitudDeCambio(
                            resultadoConsulta.getInt(
                            "idSolicitudDeCambio"));
                    solicitud.setTitulo(resultadoConsulta.getString(
                            "titulo"));
                    solicitud.setDescripcion(
                            resultadoConsulta.getString(
                            "descripcion"));
                    solicitud.setRazon(resultadoConsulta.getString(
                            "razon"));
                    solicitud.setImpacto(resultadoConsulta.getString(
                            "impacto"));
                    solicitud.setAccionPropuesta(
                            resultadoConsulta.getString(
                            "accionPropuesta"));
                    solicitud.setFechaCreacion(
                            resultadoConsulta.getString(
                            "fechaCreacion"));
                    solicitud.setFechaEvaluacion(
                            resultadoConsulta.getString(
                            "fechaEvaluacion"));
                    solicitud.setIdEstudiante(
                            resultadoConsulta.getInt(
                            "idEstudiante"));
                    solicitud.setEstudiante(
                            resultadoConsulta.getString(
                            "estudiante"));
                    solicitud.setIdEstadoSolicitud(
                            resultadoConsulta.getInt(
                            "idEstadoSolicitud"));
                    solicitud.setIdProyecto(
                            resultadoConsulta.getInt(
                            "idProyecto"));
                    solicitud.setIdResponsable(
                            resultadoConsulta.getInt(
                            "idResponsableProyecto"));
                    solicitud.setIdDefecto(
                            resultadoConsulta.getInt(
                            "idDefecto"));

                    solicitudes.add(solicitud);

                }

                respuesta.put("error", false);
                respuesta.put("solicitudes", solicitudes);

            } catch (SQLException se) {
                respuesta.put("mensaje", "Error: " + se.getMessage());
            } finally {
                ConectorBaseDatos.cerrarConexion(conexionBD);
            }
        } else {
            respuesta.put("mensaje",
                    Constantes.MENSAJE_ERROR_DE_CONEXION);
        }

        return respuesta;
    }

    public static ArrayList<EstadoSolicitud> consultarEstadosSolicitud() {

        ArrayList<EstadoSolicitud> estadosSolicitud = new ArrayList<>();
        Connection conexionBD = ConectorBaseDatos.obtenerConexion();

        if (conexionBD != null) {

            try {

                String consulta = "SELECT IdEstadoSolicitud, "
                        + "Estado FROM EstadoSolicitud";
                PreparedStatement sentencia = conexionBD.prepareStatement(
                        consulta);

                ResultSet resultadoConsulta = sentencia.executeQuery();

                while (resultadoConsulta.next()) {

                    EstadoSolicitud estado = new EstadoSolicitud();
                    estado.setIdEstadoSolicitud(
                            resultadoConsulta.getInt(
                            "IdEstadoSolicitud"));
                    estado.setEstado(resultadoConsulta.getString(
                            "Estado"));

                    estadosSolicitud.add(estado);

                }

            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                ConectorBaseDatos.cerrarConexion(conexionBD);
            }
        }

        return estadosSolicitud;
    }

    public static HashMap<String, Object> consultarEstados() {

        HashMap<String, Object> respuesta = new HashMap<String, Object>();
        respuesta.put("error", true);
        Connection conexionBD = ConectorBaseDatos.obtenerConexion();

        if (conexionBD != null) {

            try {

                String consulta = "SELECT estado FROM EstadoSolicitud";
                PreparedStatement sentencia = conexionBD.prepareStatement(
                        consulta);
                ResultSet resultadoConsulta = sentencia.executeQuery();
                ArrayList<String> estados = new ArrayList<>();

                while (resultadoConsulta.next()) {

                    String estado = resultadoConsulta.getString(
                            "estado");

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
            respuesta.put("mensaje",
                    Constantes.MENSAJE_ERROR_DE_CONEXION);
        }

        return respuesta;
    }

    public static HashMap<String, Object> consultarSolicitudesPendientes(
            Integer idProyecto) {

        HashMap<String, Object> respuesta = new HashMap<String, Object>();
        respuesta.put("error", true);
        Connection conexionBD = ConectorBaseDatos.obtenerConexion();

        if (conexionBD != null) {

            try {

                String consulta = "SELECT "
                        + "s.idSolicitudDeCambio, "
                        + "s.titulo, "
                        + "s.descripcion, "
                        + "s.razon, "
                        + "s.impacto, "
                        + "s.accionPropuesta, "
                        + "DATE_FORMAT(s.fechaCreacion, '%d-%m-%Y')"
                        + "AS fechaCreacion, "
                        + "s.fechaEvaluacion, "
                        + "s.idEstudiante, "
                        + "s.idEstadoSolicitud, "
                        + "s.idProyecto, "
                        + "s.idResponsableProyecto, "
                        + "s.idDefecto, "
                        + "CONCAT(e.nombre, ' ', e.apellidoPaterno, ' ', "
                        + "e.apellidoMaterno) AS estudiante, "
                        + "d.titulo AS defecto, "
                        + "d.descripcion AS defectoDescripcion "
                        + "FROM "
                        + "SolicitudDeCambio s "
                        + "JOIN "
                        + "Estudiante e ON s.idEstudiante = e.idEstudiante "
                        + "LEFT JOIN "
                        + "Defecto d ON s.idDefecto = d.idDefecto "
                        + "WHERE "
                        + "s.idProyecto = ? AND s.idEstadoSolicitud = 3 "
                        + "ORDER BY "
                        + "s.fechaCreacion DESC";
                PreparedStatement sentencia = conexionBD
                        .prepareStatement(consulta);
                sentencia.setInt(1, idProyecto);

                ResultSet resultadoConsulta = sentencia.executeQuery();

                ArrayList<SolicitudDeCambio> solicitudes = new ArrayList<>();

                while (resultadoConsulta.next()) {

                    SolicitudDeCambio solicitud = new SolicitudDeCambio();

                    solicitud.setIdSolicitudDeCambio(
                            resultadoConsulta
                            .getInt("idSolicitudDeCambio"));
                    solicitud.setTitulo(resultadoConsulta
                            .getString("titulo"));
                    solicitud.setDescripcion(resultadoConsulta
                            .getString("descripcion"));
                    solicitud.setRazon(resultadoConsulta
                            .getString("razon"));
                    solicitud.setImpacto(resultadoConsulta
                            .getString("impacto"));
                    solicitud.setAccionPropuesta(resultadoConsulta
                            .getString("accionPropuesta"));
                    solicitud.setFechaCreacion(resultadoConsulta
                            .getString("fechaCreacion"));
                    solicitud.setFechaEvaluacion(resultadoConsulta
                            .getString("fechaEvaluacion"));
                    solicitud.setIdEstudiante(resultadoConsulta
                            .getInt("idEstudiante"));
                    solicitud.setEstudiante(resultadoConsulta
                            .getString("estudiante"));
                    solicitud.setIdEstadoSolicitud(
                            resultadoConsulta
                            .getInt("idEstadoSolicitud"));
                    solicitud.setIdProyecto(resultadoConsulta
                            .getInt("idProyecto"));
                    solicitud.setIdResponsable(resultadoConsulta
                            .getInt("idResponsableProyecto"));
                    solicitud.setIdDefecto(resultadoConsulta
                            .getInt("idDefecto"));
                    solicitud.setDefecto(resultadoConsulta
                            .getString("defecto"));
                    solicitudes.add(solicitud);

                }

                respuesta.put("error", false);
                respuesta.put("solicitudes", solicitudes);

            } catch (SQLException se) {
                respuesta.put("mensaje", 
                        Constantes.MENSAJE_ERROR_REGISTRO);
            } finally {
                ConectorBaseDatos.cerrarConexion(conexionBD);
            }
        } else {
            respuesta.put("mensaje", 
                    Constantes.MENSAJE_ERROR_DE_CONEXION);
        }

        return respuesta;
    }

    public static HashMap<String, Object> consultarSolicitudesEstudiante(
            Integer idEstudiante,
            Integer idProyecto) {

        HashMap<String, Object> respuesta = new HashMap<String, Object>();
        respuesta.put("error", true);
        Connection conexionBD = ConectorBaseDatos.obtenerConexion();

        if (conexionBD != null) {

            try {

                String consulta = "SELECT s.idSolicitudDeCambio, s.titulo, "
                        + "s.descripcion, s.razon, "
                        + "s.impacto, s.accionPropuesta, "
                        + "DATE_FORMAT(s.fechaCreacion, '%d-%m-%Y') "
                        + "AS fechaCreacion, s.fechaEvaluacion, "
                        + "s.idEstudiante, s.idEstadoSolicitud, "
                        + "s.idProyecto, s.idResponsableProyecto, "
                        + "s.idDefecto, CONCAT(e.nombre, ' ', "
                        + "e.apellidoPaterno, ' ', e.apellidoMaterno) "
                        + "AS estudiante "
                        + "FROM SolicitudDeCambio s "
                        + "JOIN Estudiante e "
                        + "ON s.idEstudiante = e.idEstudiante "
                        + "WHERE s.idProyecto = ? AND s.idEstudiante = ? "
                        + "ORDER BY s.fechaCreacion DESC";
                PreparedStatement sentencia = conexionBD.prepareStatement(
                        consulta);
                sentencia.setInt(1, idProyecto);
                sentencia.setInt(2, idEstudiante);

                ResultSet resultadoConsulta = sentencia.executeQuery();

                ArrayList<SolicitudDeCambio> solicitudes = new ArrayList<>();

                while (resultadoConsulta.next()) {

                    SolicitudDeCambio solicitud = new SolicitudDeCambio();

                    solicitud.setIdSolicitudDeCambio(
                            resultadoConsulta.getInt(
                            "idSolicitudDeCambio"));
                    solicitud.setTitulo(resultadoConsulta.getString(
                            "titulo"));
                    solicitud.setDescripcion(
                            resultadoConsulta.getString(
                            "descripcion"));
                    solicitud.setRazon(resultadoConsulta.getString(
                            "razon"));
                    solicitud.setImpacto(resultadoConsulta.getString(
                            "impacto"));
                    solicitud.setAccionPropuesta(
                            resultadoConsulta.getString(
                            "accionPropuesta"));
                    solicitud.setFechaCreacion(
                            resultadoConsulta.getString(
                            "fechaCreacion"));
                    solicitud.setFechaEvaluacion(
                            resultadoConsulta.getString(
                            "fechaEvaluacion"));
                    solicitud.setIdEstudiante(
                            resultadoConsulta.getInt(
                            "idEstudiante"));
                    solicitud.setEstudiante(
                            resultadoConsulta.getString(
                            "estudiante"));
                    solicitud.setIdEstadoSolicitud(
                            resultadoConsulta.getInt(
                            "idEstadoSolicitud"));
                    solicitud.setIdProyecto(resultadoConsulta.getInt(
                            "idProyecto"));
                    solicitud.setIdResponsable(
                            resultadoConsulta.getInt(
                            "idResponsableProyecto"));
                    solicitud.setIdDefecto(resultadoConsulta.getInt(
                            "idDefecto"));

                    solicitudes.add(solicitud);

                }

                respuesta.put("error", false);
                respuesta.put("solicitudes", solicitudes);

            } catch (SQLException se) {
                respuesta.put("mensaje", "Error: " + se.getMessage());
            } finally {
                ConectorBaseDatos.cerrarConexion(conexionBD);
            }
        } else {
            respuesta.put("mensaje",
                    "No se pudo conectar a la base de datos, "
                            + "inténtelo más tarde");
        }

        return respuesta;
    }

    public static HashMap<String, Object> registrarSolicitud(
            SolicitudDeCambio solicitud) {

        HashMap<String, Object> respuesta = new HashMap<String, Object>();
        respuesta.put("error", true);
        Connection conexionBD = ConectorBaseDatos.obtenerConexion();

        if (conexionBD != null) {

            try {

                String consulta = "INSERT INTO SolicitudDeCambio "
                        + "(titulo, descripcion, "
                        + "razon, impacto, accionPropuesta, fechaCreacion, "
                        + "idEstudiante, idEstadoSolicitud, idProyecto, "
                        + "idDefecto) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement sentencia = conexionBD.prepareStatement(
                        consulta);

                sentencia.setString(1,
                        solicitud.getTitulo());
                sentencia.setString(2,
                        solicitud.getDescripcion());
                sentencia.setString(3,
                        solicitud.getRazon());
                sentencia.setString(4,
                        solicitud.getImpacto());
                sentencia.setString(5,
                        solicitud.getAccionPropuesta());
                sentencia.setDate(6,
                        java.sql.Date.valueOf(
                                solicitud.getFechaCreacion()));
                sentencia.setInt(7,
                        solicitud.getIdEstudiante());
                sentencia.setInt(8,
                        solicitud.getIdEstadoSolicitud());
                sentencia.setInt(9,
                        solicitud.getIdProyecto());

                if (solicitud.getIdDefecto() != null) {
                    sentencia.setInt(10,
                            solicitud.getIdDefecto());
                } else {
                    sentencia.setNull(10,
                            java.sql.Types.INTEGER);
                }

                int filasAfectadas = sentencia.executeUpdate();

                if (filasAfectadas > 0) {
                    respuesta.put("error", false);
                    respuesta.put("mensaje",
                            "Solicitud enviada correctamente");
                } else {
                    respuesta.put("mensaje",
                            "Hubo un error al intentar registrar la solicitud, "
                                    + "por favor inténtelo más tarde");
                }

            } catch (SQLException ex) {

                respuesta.put("mensaje", 
                        Constantes.MENSAJE_ERROR_REGISTRO);
                ex.printStackTrace();

            } finally {
                ConectorBaseDatos.cerrarConexion(conexionBD);
            }
        } else {
            respuesta.put("mensaje", 
                    Constantes.MENSAJE_ERROR_REGISTRO);
        }

        return respuesta;
    }

    public static HashMap<String, Object> registrarEvaluacionDeSolicitud(
            int idSolicitud,
            String nuevaFechaEvaluacion,
            int nuevoIdEstadoSolicitud,
            int idResponsable) {

        HashMap<String, Object> respuesta = new HashMap<>();
        respuesta.put("error", true);
        Connection conexionBD = ConectorBaseDatos.obtenerConexion();

        if (conexionBD != null) {

            try {

                String consulta = "UPDATE SolicitudDeCambio "
                        + "SET fechaEvaluacion = ?,"
                        + " idEstadoSolicitud = ?, "
                        + " idResponsableProyecto = ? "
                        + "WHERE idSolicitudDeCambio = ?";
                PreparedStatement sentencia = conexionBD
                        .prepareStatement(consulta);
                sentencia.setString(1, nuevaFechaEvaluacion);
                sentencia.setInt(2, nuevoIdEstadoSolicitud);
                sentencia.setInt(3, idResponsable);
                sentencia.setInt(4, idSolicitud);

                int filasAfectadas = sentencia.executeUpdate();

                if (filasAfectadas > 0) {

                    respuesta.put("error", false);
                    respuesta.put("mensaje",
                            "Solicitud evaluada correctamente");

                } else {
                    respuesta.put("mensaje",
                            Constantes.MENSAJE_ERROR_REGISTRO);
                }

            } catch (SQLException ex) {

                respuesta.put("mensaje", 
                        Constantes.MENSAJE_ERROR_REGISTRO);
                ex.printStackTrace();

            } finally {
                ConectorBaseDatos.cerrarConexion(conexionBD);
            }

        } else {
            respuesta.put("mensaje", 
                    Constantes.MENSAJE_ERROR_DE_CONEXION);
        }

        return respuesta;

    }

}
