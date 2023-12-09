package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.pojo.Estudiante;
import utilidades.Alertas;
import utilidades.Utilidades;

public class FXMLMenuEstudianteController implements Initializable {

    @FXML
    private VBox vboxMenuEstudiante;

    @FXML
    private ImageView ivSalir;

    @FXML
    private Label encabezadoEstudiante;

    private Estudiante estudiante;

    private Integer idProyecto;

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
        ivSalir.setImage(new Image(Utilidades.getInputStream(
                "/recursos/imagenes/logoSalir2.png")));
    }

    @FXML
    void hoverOutSalir(MouseEvent event) {
        ivSalir.setImage(new Image(Utilidades.getInputStream(
                "/recursos/imagenes/logoSalir.png")));
    }

    @FXML
    private void btnConsultarBitacoras(MouseEvent event) {

        Stage escenario = new Stage();

        try {

            FXMLLoader fxmlLoader = Utilidades.getFXMLLoader(
                    "/vista/FXMLBitacorasEstudiante.fxml");
            Parent vista = fxmlLoader.load();
            Scene escena = new Scene(vista);
            FXMLBitacorasEstudianteController controlador = fxmlLoader.getController();

            escena.getStylesheets().add(Utilidades.getURLString(
                    "/vista/estilos/escenaBitacorasEstudiante.css"));
            controlador.inicializarVentana(idProyecto);
            escenario.setScene(escena);
            escenario.setTitle("Bitácoras");
            escenario.setResizable(false);
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();

        } catch (IOException e) {

            Alertas.mostrarAlerta("Error",
                    "Error al cargar la ventana",
                    AlertType.ERROR);
            escenario.close();

        }

    }

    @FXML
    private void btnCrearSolicitud(MouseEvent event) {
        
        Stage escenario = new Stage();
        
        try {

            FXMLLoader fxmlLoader = Utilidades.getFXMLLoader(
                    "/vista/FXMLCrearSolicitudDeCambio.fxml");
            Parent vista = fxmlLoader.load();
            Scene escena = new Scene(vista);
            FXMLCrearSolicitudDeCambioController controlador = fxmlLoader.getController();

            //escena.getStylesheets().add(Utilidades.getURLString(
            //        "/vista/estilos/escenaBitacorasEstudiante.css"));
            controlador.inicializarVentana(1,estudiante.getIdEstudiante());
            escenario.setScene(escena);
            escenario.setTitle("Crear solicitud de cambio");
            escenario.setResizable(false);
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();

        } catch (IOException e) {

            Alertas.mostrarAlerta("Error",
                    "Error al cargar la ventana",
                    AlertType.ERROR);
            escenario.close();

        }
    }

    @FXML
    private void btnRegistrarDefecto(MouseEvent event) {

        Stage escenario = new Stage();

        try {

            FXMLLoader loader = Utilidades.getFXMLLoader("/vista/FXMLRegistrarDefecto.fxml");
            Parent vista = loader.load();
            Scene escena = new Scene(vista);
            FXMLRegistrarDefectoController controlador = loader.getController();
            controlador.inicializarInformacion(1, estudiante.getIdEstudiante());
            escena.getStylesheets().add(Utilidades.getURLString(
                    "/vista/estilos/escenaFormulario.css"));

            escenario.setScene(escena);
            escenario.setTitle("Registrar Defecto");
            escenario.setResizable(false);
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();

        } catch (IOException ioE) {

            ioE.printStackTrace();
            escenario.close();

        }

    }

    @FXML
    private void btnRegistrarCambio(MouseEvent event) {

        Stage escenario = new Stage();

        try {

            FXMLLoader fxmlLoader = Utilidades.getFXMLLoader(
                    "/vista/FXMLRegistrarCambio.fxml");
            Parent vista = fxmlLoader.load();
            Scene escena = new Scene(vista);
            FXMLRegistrarCambioController controlador = fxmlLoader.getController();

            escena.getStylesheets().add(Utilidades.getURLString(
                    "/vista/estilos/escenaFormulario.css"));
            controlador.inicializarVentana(1, estudiante);
            escenario.setScene(escena);
            escenario.setTitle("Registrar Actividad");
            escenario.setResizable(false);
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();

        } catch (IOException e) {

            e.printStackTrace();
            escenario.close();

        }

    }

    public void inicializarVentana(Estudiante estudiante) {

        this.estudiante = estudiante;
        String[] nombreInternacional = estudiante.getNombre().split(" ");
        boolean dosNombres = nombreInternacional.length == 2;
        idProyecto = estudiante.getIdProyecto();

        encabezadoEstudiante.setText(
                estudiante.getApellidoPaterno() + "-" +
                        estudiante.getApellidoMaterno() + " " +
                        nombreInternacional[0] +
                        (dosNombres ? "-" + nombreInternacional[1] : ""));

    }

}