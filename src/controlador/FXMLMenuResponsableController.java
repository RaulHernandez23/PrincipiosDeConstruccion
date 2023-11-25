package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import utilidades.Utilidades;

public class FXMLMenuResponsableController implements Initializable {

    @FXML
    private ImageView ivSalir;

    @FXML
    private VBox vboxMenuResponsable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void btnSalir(MouseEvent event) {

        Stage escenario = (Stage) vboxMenuResponsable.getScene().getWindow();

        Utilidades.inicializarVentana(escenario,
                "/vista/FXMLInicioSesion.fxml",
                "/vista/estilos/escenaInicioSesion.css",
                "Inicio de Sesión", false);

    }

    @FXML
    void hoverInSalir(MouseEvent event) {
        ivSalir.setImage(new Image(Utilidades.getInputStream("/recursos/imagenes/logoSalir2.png")));
    }

    @FXML
    void hoverOutSalir(MouseEvent event) {
        ivSalir.setImage(new Image(Utilidades.getInputStream("/recursos/imagenes/logoSalir.png")));
    }

    @FXML
    private void btnRegistrarActividad(MouseEvent event) {
    }

    @FXML
    private void btnEliminarActividad(MouseEvent event) {
    }

    @FXML
    private void btnReasignarActividad(MouseEvent event) {

        Stage escenario = new Stage();

        Utilidades.inicializarVentana(escenario,
                "/vista/FXMLReasignarActividad.fxml",
                "/vista/estilos/escenaTabla.css",
                "Reasignar Actividad", true);

    }

    @FXML
    private void btnAsignarEstudianteP(MouseEvent event) {
    }

    @FXML
    private void btnAsignarEstudianteA(MouseEvent event) {
    }

    @FXML
    private void btnDesasignarEstudianteP(MouseEvent event) {
    }

    @FXML
    private void btnConsultarDefectos(MouseEvent event) {
    }

    @FXML
    private void btnConsultarSolicitudes(MouseEvent event) {
    }

    @FXML
    private void btnConsultarBitacoras(MouseEvent event) {
    }

    @FXML
    private void btnVerMas(MouseEvent event) {
    }

}