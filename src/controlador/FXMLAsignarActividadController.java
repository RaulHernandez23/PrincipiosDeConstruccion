package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utilidades.Utilidades;

public class FXMLAsignarActividadController {

    @FXML
    private TableView<String> tvActividades;

    @FXML
    private ImageView ivSalir;

    @FXML
    private TableColumn<String, String> colTitulo;

    @FXML
    private TableColumn<String, String> colDescripcion;

    @FXML
    private ComboBox<String> cbEstudiantes;

    @FXML
    private Button btnAsignar;

    @FXML
    private Button btnVolver;

    @FXML
    private void btnAsignar() {

    }

    @FXML
    private void btnVolver() {
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

        Stage escenario = (Stage) tvActividades.getScene().getWindow();

        escenario.close();

    }

}
