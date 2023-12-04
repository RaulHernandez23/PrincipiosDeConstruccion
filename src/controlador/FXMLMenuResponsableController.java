package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.pojo.ResponsableProyecto;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import utilidades.Utilidades;

public class FXMLMenuResponsableController implements Initializable {

    @FXML
    private ImageView ivSalir;

    @FXML
    private VBox vboxMenuResponsable;

    @FXML
    private Label encabezadoResponsable;

    private ResponsableProyecto responsable;

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

        Stage escenario = new Stage();

        try {

            FXMLLoader fxmlLoader = Utilidades.getFXMLLoader("/vista/FXMLRegistrarActividad.fxml");
            Parent vista = fxmlLoader.load();
            Scene escena = new Scene(vista);
            FXMLRegistrarActividadController controlador = fxmlLoader.getController();

            escena.getStylesheets().add(Utilidades.getURLString("/vista/estilos/escenaFormulario.css"));
            controlador.inicializarVentana(responsable.getIdResponsableProyecto(), responsable.getNombre());
            escenario.setScene(escena);
            escenario.setTitle("Registrar Actividad");
            escenario.setResizable(false);
            escenario.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnEliminarActividad(MouseEvent event) {

        try {

            FXMLLoader loader = Utilidades.getFXMLLoader("/vista/FXMLEliminarActividad.fxml");
            Parent vista = loader.load();
            Scene escena = new Scene(vista);
            FXMLEliminarActividadController controlador = loader.getController();
            controlador.inicializarInformacion(1);
            //me falta pasar el idResponsable
            escena.getStylesheets().add(Utilidades.getURLString("/vista/estilos/escenaFormulario.css"));

            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Eliminar Actividad");
            escenario.setResizable(false);
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
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

        Stage escenario = new Stage();

        Utilidades.inicializarVentana(escenario,
                "/vista/FXMLAsignarEstudianteAProyecto.fxml",
                "/vista/estilos/escenaFormulario.css",
                "Asignar estudiante a proyecto", true);

    }

    @FXML
    private void btnAsignarEstudianteA(MouseEvent event) {

        try {

            FXMLLoader loader = Utilidades.getFXMLLoader("/vista/FXMLAsignarActividad.fxml");
            Parent vista = loader.load();
            Scene escena = new Scene(vista);
            FXMLAsignarActividadController controlador = loader.getController();
            controlador.inicializarInformacion(1);
            escena.getStylesheets().add(Utilidades.getURLString("/vista/estilos/escenaFormulario.css"));

            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Asignar actividad");
            escenario.setResizable(false);
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnDesasignarEstudianteP(MouseEvent event) {

        Stage escenario = new Stage();

        Utilidades.inicializarVentana(escenario,
                "/vista/FXMLDesasignarEstudiante.fxml",
                "/vista/estilos/escenaFormulario.css",
                "Desasignar Estudiante", true);

    }

    @FXML
    private void btnConsultarDefectos(MouseEvent event) {
    }

    @FXML
    private void btnConsultarSolicitudes(MouseEvent event) {
        Stage escenario = new Stage();

        Utilidades.inicializarVentana(escenario,
                "/vista/FXMLSolicitudesDeCambio.fxml",
                null,
                "Solicitudes de cambio", true);
    }

    @FXML
    private void btnConsultarBitacoras(MouseEvent event) {
        Stage escenario = new Stage();

        Utilidades.inicializarVentana(escenario,
                "/vista/FXMLBitacorasEstudiante.fxml",
                "/vista/estilos/escenaTabla.css",
                "Bitácoras", true);
    }

    @FXML
    private void btnFinalizarActividad(MouseEvent event) {

        Stage escenario = new Stage();

        Utilidades.inicializarVentana(escenario,
                "/vista/FXMLFinalizarActividad.fxml",
                "/vista/estilos/escenaTabla.css",
                "Finalizar Actividad", true);

    }

    @FXML
    private void btnFinalizarCambio(MouseEvent event) {
    }

    @FXML
    private void btnVerMas(MouseEvent event) {

        Stage escenario = (Stage) vboxMenuResponsable.getScene().getWindow();

        try {

            FXMLLoader fxmlLoader = (escenario.getTitle().equals(
                    "Menu Responsable <<pagina 1>>")
                            ? Utilidades.getFXMLLoader(
                                    "/vista/FXMLMenuResponsableB.fxml")
                            : Utilidades.getFXMLLoader(
                                    "/vista/FXMLMenuResponsableA.fxml"));
            Parent vista = fxmlLoader.load();
            Scene escena = new Scene(vista);
            FXMLMenuResponsableController controlador = fxmlLoader.getController();

            escena.getStylesheets().add(Utilidades.getURLString(
                    "/vista/estilos/escenaMenu.css"));
            controlador.inicializarVentana(responsable);
            escenario.setScene(escena);
            escenario.setTitle(
                    (escenario.getTitle().equals(
                            "Menu Responsable <<pagina 1>>")
                                    ? "Menu Responsable <<pagina 2>>"
                                    : "Menu Responsable <<pagina 1>>"));
            escenario.setResizable(false);
            escenario.show();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public void inicializarVentana(ResponsableProyecto responsable) {
        this.responsable = responsable;
        String[] nombreInternacional = responsable.getNombre().split(" ");
        boolean dosNombres = nombreInternacional.length == 2;

        encabezadoResponsable.setText(
                responsable.getApellidoPaterno() + "-" + responsable.getApellidoMaterno() + " " + nombreInternacional[0]
                        + (dosNombres ? "-" + nombreInternacional[1] : ""));
    }

}