/*
 * Nombre del archivo: FXMLDetalleActividadController.java
 * Nombre del autor: Raul Hernandez Olivares
 * Paquete: controlador
 * Fecha de creacion: 12/12/2023
 * Fecha de modificacion: 14/12/2023
 * Controlador para la vista DetalleActividad
 */

package controlador;

import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.dao.ActividadDAO;
import modelo.pojo.Actividad;
import utilidades.Utilidades;

public class FXMLDetalleActividadController implements Initializable {

    private ObservableList<Actividad> actividades;
    private boolean esFinalizar;

    @FXML
    private ComboBox<Actividad> cbActividades;

    @FXML
    private DatePicker dpFechaInicio;

    @FXML
    private DatePicker dpFechaFin;

    @FXML
    private TextArea taDescripcion;

    @FXML
    private TextField tfEstado;

    @FXML
    private TextField tfTipo;

    @FXML
    private TextField tfEsfuerzo;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnVolver;

    @FXML
    private Label lblSGBP;

    @FXML
    private Label lblTituloVentana;

    @FXML
    private ImageView ivLogoUV;

    @FXML
    private Label lblUniversidadVeracruzana;

    @FXML
    private ImageView ivSalir;

    @FXML
    private Label lblEliminarActividad;

    @FXML 
    private Label lblDatosInvalidos;

    @FXML
    private Label lblDerechosReservados;

    @FXML
    private void btnEliminarClic(ActionEvent event) {
        if (esFinalizar) {
    
            if (Utilidades.mostrarAlertaConfirmacion("Confirmar",
                    "¿Seguro que desea finalizar la actividad: "
                            + cbActividades.getValue().getTitulo() + "?")) {
                finalizarActividad();
            }
        } else {
            if (Utilidades.mostrarAlertaConfirmacion("Confirmar",
                    "¿Seguro que desea eliminar la actividad: "
                            + cbActividades.getValue().getTitulo() + "?")) {
                eliminarActividad();;
            }
        }

    }

    @FXML
    private void btnVolver(ActionEvent event) {
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

    public void inicializarInformacion(Integer idProyecto, boolean esFinalizar) {

        configurarListenerComboActividad();
        
        this.esFinalizar = esFinalizar;
        if(esFinalizar){
            lblTituloVentana.setText("Finalizar actividad");
            tfEsfuerzo.setEditable(esFinalizar);
            configurarListenerEsfuerzo();   
        }

        recuperarActividades(idProyecto);
    }

    public void salir() {

        Stage escenario = (Stage) cbActividades.getScene().getWindow();
        escenario.close();

    }

    private void recuperarActividades(Integer idProyecto) {
        HashMap<String, Object> respuesta = ActividadDAO
                .obtenerTodasActividadesProyecto(idProyecto);

        if (!(boolean) respuesta.get("error")) {

            actividades = FXCollections.observableArrayList();
            ArrayList<Actividad> lista = (ArrayList) respuesta
                    .get("actividades");
            if(esFinalizar) {
                for(Actividad actividad : lista) {
                    if(!actividad.getEstadoActividad().equals("Realizada")) {
                        actividades.add(actividad);
                    }
                }
            } else {
                actividades.addAll(lista);
            }
            cbActividades.setItems(actividades);
            cbActividades.getSelectionModel().select(0);

        } else {
            Utilidades.mostrarAlertaSimple("Error",
                    (String) respuesta.get("mensaje"),
                    Alert.AlertType.ERROR);
        }
    }

    private void configurarListenerComboActividad() {
        cbActividades.valueProperty().addListener(
                new ChangeListener<Actividad>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends Actividad> observable,
                            Actividad oldValue,
                            Actividad newValue) {
                        if (newValue != null) {
                            dpFechaInicio
                                    .setValue(LocalDate.parse(
                                            newValue.getFechaInicio()));
                            if(newValue.getFechaFin() != null) {
                                dpFechaFin
                                    .setValue(LocalDate.parse(
                                            newValue.getFechaFin()));
                            } else {
                                dpFechaFin.setValue(null);
                            }
                            taDescripcion
                                    .setText(newValue.getDescripcion());
                            tfEstado
                                    .setText(newValue.getEstadoActividad());
                            tfEsfuerzo
                                    .setText(String.valueOf(
                                            newValue.getEsfuerzoMinutos()));
                            tfTipo.setText(String.valueOf(
                                            newValue.getTipo()));
                            if(!esFinalizar) {
                                btnEliminar.setDisable(false);
                            }
                        }
                    }
                });
    }

    private void eliminarActividad() {
        HashMap<String, Object> respuesta = ActividadDAO.eliminarActividad(
                cbActividades.getValue()
                        .getIdActividad());
        if (!(boolean) respuesta.get("error")) {

            Utilidades.mostrarAlertaSimple("Actividad eliminada",
                    (String) respuesta.get("mensaje"),
                    Alert.AlertType.INFORMATION);
            salir();

        } else {
            Utilidades.mostrarAlertaSimple("Error",
                    (String) respuesta.get("mensaje"),
                    Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dpFechaInicio.setDisable(true);
        dpFechaFin.setDisable(true);
        lblDatosInvalidos.setText("");
        btnEliminar.setDisable(true);

    }

    private void configurarListenerEsfuerzo() {
        tfEsfuerzo.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                btnEliminar.setDisable(true);
                if (newValue.length() > 0 && newValue.length() <= 11) {
                    if (newValue.matches("\\d*")) {
                        if(Integer.parseInt(newValue) >= 0){
                            btnEliminar.setDisable(false);
                            lblDatosInvalidos.setText("");
                        }
                    } else {
                        lblDatosInvalidos.setText("Debe ingresar solo numeros");
                    }
                } else {
                    lblDatosInvalidos.setText("El esfuerzo no puede ser vacío");
                }

            }
        });
    }

    private void finalizarActividad() {

        HashMap<String, Object> respuesta = ActividadDAO.finalizarActividad
                (cbActividades.getValue().getIdActividad(),
                Integer.parseInt(tfEsfuerzo.getText()));
        if (!(boolean) respuesta.get("error")) {

            Utilidades.mostrarAlertaSimple("Actividad finalizada",
                    (String) respuesta.get("mensaje"),
                    Alert.AlertType.INFORMATION);
            salir();

        } else {
            Utilidades.mostrarAlertaSimple("Error",
                    (String) respuesta.get("mensaje"),
                    Alert.AlertType.ERROR);

        }
    }
}