package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utilidades.Utilidades;

public class FXMLEliminarActividadController {

    @FXML
    private ComboBox<String> cbActividades;

    @FXML
    private DatePicker dpFechaInicio;

    @FXML
    private DatePicker dpFechaFin;

    @FXML
    private TextArea taDescripcion;

    @FXML
    private TextField tfEstado;

    @FXML
    private TextField tfEsfuerzo;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnVolver;

    @FXML
    private Label lblSGBP;

    @FXML
    private ImageView ivLogoUV;

    @FXML
    private Label lblUniversidadVeracruzana;

    @FXML
    private ImageView ivSalir;

    @FXML
    private Label lblEliminarActividad;

    @FXML
    private Label lblDerechosReservados;

    @FXML
    private void btnEliminar(ActionEvent event) {
        // TODO: Implement the logic for deleting an activity
    }

    @FXML
    private void btnVolver(ActionEvent event) {
        salir();
    }

    @FXML
    private void btnSalir(MouseEvent event) {
        salir();
    }

    @FXML
    private void hoverInSalir(MouseEvent event) {
        ivSalir.setImage(new Image(Utilidades.getInputStream(
                "/recursos/imagenes/logoSalir2.png")));
    }

    @FXML
    private void hoverOutSalir(MouseEvent event) {
        ivSalir.setImage(new Image(Utilidades.getInputStream(
                "/recursos/imagenes/logoSalir.png")));
    }

    public void salir() {

        Stage escenario = (Stage) cbActividades.getScene().getWindow();

        escenario.close();

    }

}
