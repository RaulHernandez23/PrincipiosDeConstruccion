package utilidades;

import javafx.scene.control.Alert;

public class Alertas {

    public static void mostrarAlerta
            (String titulo, String mensaje, Alert.AlertType tipo) {

        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}
