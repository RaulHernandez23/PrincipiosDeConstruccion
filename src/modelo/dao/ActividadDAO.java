package modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;

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
                
                String consulta = "SELECT IdActividad, Titulo, " +
                    "a.IdEstudiante, CONCAT(e.Nombre, ' ', " +
                    "e.ApellidoPaterno, ' ', e.ApellidoMaterno) AS" +
                    " Estudiante, FechaInicio FROM actividad a INNER JOIN" +
                    " estudiante e ON a.IdEstudiante = e.IdEstudiante " +
                    "WHERE FechaFin IS NULL ORDER BY FechaInicio ASC;";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);

                ResultSet resultadoConsulta = sentencia.executeQuery();

                while (resultadoConsulta.next()) {
                    
                    Actividad actividad = new Actividad();
                    actividad.setIdActividad(resultadoConsulta.getInt("IdActividad"));
                    actividad.setTitulo(resultadoConsulta.getString("Titulo"));
                    actividad.setIdEstudiante(resultadoConsulta.getInt("IdEstudiante"));
                    actividad.setEstudiante(resultadoConsulta.getString("Estudiante"));
                    actividad.setFechaInicio(resultadoConsulta.getDate("FechaInicio"));

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
}