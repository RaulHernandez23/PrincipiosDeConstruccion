package modelo.dao;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelo.ConectorBaseDatos;
import modelo.pojo.Defecto;

public class DefectoDAO {
    public static ArrayList<Defecto> consultarDefectos(){
        
        ArrayList<Defecto> defectos = new ArrayList<Defecto>();

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {
            
            try {
                String consulta = "SELECT IdDefecto, Titulo, Descripcion, " + 
                    "EsfuerzoMinutos, FechaReporte, d.IdEstadoActividad, " + 
                    "d.IdEstudiante, ea.Estado AS EstadoActividad, " + 
                    "CONCAT(e.Nombre, ' ', e.ApellidoPaterno, ' ', e.ApellidoMaterno) AS Estudiantes " + 
                    "FROM defecto d INNER JOIN estadoactividad ea " +
                    "ON ea.IdEstadoActividad = d.IdEstadoActividad INNER JOIN " +
                    "estudiante e ON d.IdEstudiante = e.IdEstudiante " + 
                    "ORDER BY FechaReporte ASC, Estudiantes ASC;";

                PreparedStatement sentencia = conexion.prepareStatement(consulta);

                ResultSet resultado = sentencia.executeQuery();

                while (resultado.next()) {

                    Defecto defecto = new Defecto();
                    defecto.setIdDefecto(resultado.getInt("IdDefecto"));
                    defecto.setTitulo(resultado.getString("Titulo"));
                    defecto.setDescripcion(resultado.getString("Descripcion"));
                    defecto.setEsfuerzoMinutos(resultado.getString("EsfuerzoMinutos"));
                    defecto.setFechaReporte(resultado.getDate("FechaReporte"));
                    defecto.setIdEstadoActividad(resultado.getInt("IdEstadoActividad"));
                    defecto.setIdEstudiante(resultado.getInt("IdEstudiante"));
                    defecto.setEstadoActividad(resultado.getString("EstadoActividad"));
                    defecto.setNombreEstudiante(resultado.getString("Estudiantes"));

                    defectos.add(defecto);

                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally{
                ConectorBaseDatos.cerrarConexion(conexion);
            }

        }

        return defectos;
        
    }
}
