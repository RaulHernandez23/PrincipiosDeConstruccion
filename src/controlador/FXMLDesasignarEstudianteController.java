package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utilidades.Utilidades;

public class FXMLDesasignarEstudianteController {

    @FXML
    private VBox vboxDesasignarEstudiante;

    @FXML
    private ImageView ivSalir;

    @FXML
    private ComboBox<?> cbEstudiantes;

    @FXML
    private Label lbNombre;

    @FXML
    private Label lbApellidos;

    @FXML
    private Label lbMatricula;

    @FXML
    private Label lbSemestre;

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
        Stage escenario = (Stage) vboxDesasignarEstudiante.getScene().getWindow();
        escenario.close();
    }

    @FXML
    private void btnRegistrar(ActionEvent event) {
    }

}
