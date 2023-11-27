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
    
    public static HashMap<String, Object> registrarEstudiante(Estudiante estudiante) {
        
        HashMap<String, Object> respuesta = new HashMap<>();
        
        respuesta.put("error", true);
        
        Connection conexionBD = ConectorBaseDatos.obtenerConexion();

        if (conexionBD != null) {
            
            try {
                String sentencia = "INSERT INTO estudiante(Matricula, Nombre, "
                        + "ApellidoPaterno, ApellidoMaterno, "
                        + "IdEstadoEstudiante, Contraseña) "
                        + "values (?,?,?,?,?,?)";
                
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                
                prepararSentencia.setString(1, estudiante.getMatricula());
                prepararSentencia.setString(2, estudiante.getNombre());
                prepararSentencia.setString(3, estudiante.getApellidoPaterno());
                prepararSentencia.setString(4, estudiante.getApellidoMaterno());
                prepararSentencia.setInt(5, estudiante.getIdEstadoEstudiante());
                prepararSentencia.setString(6, estudiante.getPassword());

                int filasAfectadas = prepararSentencia.executeUpdate();

                if (filasAfectadas > 0) {
                    respuesta.put("error", false);
                    respuesta.put("mensaje", 
                            "Estudiante agregado correctamente");
                } else {
                    respuesta.put("mensaje", 
                            "Hubo un error al intentar registrar la información del estudiante, "
                                    + "por favor inténtelo más tarde");
                }
            } catch (SQLException e) {
                respuesta.put("mensaje", "Error de conexion en la base de datos");
                e.printStackTrace();
            }finally{
                ConectorBaseDatos.cerrarConexion(conexionBD);
            }
        } else {
            respuesta.put("mensaje", "Error de conexion en la base de datos, "
                    + "por favor inténtelo más tarde");
        }

        return respuesta;
    }
}
