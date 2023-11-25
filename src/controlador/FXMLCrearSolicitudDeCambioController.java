
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLCrearSolicitudDeCambioController implements Initializable {

    @FXML
    private TextArea taAccionPropuesta;
    @FXML
    private TextArea taImpactoCambio;
    @FXML
    private TextArea taRazonCambio;
    @FXML
    private TextArea taDescripcionCambio;
    @FXML
    private TextField tfNombreSolicitud;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnVolver(ActionEvent event) {
    }

    @FXML
    private void btnEnviarSolicitud(ActionEvent event) {
    }
    
}
