package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utilidades.Utilidades;

public class FXMLBitacorasEstudianteController {

    @FXML
    private VBox vboxBitacorasEstudiante;
    @FXML
    private ImageView ivSalir;
    @FXML
    private ComboBox<?> cbEstudiante;
    @FXML
    private ComboBox<?> cbBitacora;
    @FXML
    private TableView<?> tvBitacoras;

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
        salir();
    }

    @FXML
    private void btnVolver(ActionEvent event) {
        salir();
    }

    private void salir() {

        Stage escenario = (Stage) vboxBitacorasEstudiante.getScene().getWindow();

        escenario.close();

    }

}
