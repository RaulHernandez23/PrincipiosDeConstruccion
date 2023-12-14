/*
 * Nombre del archivo: Sgbp.java
 * Autor: Raul Hernandez Olivares
 * Paquete: Sgbp
 * Fecha de creación: 15/11/2023
 * Fecha de modificación: 9/12/2023
 * Descripción: Clase principal del proyecto
 */

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import utilidades.Utilidades;

public class Sgbp extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage escenario) throws Exception {

        cargarFuentes();

        Utilidades.inicializarVentana(
                escenario,
                "/vista/FXMLInicioSesion.fxml",
                "/vista/estilos/escenaInicioSesion.css",
                "Sistema Gestor de Bitácoras de Proyecto",
                false);

    }

    private void cargarFuentes() {

        Font.loadFont(Utilidades.getInputStream(
                "/recursos/fuentes/Poppins-Regular.ttf"), 12);
        Font.loadFont(Utilidades.getInputStream(
                "/recursos/fuentes/Poppins-Italic.ttf"), 12);
        Font.loadFont(Utilidades.getInputStream(
                "/recursos/fuentes/Poppins-Bold.ttf"), 12);

    }

}
