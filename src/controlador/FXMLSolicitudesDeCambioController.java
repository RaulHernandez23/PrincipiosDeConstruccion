/*
 * Nombre del archivo: FXMLSolicitudesDeCambioController.java
 * Autor: Miguel Angel Morales Cruz
 * Paquete: controlador
 * Fecha de creación: 20/11/2023
 * Fecha de modificación: 11/12/2023
 * Descripción: Controlador para la ventana de Solicitudes de cambio.
 */
package controlador;

import interfaces.ObservadorSolicitudesDeCambio;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.dao.SolicitudDeCambioDAO;
import modelo.pojo.SolicitudDeCambio;
import utilidades.Utilidades;

public class FXMLSolicitudesDeCambioController implements Initializable,
        ObservadorSolicitudesDeCambio {

    @FXML
    private TableView<SolicitudDeCambio> tvSolicitudesDeCambio;

    @FXML
    private TableColumn colNombreSolicitud;

    @FXML
    private TableColumn colNombreAlumno;

    @FXML
    private TableColumn colFechaRegistro;

    @FXML
    private Button clicVer;

    @FXML
    private ImageView ivSalir;

    private Integer idResponsable;

    private ObservableList<SolicitudDeCambio> solicitudes;

    private SolicitudDeCambio solicitudSeleccionada;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        clicVer.setDisable(true);
        configurarListenerATabla();
    }

    @Override
    public void operacionExitosa(String tipoOperacion, String nombre) {

        System.out.println("Solicitud Evaluada: " + tipoOperacion + nombre);
        obtenerInformacionSolicitudes();

    }

    @FXML
    private void clicVer(ActionEvent event) {

        solicitudSeleccionada = tvSolicitudesDeCambio.getSelectionModel()
                .getSelectedItem();
        consultarSolicitud(idResponsable, solicitudSeleccionada);

    }

    @FXML
    private void hoverOutSalir(MouseEvent event) {
        ivSalir.setImage(new Image(Utilidades.getInputStream(
                "/recursos/imagenes/logoSalir.png")));
    }

    @FXML
    private void hoverInSalir(MouseEvent event) {
        ivSalir.setImage(new Image(Utilidades.getInputStream(
                "/recursos/imagenes/logoSalir2.png")));
    }

    @FXML
    private void clicSalir(MouseEvent event) {
        cerrarVentana();
    }

    public void inicializarVentana(Integer idResponsable) {
        this.idResponsable = idResponsable;
    }

    private void configurarTabla() {

        obtenerInformacionSolicitudes();
        this.colNombreSolicitud
                .setCellValueFactory(new PropertyValueFactory(
                        "titulo"));
        this.colNombreAlumno
                .setCellValueFactory(new PropertyValueFactory(
                        "estudiante"));
        this.colFechaRegistro
                .setCellValueFactory(new PropertyValueFactory(
                        "fechaCreacion"));

    }

    private void obtenerInformacionSolicitudes() {

        Integer idProyecto = 1;

        HashMap<String, Object> respuesta = SolicitudDeCambioDAO
                .consultarSolicitudesPendientes(idProyecto);

        if (!(Boolean) respuesta.get("error")) {

            solicitudes = FXCollections.observableArrayList();

            ArrayList<SolicitudDeCambio> lista;
            lista = (ArrayList<SolicitudDeCambio>) respuesta
                    .get("solicitudes");

            solicitudes.addAll(lista);
            tvSolicitudesDeCambio.setItems(solicitudes);

        } else {
            Utilidades.mostrarAlertaSimple("Error de carga", ""
                    + respuesta.get("mensaje"), Alert.AlertType.ERROR);
        }

    }

    private void cerrarVentana() {

        Stage escenario = (Stage) tvSolicitudesDeCambio.getScene().getWindow();

        escenario.close();

    }

    private void consultarSolicitud(Integer idResponsable,
            SolicitudDeCambio solicitud) {

        Stage escenario = new Stage();

        try {

            FXMLLoader loader = Utilidades
                    .getFXMLLoader("/vista/FXMLSolicitudDeCambio.fxml");

            Parent vista = loader.load();

            Scene escena = new Scene(vista);

            FXMLSolicitudDeCambioController controlador = loader
                    .getController();

            controlador.inicializarVentana(idResponsable, solicitud, this);
            escena.getStylesheets().add(Utilidades.getURLString(
                    "/vista/estilos/escenaFormulario.css"));

            escenario.setScene(escena);
            escenario.setTitle("Consultar solicitud");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void configurarListenerATabla() {

        tvSolicitudesDeCambio.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Object>() {

                    @Override
                    public void changed(ObservableValue<?> observable,
                            Object oldValue, Object newValue) {

                        if (newValue != null) {
                            clicVer.setDisable(false);
                        } else {
                            clicVer.setDisable(true);
                        }

                    }

                });

    }

}
