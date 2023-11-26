package utilidades;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Utilidades {

    public static URL getURL(String url) {
        return Utilidades.class.getResource(".." + url);
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
            
            // Se implemento null mientras el css no este completo
            if (css != null) {
                escena.getStylesheets().add(Utilidades.getURLString(css));
            }
            
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
    
    public static void mostrarAlertaSimple(String titulo, String mensaje, AlertType tipo){
        Alert alertaSimple = new Alert(tipo);
        alertaSimple.setTitle(titulo);
        alertaSimple.setContentText(mensaje);
        alertaSimple.setHeaderText(null);
        alertaSimple.showAndWait();
        
    }
    
    public static boolean mostrarAlertaConfirmacion(String titulo, String mensaje){
        Alert alertaConfirmacion = new Alert(AlertType.CONFIRMATION);
        alertaConfirmacion.setTitle(titulo);
        alertaConfirmacion.setContentText(mensaje);
        alertaConfirmacion.setHeaderText(null);
        
        Optional<ButtonType> botonClic = alertaConfirmacion.showAndWait();
        return (botonClic.get() == ButtonType.OK);
        
    }

}
