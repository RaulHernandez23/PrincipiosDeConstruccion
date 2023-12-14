/*
 * Nombre del archivo: FXMLCrearSolicitudDeCambioController.java
 * Autor: Miguel Angel Morales Cruz
 * Paquete: controlador
 * Fecha de creación: 20/11/2023
 * Fecha de modificación: 10/12/2023
 * Descripción: Controlador para la ventana de creación de 
 * solicitudes de cambio.
 */

package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.dao.DefectoDAO;
import modelo.dao.SolicitudDeCambioDAO;
import modelo.pojo.Defecto;
import modelo.pojo.SolicitudDeCambio;
import utilidades.Utilidades;

public class FXMLCrearSolicitudDeCambioController implements Initializable {

    @FXML
    private TextArea taAccionPropuesta;

    @FXML
    private TextArea taImpactoCambio;

    @FXML
    private TextArea taRazonCambio;

    @FXML
    private TextArea taDescripcionCambio;

    @FXML
    private TextField tfNombreSolicitud;

    @FXML
    private VBox vboxScrollPane;

    @FXML
    private ImageView ivSalir;

    @FXML
    private ComboBox<Defecto> cbDefectos;

    @FXML
    private Button btnEnviarSolicitud;

    @FXML
    private ScrollPane scrollPanePanelPrincipal;

    private int idProyecto;

    private int idEstudiante;

    private ObservableList<Defecto> defectos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnEnviarSolicitud.setDisable(true);
        configurarListenerACampos();

        Platform.runLater(() -> {
            scrollPanePanelPrincipal.setVvalue(0);
        });

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

    @FXML
    private void clicEnviarSolicitud(ActionEvent event) {

        if (validarCampos()) {

            boolean confirmacion = Utilidades.mostrarAlertaConfirmacion(
                    "Confirmar registro",
                    "¿Estás seguro de enviar la solicitud?");

            if (confirmacion) {

                registrarSolicitud();
                cerrarVentana();

            }

        } else {
            Utilidades.mostrarAlertaSimple("Advertencia",
                    "Ingrese datos válidos", 
                    Alert.AlertType.WARNING);

        }

    }

    public void inicializarVentana(int idProyecto, int idEstudiante) {

        this.idProyecto = idProyecto;
        this.idEstudiante = idEstudiante;

        cargarInformacionDefectos();

    }

    private void cerrarVentana() {

        Stage escenario = (Stage) tfNombreSolicitud.getScene().getWindow();

        escenario.close();

    }

    @FXML
    private void clicSalir(MouseEvent event) {
        cerrarVentana();
    }

    private void cargarInformacionDefectos() {

        HashMap respuesta = DefectoDAO.consultarNombresDefectosProyecto(
                idProyecto);

        ArrayList<Defecto> defectosDeProyecto = (ArrayList<Defecto>) respuesta
                .get("defectos");

        defectos = FXCollections.observableArrayList();

        defectos.addAll(defectosDeProyecto);

        Defecto ningunDefectoSeleccionado = new Defecto();

        ningunDefectoSeleccionado.setTitulo("Ningún defecto asociado");
        ningunDefectoSeleccionado.setIdDefecto(null);
        defectos.add(ningunDefectoSeleccionado);
        cbDefectos.setItems(defectos);
    }

    private void configurarListenerACampos() {

        ChangeListener<String> cambiosEnCampos = (
                observable,
                oldValue, newValue) -> verificarCampos();
        ChangeListener<Object> cambiosEnComboBox = (
                observable,
                oldValue, newValue) -> verificarCampos();

        cbDefectos.valueProperty().addListener(cambiosEnComboBox);
        tfNombreSolicitud.textProperty().addListener(cambiosEnCampos);
        taDescripcionCambio.textProperty().addListener(cambiosEnCampos);
        taRazonCambio.textProperty().addListener(cambiosEnCampos);
        taImpactoCambio.textProperty().addListener(cambiosEnCampos);
        taAccionPropuesta.textProperty().addListener(cambiosEnCampos);

    }

    private void verificarCampos() {

        btnEnviarSolicitud.setDisable(
                cbDefectos.getValue() == null
                        || tfNombreSolicitud.getText().isEmpty()
                        || taDescripcionCambio.getText().isEmpty()
                        || taRazonCambio.getText().isEmpty()
                        || taImpactoCambio.getText().isEmpty()
                        || taAccionPropuesta.getText().isEmpty());

    }

    private boolean validarCampos() {
        boolean esValido = true;

        String titulo = tfNombreSolicitud.getText().trim();
        if (titulo.isEmpty() || titulo.length() > 50) {
            esValido = false;
        }

        String descripcion = taDescripcionCambio.getText().trim();
        if (descripcion.isEmpty() || descripcion.length() > 500) {
            esValido = false;
        }

        String razonCambio = taRazonCambio.getText().trim();
        if (razonCambio.isEmpty() || razonCambio.length() > 500) {
            esValido = false;
        }

        String impactoCambio = taImpactoCambio.getText().trim();
        if (impactoCambio.isEmpty() || impactoCambio.length() > 500) {
            esValido = false;
        }

        String accionPropuesta = taAccionPropuesta.getText().trim();
        if (accionPropuesta.isEmpty() || accionPropuesta.length() > 500) {
            esValido = false;
        }

        if (cbDefectos.getValue() == null) {
            esValido = false;
        }

        return esValido;
    }

    private void registrarSolicitud() {

        SolicitudDeCambio solicitudDeCambio = new SolicitudDeCambio();

        Defecto defectoSeleccionado = cbDefectos.getSelectionModel()
                .getSelectedItem();

        solicitudDeCambio.setIdDefecto(defectoSeleccionado
                .getIdDefecto());
        solicitudDeCambio.setTitulo(tfNombreSolicitud.getText());
        solicitudDeCambio.setDescripcion(taDescripcionCambio
                .getText());
        solicitudDeCambio.setRazon(taRazonCambio.getText());
        solicitudDeCambio.setImpacto(taImpactoCambio.getText());
        solicitudDeCambio.setAccionPropuesta(taAccionPropuesta
                .getText());

        String fechaRegistro = Utilidades.obtenerFechaActual();

        solicitudDeCambio.setFechaCreacion(fechaRegistro);
        solicitudDeCambio.setIdEstadoSolicitud(3);
        solicitudDeCambio.setIdEstudiante(idEstudiante);
        solicitudDeCambio.setIdProyecto(idProyecto);

        HashMap<String, Object> respuesta = SolicitudDeCambioDAO
                .registrarSolicitud(solicitudDeCambio);

        if (!(Boolean) respuesta.get("error")) {

            Utilidades.mostrarAlertaSimple("Registro exitoso",
                    (String) respuesta.get("mensaje"),
                    Alert.AlertType.INFORMATION);

        } else {

            Utilidades.mostrarAlertaSimple("Error en el registro",
                    (String) respuesta.get("mensaje"),
                    Alert.AlertType.ERROR);

        }

    }

}
