
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.dao.SolicitudDeCambioDAO;
import modelo.pojo.SolicitudDeCambio;
import utilidades.Utilidades;

public class FXMLSolicitudesDeCambioController implements Initializable {

    private ObservableList<SolicitudDeCambio> solicitudes;
    
    @FXML
    private TableView<SolicitudDeCambio> tvSolicitudesDeCambio;
    @FXML
    private TableColumn<?, ?> colNombreSolicitud;
    @FXML
    private TableColumn<?, ?> colNombreAlumno;
    @FXML
    private TableColumn<?, ?> colFechaRegistro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
    }    

    @FXML
    private void clicVer(ActionEvent event) {
    }

    @FXML
    private void clicVolver(ActionEvent event) {
    }
    
    private void configurarTabla(){
        obtenerInformacionPacientes();
        this.colNombreSolicitud.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colNombreAlumno.setCellValueFactory(new PropertyValueFactory("idEstudiante"));
        this.colFechaRegistro.setCellValueFactory(new PropertyValueFactory("fechaCreacion"));
    }
    
    private void obtenerInformacionPacientes(){
        Integer idProyecto =1;
        HashMap<String,Object> respuesta = SolicitudDeCambioDAO.consultarSolicitudesPendientes(idProyecto);
        if(!(Boolean) respuesta.get("error")){
            solicitudes = FXCollections.observableArrayList();
            ArrayList<SolicitudDeCambio> lista = (ArrayList<SolicitudDeCambio>)respuesta.get("solicitudes"); //Se guardan los pacientes en el array list del hashMap
            solicitudes.addAll(lista);
            tvSolicitudesDeCambio.setItems(solicitudes);
        }else{
            Utilidades.mostrarAlertaSimple("Error de carga", "" + respuesta.get("mensaje"), Alert.AlertType.ERROR);// Se podria castear en vez de ""+ ...(String)
        }
    }
}
