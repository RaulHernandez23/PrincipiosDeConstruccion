package modelo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import modelo.ConectorBaseDatos;
import modelo.pojo.ResponsableProyecto;
import modelo.pojo.RespuestaInicioSesion;

public class ResponsableProyectoDAO {

    public static RespuestaInicioSesion iniciarSesionResponsable(String numPersonal, String password) {

        RespuestaInicioSesion respuesta = new RespuestaInicioSesion();

        respuesta.setCorrecto(false);

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {

                PreparedStatement consulta = (PreparedStatement) conexion.prepareStatement(
                        "SELECT idResponsableProyecto, nombre, apellidoPaterno, apellidoMaterno, correo, telefono, numPersonal, password FROM ResponsableProyecto WHERE numPersonal = ? AND password = ?");

                consulta.setString(1, numPersonal);
                consulta.setString(2, password);

                ResultSet resultado = consulta.executeQuery();

                if (resultado.next()) {

                    ResponsableProyecto responsableProyecto = new ResponsableProyecto();

                    responsableProyecto.setIdResponsableProyecto(resultado.getInt("idResponsableProyecto"));
                    responsableProyecto.setNumPersonal(resultado.getString("numPersonal"));
                    responsableProyecto.setPassword(resultado.getString("password"));
                    responsableProyecto.setNombre(resultado.getString("nombre"));
                    responsableProyecto.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                    responsableProyecto.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                    responsableProyecto.setCorreo(resultado.getString("correo"));
                    responsableProyecto.setTelefono(resultado.getString("telefono"));
                    
                    if (numPersonal.equals(responsableProyecto.getNumPersonal()) && password.equals(responsableProyecto.getPassword())) {
                        respuesta.setCorrecto(true);
                        respuesta.setMensaje("Inicio de sesión correcto");
                        respuesta.setResponsableProyecto(responsableProyecto);
                    } else {
                        respuesta.setMensaje("El número de personal y/o la contraseña son incorrectos");
                    }

                } else {
                    respuesta.setMensaje("El número de personal y/o la contraseña son incorrectos");
                }

            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }

        } else {
            respuesta.setMensaje("No se pudo conectar a la base de datos, por favor intente más tarde");
        }

        return respuesta;

    }

}
