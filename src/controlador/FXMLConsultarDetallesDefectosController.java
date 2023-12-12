package controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import modelo.pojo.Defecto;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLConsultarDetallesDefectosController implements Initializable {
    
    private Defecto defecto;

    @FXML
    private TextField tfTitulo;

    @FXML
    private TextArea tbDescripcion;

    @FXML
    private TextField tfFechaInicio;

    @FXML
    private TextField tfFechaFin;

    @FXML
    private TextField tfEstudiante;

    @FXML
    private TextField tfEsfuerzo;
    @FXML
    private TextField tfEstado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void inicializarVentana(Defecto defectoSeleccionado) {

        this.defecto = defectoSeleccionado;
        tfTitulo.setText(defecto.getTitulo());

        tfTitulo.setText(defectoSeleccionado.getTitulo());
        tbDescripcion.setText(defectoSeleccionado.getDescripcion());
        tfFechaInicio.setText(defectoSeleccionado.getFechaReporte());
        tfFechaFin.setText(defectoSeleccionado.getFechaFin());
        tfEstudiante.setText(defectoSeleccionado.getNombreEstudiante());
        tfEsfuerzo.setText(String.valueOf(defectoSeleccionado.getEsfuerzoMinutos()));
        tfEstado.setText(defectoSeleccionado.getEstadoDefecto());        

    }
}
