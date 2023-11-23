import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import utilidades.Utilidades;

public class SGBP extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage escenario) throws Exception {

        try {

            Parent vista = FXMLLoader.load(Utilidades.getURL("/vista/FXMLInicioSesion.fxml"));
            Scene escena = new Scene(vista);
            String css = Utilidades.getURLString("/vista/estilos/FXMLInicioSesion.css");

            Font.loadFont(Utilidades.getInputStream("/recursos/fuentes/Poppins-Regular.ttf"), 12);
            escena.getStylesheets().add(css);
            escenario.setScene(escena);
            escenario.setTitle("Sistema Gestor de Bitácoras de Proyecto");
            escenario.setResizable(false);
            escenario.show();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

}
