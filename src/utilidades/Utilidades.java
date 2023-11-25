package utilidades;

import java.io.InputStream;
import java.net.URL;

import javafx.fxml.FXMLLoader;

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

}
