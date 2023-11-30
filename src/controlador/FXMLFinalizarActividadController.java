package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utilidades.Utilidades;

public class FXMLFinalizarActividadController {
    @FXML
    private TableView<?> tvActividades;

    @FXML
    private TableColumn<?, ?> colTitulo;

    @FXML
    private TableColumn<?, ?> colFechaInicio;

    @FXML
    private TableColumn<?, ?> colFechaFin;

    @FXML
    private TableColumn<?, ?> colEstado;

    @FXML
    private TableColumn<?, ?> colEsfuerzo;

    @FXML
    private Button btnFinalizar;

    @FXML
    private Button btnVolver;

    @FXML
    private ImageView ivSalir;

    @FXML
    private void btnFinalizar() {
        // TODO: Implement the logic for the "Finalizar" button
    }

    @FXML
    private void btnVolver() {
        salir();
    }

    @FXML
    private void btnSalir() {
        salir();
    }

    @FXML
    private void hoverInSalir() {
        ivSalir.setImage(new Image(Utilidades.getInputStream(
                "/recursos/imagenes/logoSalir2.png")));
    }

    @FXML
    private void hoverOutSalir() {
        ivSalir.setImage(new Image(Utilidades.getInputStream(
                "/recursos/imagenes/logoSalir.png")));
    }

    private void salir() {
        Stage escenario = (Stage) tvActividades.getScene().getWindow();

        escenario.close();
    }
}
