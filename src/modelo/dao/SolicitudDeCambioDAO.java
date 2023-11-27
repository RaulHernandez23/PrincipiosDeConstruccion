
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.ConectorBaseDatos;
import modelo.pojo.EstadoSolicitud;
import modelo.pojo.SolicitudDeCambio;

public class SolicitudDeCambioDAO {
    
    public static ArrayList<SolicitudDeCambio> consultarSolicitudesCambio() {
        
        ArrayList<SolicitudDeCambio> solicitudes = new ArrayList<>();

        Connection conexionBD = ConectorBaseDatos.obtenerConexion();

        if (conexionBD != null) {
            try {
                String consulta = "SELECT Nombre, Descripcion, Razon, "
                        + "Impacto, Propuesta, IdEstadoSolicitud, IdEstudiante, FechaSolicitud "
                        + "FROM solicitudcambios ORDER BY FechaSolicitud DESC";
                PreparedStatement sentencia = conexionBD.prepareStatement(consulta);

                ResultSet resultadoConsulta = sentencia.executeQuery();

                while (resultadoConsulta.next()) {
                    SolicitudDeCambio solicitud = new SolicitudDeCambio();
                    solicitud.setNombre(resultadoConsulta.getString("Nombre"));
                    solicitud.setDescripcion(resultadoConsulta.getString("Descripcion"));
                    solicitud.setRazon(resultadoConsulta.getString("Razon"));
                    solicitud.setImpacto(resultadoConsulta.getString("Impacto"));
                    solicitud.setPropuesta(resultadoConsulta.getString("Propuesta"));
                    solicitud.setIdEstadoSolicitud(resultadoConsulta.getInt("IdEstadoSolicitud"));
                    solicitud.setIdEstudiante(resultadoConsulta.getInt("IdEstudiante"));
                    solicitud.setFechaCreacion(resultadoConsulta.getDate("FechaCreacion"));

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
    
    public static ArrayList<EstadoSolicitud> consultarEstadosSolicitud() {
        ArrayList<EstadoSolicitud> estadosSolicitud = new ArrayList<>();

        Connection conexionBD = ConectorBaseDatos.obtenerConexion();

        if (conexionBD != null) {
            try {
                String consulta = "SELECT IdEstadoSolicitud, Estado FROM estadosolicitud";
                PreparedStatement sentencia = conexionBD.prepareStatement(consulta);

                ResultSet resultadoConsulta = sentencia.executeQuery();

                while (resultadoConsulta.next()) {
                    EstadoSolicitud estado = new EstadoSolicitud();
                    estado.setIdEstadoSolicitud(resultadoConsulta.getInt("IdEstadoSolicitud"));
                    estado.setEstado(resultadoConsulta.getString("Estado"));

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
    
}
