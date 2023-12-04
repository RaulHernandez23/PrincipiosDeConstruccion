package modelo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelo.ConectorBaseDatos;
import modelo.pojo.Defecto;

public class DefectoDAO {

    public static HashMap<String, Object> registrarDefecto(Defecto defecto) {

        HashMap<String, Object> respuesta = new LinkedHashMap<String, Object>();
        respuesta.put("error", true);
        Connection conexionBD = ConectorBaseDatos.obtenerConexion();

        if (conexionBD != null) {

            try {

                String sentencia = "INSERT INTO defecto "
                        + "(Titulo, "
                        + "Descripcion, "
                        + "FechaReporte, "
                        + "idEstadoDefecto, "
                        + "IdEstudiante) "
                        + "VALUES (?, ?, ?, ?, ?);";

                PreparedStatement consulta = conexionBD.prepareStatement(sentencia);
                consulta.setString(1, defecto.getTitulo());
                consulta.setString(2, defecto.getDescripcion());
                consulta.setString(3, defecto.getFechaReporte());
                consulta.setInt(4, defecto.getIdestadoDefecto());
                consulta.setInt(5, defecto.getIdEstudiante());
                int filasAfectadas = consulta.executeUpdate();

                if (filasAfectadas > 0) {

                    respuesta.put("error", false);
                    respuesta.put("mensaje", "Defecto registrado exitosamente");

                } else {
                    respuesta.put("mensaje", "No se pudo registrar el defecto");
                }

            } catch (SQLException sqlE) {

                sqlE.printStackTrace();
                respuesta.put("mensaje", "Error al registrar el defecto");

            } finally {
                ConectorBaseDatos.cerrarConexion(conexionBD);
            }

        } else {
            respuesta.put("mensaje", "Error en la conexión con la base de datos");
        }

        return respuesta;
    }

    public static ArrayList<Defecto> consultarDefectos() {

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
                    defecto.setFechaReporte(resultado.getString("FechaReporte"));
                    defecto.setIdestadoDefecto(resultado.getInt("IdEstadoActividad"));
                    defecto.setIdEstudiante(resultado.getInt("IdEstudiante"));
                    defecto.setEstadoDefecto(resultado.getString("EstadoActividad"));
                    defecto.setNombreEstudiante(resultado.getString("Estudiantes"));

                    defectos.add(defecto);

                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                ConectorBaseDatos.cerrarConexion(conexion);
            }

        }

        return defectos;

    }
}
