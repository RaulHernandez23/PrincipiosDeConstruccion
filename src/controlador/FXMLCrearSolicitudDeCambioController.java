
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
import modelo.dao.DefectoDAO;
import modelo.dao.SolicitudDeCambioDAO;
import modelo.pojo.Defecto;
import modelo.pojo.SolicitudDeCambio;
import utilidades.Utilidades;

public class FXMLCrearSolicitudDeCambioController implements Initializable {
    
    private int idProyecto;
    private int idEstudiante;
    private ObservableList<Defecto> defectos;
    
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarListenerACampos();
    }
    
    @FXML
    private void clicVolver(ActionEvent event) {
        cerrarVentana();
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
    private void clicEnviarSolicitud(ActionEvent event) {
        if (validarCampos()) {
            boolean confirmacion = Utilidades.mostrarAlertaConfirmacion("Confirmar registro",
                    "¿Estas seguro de enviar la solicitud?");
            if (confirmacion) {
                registrarPaciente();
                cerrarVentana();
            }
        } else {
            Utilidades.mostrarAlertaSimple("Advertencia", "Ingrese datos validos", Alert.AlertType.WARNING);
        }
    }
    
    public void inicializarVentana(int idProyecto, int idEstudiante) {
        this.idProyecto = idProyecto;
        this.idEstudiante = idEstudiante;
        cargarInformacionDefectos(); 
    }
    
    private void cargarInformacionDefectos() {
        HashMap respuesta = DefectoDAO.consultarNombresDefectosProyecto(idProyecto);
        ArrayList<Defecto> defectosDeProyecto = (ArrayList<Defecto>) respuesta.get("defectos");
        defectos = FXCollections.observableArrayList();
        defectos.addAll(defectosDeProyecto);
        
        cbDefectos.setItems(defectos);
    }
    
    private void configurarListenerACampos() {

        ChangeListener<String> cambiosEnCampos = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                verificarCampos();  
            }
        };
        
        ChangeListener<Object> cambiosEnComboBox = new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
                verificarCampos();
            }
        };

        cbDefectos.valueProperty().addListener(cambiosEnComboBox);
        tfNombreSolicitud.textProperty().addListener(cambiosEnCampos);
        taDescripcionCambio.textProperty().addListener(cambiosEnCampos);
        taRazonCambio.textProperty().addListener(cambiosEnCampos);
        taImpactoCambio.textProperty().addListener(cambiosEnCampos);
        taAccionPropuesta.textProperty().addListener(cambiosEnCampos);
    }
    
    private void verificarCampos() {
        btnEnviarSolicitud.setDisable(
                    cbDefectos.getValue() == null ||
                    tfNombreSolicitud.getText().isEmpty() ||
                    taDescripcionCambio.getText().isEmpty() ||
                    taRazonCambio.getText().isEmpty() ||
                    taImpactoCambio.getText().isEmpty() ||
                    taAccionPropuesta.getText().isEmpty());
    }
    
    private boolean validarCampos() {
        boolean esValido = true;
        limpiarEstiloCampos();

        if (tfNombreSolicitud.getText().isEmpty()) {
            tfNombreSolicitud.setStyle("-fx-border-color: red;");
            esValido = false;
        }

        if (taDescripcionCambio.getText().isEmpty()) {
            taDescripcionCambio.setStyle("-fx-border-color: red;");
            esValido = false;
        }

        if (taRazonCambio.getText().isEmpty()) {
            taRazonCambio.setStyle("-fx-border-color: red;");
            esValido = false;
        }

        if (taImpactoCambio.getText().isEmpty()) {
            taImpactoCambio.setStyle("-fx-border-color: red;");
            esValido = false;
        }

        if (taAccionPropuesta.getText().isEmpty()) {
            taAccionPropuesta.setStyle("-fx-border-color: red;");
            esValido = false;
        }

        if (cbDefectos.getValue() == null) {
            cbDefectos.setStyle("-fx-border-color: red;");
            esValido = false;
        }

        return esValido;
    }
    
    private void limpiarEstiloCampos(){
        cbDefectos.setStyle("");
        tfNombreSolicitud.setStyle("");
        taDescripcionCambio.setStyle("");
        taRazonCambio.setStyle("");
        taImpactoCambio.setStyle("");
        taAccionPropuesta.setStyle("");
    }
    
    private void registrarPaciente(){
        SolicitudDeCambio solicitudDeCambio = new SolicitudDeCambio();
        Defecto defectoSeleccionado = cbDefectos.getSelectionModel().getSelectedItem();
        solicitudDeCambio.setIdDefecto(defectoSeleccionado.getIdDefecto());
        solicitudDeCambio.setTitulo(tfNombreSolicitud.getText());
        solicitudDeCambio.setDescripcion(taDescripcionCambio.getText());
        solicitudDeCambio.setRazon(taRazonCambio.getText());
        solicitudDeCambio.setImpacto(taImpactoCambio.getText());
        solicitudDeCambio.setAccionPropuesta(taAccionPropuesta.getText());
        String fechaRegistro = Utilidades.obtenerFechaActual();
        solicitudDeCambio.setFechaCreacion(fechaRegistro);
        solicitudDeCambio.setIdEstadoSolicitud(3);
        solicitudDeCambio.setIdEstudiante(idEstudiante);
        solicitudDeCambio.setIdProyecto(idProyecto);
        
        HashMap<String, Object> respuesta = SolicitudDeCambioDAO.registrarSolicitud(solicitudDeCambio);
        if (!(Boolean) respuesta.get("error")) {
            Utilidades.mostrarAlertaSimple("Registro exitoso",
                    (String) respuesta.get("mensaje"),
                    Alert.AlertType.INFORMATION);
        } else {
            Utilidades.mostrarAlertaSimple("Error en el registro",
                    (String) respuesta.get("mensaje"), Alert.AlertType.ERROR);
        }
    }
    
    private void cerrarVentana() {
        Stage escenario = (Stage) tfNombreSolicitud.getScene().getWindow();
        escenario.close();
    }

    @FXML
    private void clicSalir(MouseEvent event) {
        cerrarVentana();
    }

}
