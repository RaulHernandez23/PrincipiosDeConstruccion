package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.dao.ActividadDAO;
import modelo.pojo.Actividad;
import utilidades.Alertas;
import utilidades.Utilidades;

public class FXMLRegistrarActividadController implements Initializable {

    @FXML
    private ImageView ivSalir;

    @FXML
    private TextField tfTitulo;

    @FXML
    private TextArea tfDescripcion;

    @FXML
    private ComboBox<String> cbTipo;

    @FXML
    private VBox vboxRegistrarActividad;

    private ObservableList<String> listaTipos;

    private int idResponsable;

    private String responsable;

    private String fechaInicio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listaTipos = FXCollections.observableArrayList();

        cargarTipos();
        cbTipo.getSelectionModel().select(0);

    }

    @FXML
    void btnRegistrar(ActionEvent event) {

        if (validarCampos()) {

            registrarActividad();

        } else {

            Alertas.mostrarAlerta("Campos Vacios", "Por favor llena todos los campos",
                    Alert.AlertType.WARNING);

        }

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
    private void btnSalir(MouseEvent event) {
        Stage escenario = (Stage) vboxRegistrarActividad.getScene().getWindow();
        escenario.close();
    }

    public void inicializarVentana(int idResponsable, String responsable) {

        this.idResponsable = idResponsable;
        this.responsable = responsable;

    }

    private void cargarTipos() {

        HashMap<String, Object> respuesta = ActividadDAO.consultarTiposActividades();

        if (!(Boolean) respuesta.get("error")) {

            listaTipos = FXCollections.observableArrayList();

            ArrayList<String> lista = (ArrayList<String>) respuesta.get(
                    "tiposActividades");

            listaTipos.addAll(lista);
            cbTipo.setItems(listaTipos);

        } else {
            Alertas.mostrarAlerta("Error de Conexion", respuesta.get(
                    "mensaje").toString(), Alert.AlertType.ERROR);
        }

    }

    private void registrarActividad() {

        Actividad actividad = new Actividad();
        actividad.setTitulo(tfTitulo.getText());
        actividad.setDescripcion(tfDescripcion.getText());
        actividad.setIdTipo(cbTipo.getSelectionModel().getSelectedIndex() + 1);
        actividad.setTipo(cbTipo.getValue());
        actividad.setFechaInicio(fechaInicio);
        actividad.setIdResponsable(idResponsable);
        actividad.setResponsable(responsable);
        actividad.setEstadoActividad("No Asignada");
        actividad.setIdEstadoActividad(3);

        HashMap<String, Object> respuesta = ActividadDAO.registrarActividad(
                actividad);

        if (!(Boolean) respuesta.get("error")) {

            Alertas.mostrarAlerta("Actividad Registrada", respuesta.get(
                    "mensaje").toString(), Alert.AlertType.INFORMATION);

        } else {
            Alertas.mostrarAlerta("Error de Conexion", respuesta.get(
                    "mensaje").toString(), Alert.AlertType.ERROR);
        }

    }

    private boolean validarCampos() {

        boolean camposValidos = true;

        if (tfTitulo.getText().length() == 0) {
            camposValidos = false;
        }

        if (tfDescripcion.getText().length() == 0) {
            camposValidos = false;
        }

        if (cbTipo.getSelectionModel().getSelectedIndex() < 0) {
            camposValidos = false;
        }

        fechaInicio = Utilidades.obtenerFechaActual();

        return camposValidos;

    }

}