package utilidades;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Utilidades {

    public static URL getURL(String url) {
        return Utilidades.class.getResource(".."+ url);
    }

    public static FXMLLoader getFXMLLoader(String fxml) {
        return new FXMLLoader(Utilidades.getURL(fxml));
    }

    public static String getURLString(String url) {
        return Utilidades.getURL(url).toExternalForm();
    }

    public static InputStream getInputStream(String url) {
        return Utilidades.class.getResourceAsStream(url);
    }

    public static void inicializarVentana(Stage escenario,
            String fxml,
            String css,
            String titulo,
            boolean esperar) {

        try {

            FXMLLoader fxmlLoader = Utilidades.getFXMLLoader(fxml);
            Parent vista = fxmlLoader.load();
            Scene escena = new Scene(vista);

            escena.getStylesheets().add(Utilidades.getURLString(css));
            escenario.setScene(escena);
            escenario.setTitle(titulo);
            escenario.setResizable(false);

            if (esperar) {
                escenario.showAndWait();
            } else {
                escenario.show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
