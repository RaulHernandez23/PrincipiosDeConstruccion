package controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.dao.CambioDAO;
import modelo.dao.SolicitudDeCambioDAO;
import modelo.pojo.Cambio;
import modelo.pojo.SolicitudDeCambio;
import utilidades.Alertas;
import utilidades.Utilidades;

public class FXMLRegistrarCambioController implements Initializable {

    @FXML
    private VBox vboxRegistrarCambio;

    @FXML
    private ImageView ivSalir;

    @FXML
    private TextField tfTitulo;

    @FXML
    private TextArea tfDescripcion;

    @FXML
    private ComboBox<SolicitudDeCambio> cbSolicitud;

    @FXML
    private ComboBox<String> cbTipo;

    @FXML
    private TextField tfEsfuerzo;

    @FXML
    private ComboBox<String> cbEstado;

    @FXML
    private Button btnRegistrarComponente;

    private ObservableList<String> listaTipos;

    private ObservableList<String> listaEstados;

    private ObservableList<SolicitudDeCambio> listaSolicitudes;

    private String fechaInicio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        listaSolicitudes = FXCollections.observableArrayList();
        listaTipos = FXCollections.observableArrayList();
        listaEstados = FXCollections.observableArrayList();

        cargarSolicitudes();
        cargarTipos();
        cargarEstados();
        cbSolicitud.getSelectionModel().select(0);
        cbTipo.getSelectionModel().select(0);
        cbEstado.getSelectionModel().select(0);
        btnRegistrarComponente.setDisable(true);
        verificarCamposLlenos();

    }

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
        Stage escenario = (Stage) vboxRegistrarCambio.getScene().getWindow();
        escenario.close();
    }

    @FXML
    private void btnRegistrar(ActionEvent event) {

        if (validarCampos()) {

            registrarCambio();
            Stage escenario = (Stage) vboxRegistrarCambio.getScene().getWindow();
            escenario.close();

        } else {
            Alertas.mostrarAlerta("Datos Inválidos", "Ingrese datos válidos",
                    Alert.AlertType.WARNING);
        }
    }

    private void cargarSolicitudes() {

        HashMap<String, Object> respuesta = SolicitudDeCambioDAO.consultarSolicitudes();

        if (!(Boolean) respuesta.get("error")) {

            listaSolicitudes = FXCollections.observableArrayList();

            ArrayList<SolicitudDeCambio> lista = (ArrayList<SolicitudDeCambio>) respuesta.get("solicitudes");
            listaSolicitudes.addAll(lista);
            cbSolicitud.setItems(listaSolicitudes);

        } else {
            Alertas.mostrarAlerta("Error de Conexion", respuesta.get(
                    "mensaje").toString(), Alert.AlertType.ERROR);
        }

    }

    private void cargarEstados() {

        HashMap<String, Object> respuesta = CambioDAO.consultarEstados();

        if (!(Boolean) respuesta.get("error")) {

            listaEstados = FXCollections.observableArrayList();

            ArrayList<String> lista = (ArrayList<String>) respuesta.get("estados");
            listaEstados.addAll(lista);
            cbEstado.setItems(listaEstados);

        } else {
            Alertas.mostrarAlerta("Error de Conexion", respuesta.get(
                    "mensaje").toString(), Alert.AlertType.ERROR);
        }

    }

    private void cargarTipos() {

        HashMap<String, Object> respuesta = CambioDAO.consultarTiposActividades();

        if (!(Boolean) respuesta.get("error")) {

            listaTipos = FXCollections.observableArrayList();

            ArrayList<String> lista = (ArrayList<String>) respuesta.get("tiposActividades");
            listaTipos.addAll(lista);
            cbTipo.setItems(listaTipos);

        } else {
            Alertas.mostrarAlerta("Error de Conexion", respuesta.get(
                    "mensaje").toString(), Alert.AlertType.ERROR);
        }

    }

    private void verificarCamposLlenos() {

        ChangeListener<String> camposLlenos = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                btnRegistrarComponente.setDisable(
                        tfTitulo.getText().isEmpty() ||
                                tfDescripcion.getText().isEmpty() ||
                                tfEsfuerzo.getText().isEmpty() ||
                                cbTipo.getSelectionModel().getSelectedIndex() < 0 ||
                                cbSolicitud.getSelectionModel().getSelectedIndex() < 0 ||
                                cbEstado.getSelectionModel().getSelectedIndex() < 0);

            }
        };

        ChangeListener<SolicitudDeCambio> solicitudElegida = new ChangeListener<SolicitudDeCambio>() {
            @Override
            public void changed(ObservableValue<? extends SolicitudDeCambio> observable, SolicitudDeCambio oldValue,
                    SolicitudDeCambio newValue) {

                btnRegistrarComponente.setDisable(
                        tfTitulo.getText().isEmpty() ||
                                tfDescripcion.getText().isEmpty() ||
                                tfEsfuerzo.getText().isEmpty() ||
                                cbTipo.getSelectionModel().getSelectedIndex() < 0 ||
                                cbSolicitud.getSelectionModel().getSelectedIndex() < 0 ||
                                cbEstado.getSelectionModel().getSelectedIndex() < 0);

            }
        };

        tfTitulo.textProperty().addListener(camposLlenos);
        tfDescripcion.textProperty().addListener(camposLlenos);
        tfEsfuerzo.textProperty().addListener(camposLlenos);
        cbSolicitud.getSelectionModel().selectedItemProperty().addListener(solicitudElegida);
        cbTipo.getSelectionModel().selectedItemProperty().addListener(camposLlenos);
        cbEstado.getSelectionModel().selectedItemProperty().addListener(camposLlenos);

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

        if (cbSolicitud.getSelectionModel().getSelectedIndex() < 0) {
            camposValidos = false;
        }

        if (cbEstado.getSelectionModel().getSelectedIndex() < 0) {
            camposValidos = false;
        }

        if (tfEsfuerzo.getText().length() == 0) {
            camposValidos = false;
        }

        fechaInicio = Utilidades.obtenerFechaActual();

        return camposValidos;

    }

    private void registrarCambio() {

        Cambio cambio = new Cambio();
        cambio.setTitulo(tfTitulo.getText());
        cambio.setDescripcion(tfDescripcion.getText());
        cambio.setEsfuerzoMinutos(Integer.parseInt(tfEsfuerzo.getText()));
        cambio.setIdTipoActividad(cbTipo.getSelectionModel().getSelectedIndex() + 1);
        cambio.setIdSolicitud(cbSolicitud.getSelectionModel().getSelectedItem().getIdSolicitudDeCambio());
        cambio.setIdEstadoCambio(cbEstado.getSelectionModel().getSelectedIndex() + 1);
        cambio.setFechaInicio(fechaInicio);

        HashMap<String, Object> respuesta = null;
        try {

            respuesta = CambioDAO.registrarCambio(cambio);

            if (!(Boolean) respuesta.get("error")) {
                Alertas.mostrarAlerta("Cambio Registrado", respuesta.get(
                        "mensaje").toString(), Alert.AlertType.INFORMATION);
            } else {
                Alertas.mostrarAlerta("Error de Conexion", respuesta.get(
                        "mensaje").toString(), Alert.AlertType.ERROR);
            }

        } catch (SQLException e) {
            Utilidades.mostrarAlertaSimple("Error", "No se pudo conectar a la base de datos, inténtelo más tarde",
                    Alert.AlertType.ERROR);
        }

    }

}
