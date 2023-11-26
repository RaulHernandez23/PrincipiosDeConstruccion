package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utilidades.Utilidades;

public class FXMLRegistrarCambioController implements Initializable {

    @FXML
    private VBox vboxRegistrarCambio;
    @FXML
    private ImageView ivSalir;
    @FXML
    private TextField tfTitulo;
    @FXML
    private TextArea tfDescripcion;
    @FXML
    private ComboBox<?> cbTipo;
    @FXML
    private TextField tfEsfuerzo;
    @FXML
    private ComboBox<?> cbEstado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void hoverOutSalir(MouseEvent event) {
        ivSalir.setImage(new Image(Utilidades.getInputStream("/recursos/imagenes/logoSalir.png")));
    }

    @FXML
    private void hoverInSalir(MouseEvent event) {
        ivSalir.setImage(new Image(Utilidades.getInputStream("/recursos/imagenes/logoSalir2.png")));
    }

    @FXML
    private void btnSalir(MouseEvent event) {
        Stage escenario = (Stage) vboxRegistrarCambio.getScene().getWindow();
        escenario.close();
    }

    @FXML
    private void btnRegistrar(ActionEvent event) {
    }

}
