package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.ConectorBaseDatos;
import modelo.pojo.Estudiante;
import modelo.pojo.RespuestaInicioSesion;
import utilidades.Constantes;

public class EstudianteDAO {

    public static RespuestaInicioSesion iniciarSesionEstudiante(String matricula, String password) {

        RespuestaInicioSesion respuesta = new RespuestaInicioSesion();

        respuesta.setCorrecto(false);

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                PreparedStatement consulta = (PreparedStatement) conexion.prepareStatement(
                        "SELECT idEstudiante, nombre, apellidoPaterno, apellidoMaterno, e.idEstadoEstudiante, s.estado, e.password FROM estudiante e INNER JOIN estadoestudiante s ON e.idEstadoEstudiante = s.idEstadoEstudiante WHERE matricula = ? AND password = ?");

                consulta.setString(1, matricula);
                consulta.setString(2, password);

                ResultSet resultado = consulta.executeQuery();

                if (resultado.next()) {

                    Estudiante estudiante = new Estudiante();

                    estudiante.setIdEstudiante(resultado.getInt("idEstudiante"));
                    estudiante.setMatricula(matricula);
                    estudiante.setPassword(resultado.getString("password"));
                    estudiante.setNombre(resultado.getString("nombre"));
                    estudiante.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                    estudiante.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                    estudiante.setIdEstadoEstudiante(resultado.getInt("idEstadoEstudiante"));
                    estudiante.setEstadoEstudiante(resultado.getString("estado"));

                    if (password.equals(estudiante.getPassword())) {
                        respuesta.setCorrecto(true);
                        respuesta.setMensaje("Inicio de sesión correcto");
                        respuesta.setEstudiante(estudiante);
                    } else {
                        respuesta.setMensaje("La matrícula y/o la contraseña son incorrectos");
                    }

                } else {
                    respuesta.setMensaje("La matrícula y/o la contraseña son incorrectos");
                }

            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }

        } else {
            respuesta.setMensaje("No se pudo conectar a la base de datos");
        }

        return respuesta;

    }

    public static HashMap<String, Object> consultarListaEstudiante() {
        HashMap<String, Object> respuesta = new HashMap<>();
        respuesta.put("error", true);

        ArrayList<HashMap<String, Object>> listaEstudiantes = new ArrayList<>();

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {
            try {
                String consulta = "SELECT idEstudiante, nombre, apellidoPaterno, apellidoMaterno FROM estudiante ORDER BY nombre ASC";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);

                ResultSet resultadoConsulta = sentencia.executeQuery();

                while (resultadoConsulta.next()) {
                    HashMap<String, Object> estudianteMap = new HashMap<>();
                    estudianteMap.put("idEstudiante", resultadoConsulta.getInt("idEstudiante"));
                    estudianteMap.put("nombre", resultadoConsulta.getString("nombre"));
                    estudianteMap.put("apellidoPaterno", resultadoConsulta.getString("apellidoPaterno"));
                    estudianteMap.put("apellidoMaterno", resultadoConsulta.getString("apellidoMaterno"));

                    listaEstudiantes.add(estudianteMap);
                }

                respuesta.put("error", false);
                respuesta.put("estudiantes", listaEstudiantes);

            } catch (SQLException se) {
                se.printStackTrace();
                respuesta.put("mensaje", "Error en la base de datos: " + se.getMessage());
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }
        } else {
            respuesta.put("mensaje", "No se pudo conectar a la base de datos, inténtelo más tarde");
        }

        return respuesta;
    }

    public static HashMap<String, Object> recuperarEstudiantesProyecto(int idProyecto) {

        HashMap<String, Object> respuesta = new HashMap<>();
        respuesta.put("error", true);
        Connection conexionBD = ConectorBaseDatos.obtenerConexion();

        if (conexionBD != null) {
            try {
                String sentencia = "SELECT "
                        + "idEstudiante, "
                        + "matricula, "
                        + "e. nombre, "
                        + "apellidoPaterno, "
                        + "apellidoMaterno, "
                        + "e.idEstadoEstudiante, "
                        + "ee.estado"
                        + "e.idProyecto, "
                        + "p.nombre AS nombreProyecto "
                        + "FROM estudiante e "
                        + "INNER JOIN estadoestudiante ee ON e.idEstadoEstudiante = ee.idEstadoEstudiante "
                        + "INNER JOIN proyecto p ON e.idProyecto = p.idProyecto "
                        + "WHERE e.idProyecto = ?;";

                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setInt(1, idProyecto);
                ResultSet resultadoConsulta = prepararSentencia.executeQuery();
                ArrayList<Estudiante> estudiantes = new ArrayList<>();

                while (resultadoConsulta.next()) {
                    Estudiante estudiante = new Estudiante();
                    estudiante.setIdEstudiante(resultadoConsulta.getInt("idEstudiante"));
                    estudiante.setMatricula(resultadoConsulta.getString("matricula"));
                    estudiante.setNombre(resultadoConsulta.getString("nombre"));
                    estudiante.setApellidoPaterno(resultadoConsulta.getString("apellidoPaterno"));
                    estudiante.setApellidoMaterno(resultadoConsulta.getString("apellidoMaterno"));
                    estudiante.setIdEstadoEstudiante(resultadoConsulta.getInt("idEstadoEstudiante"));
                    estudiante.setEstadoEstudiante(resultadoConsulta.getString("estado"));
                    estudiante.setIdProyecto(resultadoConsulta.getInt("idProyecto"));
                    estudiante.setNombreProyecto(resultadoConsulta.getString("nombreProyecto"));
                    estudiantes.add(estudiante);
                }

                conexionBD.close();
                respuesta.put("error", false);
                respuesta.put("estudiantes", estudiantes);

            } catch (Exception e) {
                respuesta.put("mensaje", "Error de conexion en la base de datos");
                e.printStackTrace();
            }

        } else {
            respuesta.put("mensaje", "Error de conexion en la base de datos, "
                    + "por favor inténtelo más tarde");
        }

        return respuesta;
    }

    public static HashMap<String, Object> recuperarEstudiantesActivosProyecto(int idProyecto) {

        HashMap<String, Object> respuesta = new HashMap<>();
        respuesta.put("error", true);
        Connection conexionBD = ConectorBaseDatos.obtenerConexion();

        if (conexionBD != null) {
            try {
                String sentencia = "SELECT "
                        + "idEstudiante, "
                        + "matricula, "
                        + "e.nombre, "
                        + "apellidoPaterno, "
                        + "apellidoMaterno, "
                        + "e.idEstadoEstudiante, "
                        + "ee.estado, "
                        + "e.idProyecto, "
                        + "p.nombre AS proyecto "
                        + "FROM estudiante e "
                        + "JOIN estadoestudiante ee ON e.idEstadoEstudiante = ee.idEstadoEstudiante "
                        + "JOIN proyecto p ON e.idProyecto = p.idProyecto "
                        + "WHERE e.idProyecto = ? AND e.idEstadoEstudiante = 1;";

                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setInt(1, idProyecto);
                ResultSet resultadoConsulta = prepararSentencia.executeQuery();
                ArrayList<Estudiante> estudiantes = new ArrayList<>();

                while (resultadoConsulta.next()) {

                    Estudiante estudiante = new Estudiante();
                    estudiante.setIdEstudiante(resultadoConsulta.getInt("idEstudiante"));
                    estudiante.setMatricula(resultadoConsulta.getString("matricula"));
                    estudiante.setNombre(resultadoConsulta.getString("nombre"));
                    estudiante.setApellidoPaterno(resultadoConsulta.getString("apellidoPaterno"));
                    estudiante.setApellidoMaterno(resultadoConsulta.getString("apellidoMaterno"));
                    estudiante.setIdEstadoEstudiante(resultadoConsulta.getInt("idEstadoEstudiante"));
                    estudiante.setEstadoEstudiante(resultadoConsulta.getString("estado"));
                    estudiante.setIdProyecto(resultadoConsulta.getInt("idProyecto"));
                    estudiante.setNombreProyecto(resultadoConsulta.getString("proyecto"));
                    estudiantes.add(estudiante);

                }

                respuesta.put("error", false);
                respuesta.put("estudiantes", estudiantes);

            } catch (Exception sqlE) {

                respuesta.put("mensaje", Constantes.MENSAJE_ERROR_SELECT);
                sqlE.printStackTrace();
                
            } finally {
                ConectorBaseDatos.cerrarConexion(conexionBD);
            }

        } else {
            respuesta.put("mensaje", Constantes.MENSAJE_ERROR_DE_CONEXION);
        }

        return respuesta;
    }

    public static HashMap<String, Object> consultarEstudiantesActivosProyecto(
            Integer idProyecto) {

        HashMap<String, Object> respuesta = new HashMap<>();

        respuesta.put("error", true);

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "WITH RankedPeriodos AS ( "
                        + "SELECT e.idEstudiante, pe.idPeriodoEscolar, "
                        + "ROW_NUMBER() OVER (PARTITION BY e.idEstudiante "
                        + "ORDER BY pe.fechaFin ASC) AS rn "
                        + "FROM estudiante e "
                        + "JOIN estudiante_periodoescolar ep ON "
                        + "e.idEstudiante = ep.idEstudiante "
                        + "JOIN periodoescolar pe ON "
                        + "ep.idPeriodoEscolar = pe.idPeriodoEscolar "
                        + "JOIN proyecto p ON e.idProyecto = p.idProyecto "
                        + "JOIN proyecto_periodoescolar pp ON "
                        + "p.idProyecto = pp.idProyecto "
                        + "WHERE p.idProyecto = ? "
                        + "AND e.idEstadoEstudiante = 1 "
                        + ") "
                        + "SELECT e.idEstudiante, e.nombre, "
                        + "e.apellidoPaterno, e.apellidoMaterno, e.matricula, "
                        + "e.idEstadoEstudiante, pe.nombre AS nombrePeriodoEscolar "
                        + "FROM estudiante e "
                        + "JOIN RankedPeriodos rp ON e.idEstudiante = rp.idEstudiante "
                        + "JOIN periodoescolar pe ON rp.idPeriodoEscolar = pe.idPeriodoEscolar "
                        + "WHERE rp.rn = 1 "
                        + "ORDER BY pe.fechaFin ASC";
                PreparedStatement sentencia = conexion.prepareStatement(
                        consulta);

                sentencia.setInt(1, idProyecto);

                ResultSet resultadoConsulta = sentencia.executeQuery();

                ArrayList<Estudiante> estudiantes = new ArrayList<>();

                while (resultadoConsulta.next()) {

                    Estudiante estudiante = new Estudiante();
                    estudiante.setIdEstudiante(resultadoConsulta.getInt(
                            "idEstudiante"));
                    estudiante.setNombre(resultadoConsulta.getString(
                            "nombre"));
                    estudiante.setApellidoPaterno(resultadoConsulta.getString(
                            "apellidoPaterno"));
                    estudiante.setApellidoMaterno(resultadoConsulta.getString(
                            "apellidoMaterno"));
                    estudiante.setMatricula(resultadoConsulta.getString(
                            "matricula"));
                    estudiante.setNombrePeriodoEscolar(
                            resultadoConsulta.getString(
                                    "nombrePeriodoEscolar"));

                    estudiantes.add(estudiante);

                }

                respuesta.put("error", false);
                respuesta.put("estudiantes", estudiantes);

            } catch (SQLException se) {
                respuesta.put("mensaje", "Error: " + se.getMessage());
                se.printStackTrace();
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }
        }

        return respuesta;
    }

    public static HashMap<String, Object> registrarEstudiante(Estudiante estudiante) {

        HashMap<String, Object> respuesta = new HashMap<>();
        respuesta.put("error", true);

        Connection conexionBD = ConectorBaseDatos.obtenerConexion();

        if (conexionBD != null) {

            try {

                String sentencia = "INSERT INTO estudiante( matricula, nombre, "
                        + "apellidoPaterno, apellidoMaterno, "
                        + "idEstadoEstudiante, password, idProyecto) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                        "ON DUPLICATE KEY UPDATE " +
                        "nombre = VALUES(nombre), " +
                        "apellidoPaterno = VALUES(apellidoPaterno), " +
                        "apellidoMaterno = VALUES(apellidoMaterno), " +
                        "idEstadoEstudiante = VALUES(idEstadoEstudiante), " +
                        "password = VALUES(password), " +
                        "idProyecto = VALUES(idProyecto)";

                PreparedStatement prepararSentencia = conexionBD
                        .prepareStatement(sentencia);

                prepararSentencia.setString(1, estudiante
                        .getMatricula());
                prepararSentencia.setString(2, estudiante
                        .getNombre());
                prepararSentencia.setString(3, estudiante
                        .getApellidoPaterno());
                prepararSentencia.setString(4, estudiante
                        .getApellidoMaterno());
                prepararSentencia.setInt(5, estudiante
                        .getIdEstadoEstudiante());
                prepararSentencia.setString(6, estudiante
                        .getPassword());
                prepararSentencia.setInt(7, estudiante
                        .getIdProyecto());

                int filasAfectadas = prepararSentencia.executeUpdate();

                if (filasAfectadas > 0) {

                    respuesta.put("error", false);
                    respuesta.put("mensaje", "Estudiante registrado"
                            + " anteriormente actualización y reasignación "
                            + "completa");

                } else {
                    respuesta.put("mensaje", Constantes.MENSAJE_ERROR_REGISTRO);
                }

            } catch (SQLException e) {
                respuesta.put("mensaje", Constantes.MENSAJE_ERROR_REGISTRO);
            } finally {
                ConectorBaseDatos.cerrarConexion(conexionBD);
            }

        } else {
            respuesta.put("mensaje", Constantes.MENSAJE_ERROR_DE_CONEXION);
        }

        return respuesta;
    }

    public static HashMap<String, Object> desasignarEstudiante(
            Integer idEstudiante) {

        HashMap<String, Object> respuesta = new HashMap<>();

        respuesta.put("error", true);

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "UPDATE estudiante SET idEstadoEstudiante = 2," +
                        "idProyecto = NULL WHERE idEstudiante = ?";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);

                sentencia.setInt(1, idEstudiante);

                int filasAfectadas = sentencia.executeUpdate();

                if (filasAfectadas > 0) {
                    respuesta.put("error", false);
                    respuesta.put("mensaje", "El estudiante fue desasignado con éxito");
                } else {
                    respuesta.put("mensaje", "No se pudo desasignar al estudiante");
                }

            } catch (SQLException se) {
                respuesta.put("mensaje", "Error: " + se.getMessage());
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }

            conexion = ConectorBaseDatos.obtenerConexion();

            try {

                String consulta = "DELETE FROM estudiante_periodoescolar WHERE idEstudiante = ?";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);

                sentencia.setInt(1, idEstudiante);

                int filasAfectadas = sentencia.executeUpdate();

                if (filasAfectadas > 0) {
                    respuesta.put("error", false);
                    respuesta.put("mensaje", "Estudiante desasignado correctamente");
                } else {
                    respuesta.put("mensaje", "No se pudo desasignar al estudiante");
                }

            } catch (SQLException se) {
                respuesta.put("mensaje", "Error: " + se.getMessage());
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }

        } else {
            respuesta.put("mensaje", "No se pudo conectar a la base de datos");
        }

        return respuesta;

    }
}
