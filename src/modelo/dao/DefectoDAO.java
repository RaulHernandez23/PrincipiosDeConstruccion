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

                String consulta = "INSERT INTO defecto "
                        + "(titulo, "
                        + "descripcion, "
                        + "fechaReporte, "
                        + "idEstadoDefecto, "
                        + "idEstudiante, "
                        + "idProyecto) "
                        + "VALUES (?, ?, NOW(), 2, ?, ?);";

                PreparedStatement sentencia = conexionBD.prepareStatement(consulta);
                sentencia.setString(1, defecto.getTitulo());
                sentencia.setString(2, defecto.getDescripcion());
                sentencia.setInt(3, defecto.getIdEstudiante());
                sentencia.setInt(4, defecto.getIdProyecto());
                int filasAfectadas = sentencia.executeUpdate();
                conexionBD.close();
                
                if (filasAfectadas > 0) {

                    respuesta.put("error", false);
                    respuesta.put("mensaje", "Defecto registrado exitosamente");

                } else {
                    respuesta.put("mensaje", "No se pudo registrar el defecto");
                }

            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
                respuesta.put("mensaje", "Error al registrar el defecto");
            }
        } else {
            respuesta.put("mensaje", "No se pudo conectar a la base de datos, intentélo más tarde");
        }

        return respuesta;
    }

    public static ArrayList<Defecto> consultarDefectos() {

        ArrayList<Defecto> defectos = new ArrayList<Defecto>();

        Connection conexion = ConectorBaseDatos.obtenerConexion();

        if (conexion != null) {

            try {
                String consulta = "SELECT idDefecto, titulo, descripcion, " +
                "esfuerzoMinutos, fechaReporte, fechaFin, d.idEstadoDefecto, " +
                "d.idEstudiante, ed.estado AS estadoActividad, " +
                "CONCAT(e.nombre, ' ', e.apellidoPaterno, ' ', " +
                "e.apellidoMaterno) AS estudiante FROM defecto d INNER JOIN " +
                "estadodefecto ed ON ed.idEstadoDefecto = d.idEstadoDefecto " +
                "INNER JOIN estudiante e ON d.idEstudiante = e.idEstudiante " +
                "ORDER BY fechaReporte ASC, estudiante ASC";

                PreparedStatement sentencia = conexion.prepareStatement(consulta);

                ResultSet resultado = sentencia.executeQuery();

                while (resultado.next()) {

                    Defecto defecto = new Defecto();
                    defecto.setIdDefecto(resultado.getInt("idDefecto"));
                    defecto.setTitulo(resultado.getString("titulo"));
                    defecto.setDescripcion(resultado.getString("descripcion"));
                    defecto.setEsfuerzoMinutos(resultado.getInt("esfuerzoMinutos"));
                    defecto.setFechaReporte(resultado.getString("fechaReporte"));
                    defecto.setFechaFin(resultado.getString("fechaFin"));
                    defecto.setIdEstadoDefecto(resultado.getInt("idEstadoDefecto"));
                    defecto.setIdEstudiante(resultado.getInt("idEstudiante"));
                    defecto.setEstadoDefecto(resultado.getString("estadoActividad"));
                    defecto.setNombreEstudiante(resultado.getString("estudiante"));

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

    public static HashMap<String, Object> consultarDefectosProyecto(Integer idProyecto) {

        HashMap<String, Object> respuesta = new HashMap<String, Object>();

        respuesta.put("error", true);

        Connection conexionBD = ConectorBaseDatos.obtenerConexion();

        if (conexionBD != null) {

            try {

                String consulta = "SELECT d.idDefecto, " +
                        "d.titulo, d.descripcion, " +
                        "d.esfuerzoMinutos, d.fechaReporte, " +
                        "d.idEstadoDefecto, " +
                        "ed.estado AS estadoDefecto, " +
                        "d.idEstudiante, " +
                        "CONCAT(e.nombre, ' ', e.apellidoPaterno, " +
                        "' ', e.apellidoMaterno) AS nombreEstudiante " +
                        "FROM defecto d INNER JOIN estudiante e " +
                        "ON d.idEstudiante = e.idEstudiante " +
                        "INNER JOIN estadodefecto ed " +
                        "ON d.idEstadoDefecto = ed.idEstadoDefecto " +
                        "WHERE d.idProyecto = ?";

                PreparedStatement sentencia = conexionBD.prepareStatement(consulta);
                sentencia.setInt(1, idProyecto);

                ResultSet resultadoConsulta = sentencia.executeQuery();

                ArrayList<Defecto> defectos = new ArrayList<Defecto>();

                while (resultadoConsulta.next()) {

                    Defecto defecto = new Defecto();
                    defecto.setIdDefecto(resultadoConsulta.getInt("idDefecto"));
                    defecto.setTitulo(resultadoConsulta.getString("titulo"));
                    defecto.setDescripcion(resultadoConsulta.getString("descripcion"));
                    defecto.setEsfuerzoMinutos(resultadoConsulta.getInt("esfuerzoMinutos"));
                    defecto.setFechaReporte(resultadoConsulta.getString("fechaReporte"));
                    defecto.setIdEstadoDefecto(resultadoConsulta.getInt("idEstadoDefecto"));
                    defecto.setIdEstudiante(resultadoConsulta.getInt("idEstudiante"));
                    defecto.setEstadoDefecto(resultadoConsulta.getString("estadoDefecto"));
                    defecto.setNombreEstudiante(resultadoConsulta.getString("nombreEstudiante"));

                    defectos.add(defecto);

                }

                respuesta.put("error", false);
                respuesta.put("defectos", defectos);

            } catch (SQLException se) {

                respuesta.put("mensaje", "Error" + se.getMessage());

            } finally {
                ConectorBaseDatos.cerrarConexion(conexionBD);
            }

        } else {
            respuesta.put("mensaje", "Error en la conexión con la base de datos");
        }

        return respuesta;

    }

}
