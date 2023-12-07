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

public class FXMLEliminarActividadController implements Initializable{

    private ObservableList<Actividad> actividades;

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
    private TextField tfEsfuerzo;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnVolver;

    @FXML
    private Label lblSGBP;

    @FXML
    private ImageView ivLogoUV;

    @FXML
    private Label lblUniversidadVeracruzana;

    @FXML
    private ImageView ivSalir;

    @FXML
    private Label lblEliminarActividad;

    @FXML
    private Label lblDerechosReservados;


    @FXML
    private void btnEliminarClic(ActionEvent event) {

        if(Utilidades.mostrarAlertaConfirmacion("Confirmar", 
                "¿Seguro que desea eliminar la actividad: " 
                + cbActividades.getValue().getTitulo() + "?") ) {
                    eliminarActividad();
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

    public void inicializarInformacion(int idProyecto) {
        recuperarActividades(idProyecto);
    }

    public void salir() {

        Stage escenario = (Stage) cbActividades.getScene().getWindow();
        escenario.close();

    }

    private void recuperarActividades(int idProyecto) {
        HashMap<String, Object> respuesta = ActividadDAO.obtenerActividadesProyecto(idProyecto);

        if(!(boolean) respuesta.get("error")) {
            actividades = FXCollections.observableArrayList();
            ArrayList<Actividad> lista = (ArrayList) respuesta.get("actividades");
            actividades.addAll(lista);
            cbActividades.setItems(actividades);
            cbActividades.getSelectionModel().select(0);

        } else {
            Utilidades.mostrarAlertaSimple("Error", 
                    (String) respuesta.get("mensaje"),
                    Alert.AlertType.ERROR);
        }
    }

    private void configurarListenerComboActividad() {
        cbActividades.valueProperty().addListener(new ChangeListener<Actividad>() {
            
            @Override
            public void changed(ObservableValue<? extends Actividad> observable, Actividad oldValue, Actividad newValue) {
                if(newValue != null) {
                    dpFechaInicio.setValue(LocalDate.parse(newValue.getFechaInicio()));
                    dpFechaFin.setValue(LocalDate.parse(newValue.getFechaFin()));
                    taDescripcion.setText(newValue.getDescripcion());
                    tfEstado.setText(newValue.getEstadoActividad());
                    tfEsfuerzo.setText(String.valueOf(newValue.getEsfuerzoMinutos()));
                    btnEliminar.setDisable(false);
                }
            }
        });
    }

    private void eliminarActividad() {
        HashMap<String, Object> respuesta = 
                ActividadDAO.eliminarActividad(cbActividades.getValue()
                        .getIdActividad());
        if(!(boolean) respuesta.get("error")) {

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

        configurarListenerComboActividad();
        dpFechaInicio.setDisable(true);
        dpFechaFin.setDisable(true);

    }
}
