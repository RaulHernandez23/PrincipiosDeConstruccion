/*
 * Nombre del archivo: Alertas.java
 * Autor: Miguel Angel Morales Cruz
 * Paquete: utilidades
 * Fecha de creación: 25/11/2023
 * Fecha de modificación: 5/12/2023
 * Descripción: Clase para mostrar alertas.
 */

package utilidades;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Alertas {

    public static void mostrarAlerta(String titulo, String mensaje,
            Alert.AlertType tipo) {

        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }

    public static boolean mostrarAlertaConfirmacion(String titulo,
            String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(null);
        return alerta.showAndWait().get() == ButtonType.OK;

    }
}
