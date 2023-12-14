/*
 * Nombre del archivo: FXMLRegistrarActividadController.java
 * Autor: Albhieri Cristoff Villa Contreras
 * Paquete: controlador
 * Fecha de creación: 03/12/2023
 * Fecha de modificación: 14/12/2023
 * Descripción: Clase del controlador para poder registrar una actividad
 */

package controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javafx.beans.value.ChangeListener;

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

    @FXML
    private Button btnRegistrar;

    private ObservableList<String> listaTipos;

    private Integer idProyecto;

    private int idResponsable;

    private String responsable;

    private String fechaInicio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listaTipos = FXCollections.observableArrayList();

        cargarTipos();
        cbTipo.getSelectionModel().select(0);
        btnRegistrar.setDisable(true);
        verificarCamposLlenos();

    }

    @FXML
    void btnRegistrarClic(ActionEvent event) {

        if (validarCampos()) {

            registrarActividad();
            salir();

        } else {
            Alertas.mostrarAlerta("Datos Inválidos",
                    "Ingrese datos válidos",
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
        salir();
    }

    public void salir() {

        Stage escenario = (Stage) vboxRegistrarActividad.getScene().getWindow();

        escenario.close();

    }

    public void inicializarVentana(
            Integer idProyecto, int idResponsable, String responsable) {

        this.idProyecto = idProyecto;
        this.idResponsable = idResponsable;
        this.responsable = responsable;

    }

    private void cargarTipos() {

        HashMap<String, Object> respuesta = ActividadDAO
                .consultarTiposActividades();

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
        actividad.setEstadoActividad("No asignada");
        actividad.setIdEstadoActividad(3);
        actividad.setIdProyecto(idProyecto);

        HashMap<String, Object> respuesta = null;

        try {

            respuesta = ActividadDAO.registrarActividad(
                    actividad);

            if (!(Boolean) respuesta.get("error")) {

                Alertas.mostrarAlerta("Actividad Registrada",
                        respuesta.get("mensaje").toString(),
                        Alert.AlertType.INFORMATION);

            } else {
                Alertas.mostrarAlerta("Error de Conexion",
                        respuesta.get(
                                "mensaje").toString(),
                        Alert.AlertType.ERROR);
            }

        } catch (SQLException e) {
            Utilidades.mostrarAlertaSimple("Error",
                    "No se pudo conectar a la base de datos,"
                            + " inténtelo más tarde",
                    Alert.AlertType.ERROR);
        }

    }

    private boolean validarCampos() {

        boolean camposValidos = true;
        Pattern patron = Pattern
                .compile(
                        "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ][a-zA-ZáéíóúÁÉÍÓÚñÑüÜ0-9]*"
                                + "(?: [a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]"
                                + "[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ0-9]*)*"
                                + "(?: [0-9]{1,50})?$");
        Matcher matcher = patron.matcher(tfTitulo.getText());

        if (tfTitulo.getText().length() == 0 || !matcher.matches()) {
            camposValidos = false;
        }

        patron = Pattern
                .compile(
                        "^[a-zA-Z0-9,.!?;:'\"()\s*'ñáéíó"
                                + "úÁÉÍÓÚàèìòùÀÈÌÒÙäëïöüÄËÏÖÜâêîôûÂÊÎÔÛçÇ-]*$");
        matcher = patron.matcher(tfDescripcion.getText());

        if (tfDescripcion.getText().length() == 0 || !matcher.matches()) {
            camposValidos = false;
        }

        if (cbTipo.getSelectionModel().getSelectedIndex() < 0) {
            camposValidos = false;
        }

        fechaInicio = Utilidades.obtenerFechaServidor();

        return camposValidos;

    }

    private void verificarCamposLlenos() {

        ChangeListener<String> camposLlenos = (observable, oldValue,
                newValue) -> verificar();

        tfTitulo.textProperty().addListener(camposLlenos);
        tfDescripcion.textProperty().addListener(camposLlenos);

    }

    public void verificar() {
        btnRegistrar.setDisable(tfTitulo.getText().isEmpty() ||
                tfDescripcion.getText().isEmpty() ||
                cbTipo.getSelectionModel().getSelectedIndex() < 0);
    }

}