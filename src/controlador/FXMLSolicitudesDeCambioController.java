
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class FXMLSolicitudesDeCambioController implements Initializable {

    @FXML
    private TableView<?> tvSolicitudesDeCambio;
    @FXML
    private TableColumn<?, ?> colNombreSolicitud;
    @FXML
    private TableColumn<?, ?> colNombreAlumno;
    @FXML
    private TableColumn<?, ?> colFechaRegistro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnVer(ActionEvent event) {
    }

    @FXML
    private void btnVolver(ActionEvent event) {
    }
    
}
