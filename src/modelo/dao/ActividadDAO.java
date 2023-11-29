package modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelo.ConectorBaseDatos;
import modelo.pojo.Actividad;

public class ActividadDAO {
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
            respuesta.put("mensaje", "No se pudo conectar a la base de datos, inténtelo más tarde");
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

    public static HashMap<String, Object> registrarActividad(Actividad actividad) {

        HashMap<String, Object> respuesta = new HashMap<>();

        respuesta.put("error", true);

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "INSERT INTO actividad (titulo, descripcion, idResponsable, idTipoActividad, fechaInicio, idEstadoActividad) VALUES (?, ?, ?, ?, ?, ?);";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);

                sentencia.setString(1, actividad.getTitulo());
                sentencia.setString(2, actividad.getDescripcion());
                sentencia.setInt(3, actividad.getIdResponsable());
                sentencia.setInt(4, actividad.getIdTipo());
                sentencia.setString(5, actividad.getFechaInicio());
                sentencia.setInt(6, actividad.getIdEstadoActividad());

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

}