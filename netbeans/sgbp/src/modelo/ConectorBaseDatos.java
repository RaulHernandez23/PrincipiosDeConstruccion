/*
 * Nombre del archivo: ConectorBaseDatos.java
 * Autor: Albhieri Cristoff Villa Contreras
 * Paquete: modelo
 * Fecha de creación: 03/12/2023
 * Fecha de modificación: 14/12/2023
 * Descripción: Clase que contiene la conexión a la base de datos
 */

package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.IOException;

import utilidades.Utilidades;

public class ConectorBaseDatos {

    private static String driver;
    private static String nombreBD;
    private static String hostname;
    private static String puerto;
    private static String[] usuarios;
    private static String[] passwords;
    private static int usuarioActual = 0;
    private static Properties propiedades = new Properties();

    static {

        try {

            propiedades.load(Utilidades.getInputStream(
                    "/utilidades/configuracion.properties"));

            driver = propiedades.getProperty("driver");
            nombreBD = propiedades.getProperty("nombreBD");
            hostname = propiedades.getProperty("hostname");
            puerto = propiedades.getProperty("puerto");
            usuarios = propiedades.getProperty("usuarios")
                    .split(",");
            passwords = propiedades.getProperty("passwords")
                    .split(",");

            propiedades.clear();

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {

            if (propiedades != null && !propiedades.isEmpty()) {
                propiedades.clear();
            }

        }
    }

    public static final String URL_CONEXION = "jdbc:mysql://" + hostname 
            + ":" + puerto + "/"
            + nombreBD + "?allowPublicKeyRetrieval=true&useSSL=false";

    public static Connection obtenerConexion() {

        Connection conexion = null;

        try {

            Class.forName(driver);

            conexion = java.sql.DriverManager.getConnection(
                    URL_CONEXION, usuarios[usuarioActual],
                    passwords[usuarioActual]);

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return conexion;

    }

    public static void cerrarConexion(Connection conexion) {

        if (conexion != null) {

            try {
                conexion.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }

    }

}