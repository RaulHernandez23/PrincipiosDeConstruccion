package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
        // TODO: Implement the logic for going back
    }

    @FXML
    private void btnSalir(MouseEvent event) {
        // TODO: Implement the logic for exiting the application
    }

    @FXML
    private void hoverInSalir(MouseEvent event) {
        // TODO: Implement the logic for hover in effect on the exit button
    }

    @FXML
    private void hoverOutSalir(MouseEvent event) {
        // TODO: Implement the logic for hover out effect on the exit button
    }
}
