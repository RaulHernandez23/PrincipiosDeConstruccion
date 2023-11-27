package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.pojo.Estudiante;
import utilidades.Utilidades;

public class FXMLMenuEstudianteController implements Initializable {

    @FXML
    private VBox vboxMenuEstudiante;

    @FXML
    private ImageView ivSalir;

    private Estudiante estudiante;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void btnSalir(MouseEvent event) {

        Stage escenario = (Stage) vboxMenuEstudiante.getScene().getWindow();

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
    private void btnConsultarBitacoras(MouseEvent event) {
    }

    @FXML
    private void btnCrearSolicitud(MouseEvent event) {
    }

    @FXML
    private void btnRegistrarDefecto(MouseEvent event) {
    }

    @FXML
    private void btnRegistrarCambio(MouseEvent event) {
        Stage escenario = new Stage();

        Utilidades.inicializarVentana(escenario,
                "/vista/FXMLRegistrarCambio.fxml",
                "/vista/estilos/escenaFormulario.css",
                "Registrar Cambio", true);

    }

    public void inicializarVentana(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

}