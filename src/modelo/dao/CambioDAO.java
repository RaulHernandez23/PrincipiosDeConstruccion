package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import modelo.ConectorBaseDatos;
import modelo.pojo.Cambio;

public class CambioDAO {
    public static ArrayList<Cambio> consultarCambios() {
        ArrayList<Cambio> cambios = new ArrayList<Cambio>();

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {
            
            try {
                
                String consulta = "SELECT * FROM cambio ORDER BY Nombre ASC";
                PreparedStatement sentencia = conexion.prepareStatement(consulta);

                ResultSet resultadoConsulta = sentencia.executeQuery();

                while (resultadoConsulta.next()) {
                    
                    Cambio cambio = new Cambio();
                    cambio.setIdCambio(resultadoConsulta.getInt("IdCambio"));
                    cambio.setNombre(resultadoConsulta.getString("Nombre"));
                    cambio.setDescripcion(resultadoConsulta.getString("Descripcion"));
                    cambio.setIdEstadoActividad(resultadoConsulta.getInt("IdEstadoActividad"));
                    cambio.setIdSolicitud(resultadoConsulta.getInt("IdSolicitud"));

                    cambios.add(cambio);
                    
                }

            } catch (SQLException se) {
                se.printStackTrace();
            } finally{
                ConectorBaseDatos.cerrarConexion(conexion);
            }

        }

        return cambios;
        
    }
}
