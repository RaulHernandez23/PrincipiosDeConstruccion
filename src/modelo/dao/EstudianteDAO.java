package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.ConectorBaseDatos;
import modelo.pojo.Estudiante;
import modelo.pojo.RespuestaInicioSesion;

public class EstudianteDAO {

    public static RespuestaInicioSesion iniciarSesionEstudiante(String matricula, String password) {

        RespuestaInicioSesion respuesta = new RespuestaInicioSesion();

        respuesta.setCorrecto(false);

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                PreparedStatement consulta = (PreparedStatement) conexion.prepareStatement(
                        "SELECT idEstudiante, nombre, apellidoPaterno, apellidoMaterno, e.idEstadoEstudiante, s.estado FROM estudiante e INNER JOIN estadoestudiante s ON e.idEstadoEstudiante = s.idEstadoEstudiante WHERE matricula = ? AND contraseña = ?");

                consulta.setString(1, matricula);
                consulta.setString(2, password);

                ResultSet resultado = consulta.executeQuery();

                if (resultado.next()) {

                    Estudiante estudiante = new Estudiante();

                    estudiante.setIdEstudiante(resultado.getInt("idEstudiante"));
                    estudiante.setMatricula(matricula);
                    estudiante.setNombre(resultado.getString("nombre"));
                    estudiante.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                    estudiante.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                    estudiante.setIdEstadoEstudiante(resultado.getInt("idEstadoEstudiante"));
                    estudiante.setEstadoEstudiante(resultado.getString("estado"));
                    respuesta.setCorrecto(true);
                    respuesta.setMensaje("Inicio de sesión correcto");
                    respuesta.setEstudiante(estudiante);

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

    public static ArrayList<Estudiante> consultarListaEstudiante() {

        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                String consulta = "SELECT IdEstudiante, Nombre, " +
                    " ApellidoPaterno, ApellidoMaterno FROM estudiante ORDER" +
                    " BY Nombre ASC";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);

                ResultSet resultadoConsulta = sentencia.executeQuery();

                while (resultadoConsulta.next()) {

                    Estudiante estudiante = new Estudiante();
                    estudiante.setIdEstudiante(resultadoConsulta.getInt
                        ("IdEstudiante"));
                    estudiante.setNombre(resultadoConsulta.getString
                        ("Nombre"));
                    estudiante.setApellidoPaterno(resultadoConsulta.getString
                        ("ApellidoPaterno"));
                    estudiante.setApellidoMaterno(resultadoConsulta.getString
                        ("ApellidoMaterno"));

                    estudiantes.add(estudiante);

                }

            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }
        }

        return estudiantes;

    }
}
