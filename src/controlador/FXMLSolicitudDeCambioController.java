/*
 * Nombre del archivo: FXMLSolicitudDeCambioController.java
 * Autor: Miguel Angel Morales Cruz
 * Paquete: controlador
 * Fecha de creación: 20/11/2023
 * Fecha de modificación: 11/12/2023
 * Descripción: Controlador para la ventana solicitud de cambio.
 */
package controlador;

import interfaces.ObservadorSolicitudesDeCambio;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.dao.SolicitudDeCambioDAO;
import modelo.pojo.SolicitudDeCambio;
import utilidades.Utilidades;

public class FXMLSolicitudDeCambioController implements Initializable {
    
    @FXML
    private VBox vboxScrollPane;
    
    @FXML
    private ImageView ivSalir;
    
    @FXML
    private Label lbNombreSolicitud;
    
    @FXML
    private Label lbFecha;
    
    @FXML
    private Label lbNumSolicitud;
    
    @FXML
    private Label lbSolicitadoPor;
    
    @FXML
    private Label lbDescripcion;
    
    @FXML
    private Label lbRazon;
    
    @FXML
    private Label lbImpacto;
    
    @FXML
    private Label lbAccionPropuesta;
    
    @FXML
    private Label lbDefectoAsociado;
    
    @FXML
    private ScrollPane scrollPanePanelPrincipal;
    
    private Integer idResposable;
    
    private ObservadorSolicitudesDeCambio observador;
    
    private SolicitudDeCambio solicitudDeCambio;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Platform.runLater(() -> {
            scrollPanePanelPrincipal.setVvalue(0);
        });
        
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

    @FXML
    private void clicAprobarSolicitud(ActionEvent event) {
        
        boolean confirmacion = Utilidades
                .mostrarAlertaConfirmacion("Confirmar registro",
                "¿Estas Seguro de aprobar esta solicitud?");
        
            if (confirmacion) {
                
                registrarEvaluacion(1);
                cerrarVentana();
                
            }
            
    }
    
    @FXML
    private void ClicRechazarSolicitud(ActionEvent event) {
        
        boolean confirmacion = Utilidades
                .mostrarAlertaConfirmacion("Confirmar registro",
                "¿Estas Seguro de rechazar esta solicitud?");
        
            if (confirmacion) {
                
                registrarEvaluacion(2);
                cerrarVentana();
                
            }
            
    }
    
    public void inicializarVentana(Integer idResposable, 
            SolicitudDeCambio solicitud, 
            ObservadorSolicitudesDeCambio observador){
        
        this.idResposable = idResposable;
        this.solicitudDeCambio = solicitud;
        this.observador = observador;
        cargarInformacionDeSolicitud();
        
    }
    
    private void cargarInformacionDeSolicitud(){
        
        if(solicitudDeCambio.getDefecto()!=null){
            lbDefectoAsociado.setText(solicitudDeCambio.getDefecto());
        }else{
            lbDefectoAsociado.setText("Ningun defecto asociado");
        }
        
        lbNombreSolicitud.setText(solicitudDeCambio.getTitulo());
        lbFecha.setText(solicitudDeCambio.getFechaCreacion());
        lbNumSolicitud.setText("" + solicitudDeCambio.getIdSolicitudDeCambio());
        lbSolicitadoPor.setText(solicitudDeCambio.getEstudiante());
        lbDescripcion.setText(solicitudDeCambio.getDescripcion());
        lbRazon.setText(solicitudDeCambio.getRazon());
        lbImpacto.setText(solicitudDeCambio.getImpacto());
        lbAccionPropuesta.setText(solicitudDeCambio.getAccionPropuesta());
        
    }
    
    private void cerrarVentana(){
        
        Stage escena = (Stage)lbNombreSolicitud.getScene().getWindow();
        
        escena.close();
        
    }
    
    private void registrarEvaluacion(int evaluacion){
        
        HashMap<String,Object> respuesta = SolicitudDeCambioDAO
                .registrarEvaluacionDeSolicitud
                (solicitudDeCambio.getIdSolicitudDeCambio(), 
                Utilidades.obtenerFechaActual(), 
                evaluacion);
        
        if(!(Boolean) respuesta.get("error")){
            
            Utilidades.mostrarAlertaSimple("Confirmación correcta", "" 
                    + respuesta.get("mensaje"), Alert.AlertType.INFORMATION);
            observador.operacionExitosa("Registro completo de: ", 
                    solicitudDeCambio.getTitulo());
            
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "" 
                    + respuesta.get("mensaje"), Alert.AlertType.ERROR);
        }
        
    }
    
}
