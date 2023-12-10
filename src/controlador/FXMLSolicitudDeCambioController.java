/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import interfaces.ObservadorSolicitudesDeCambio;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.dao.SolicitudDeCambioDAO;
import modelo.pojo.SolicitudDeCambio;
import utilidades.Utilidades;

/**
 *
 * @author MIGUEL ANGEL
 */
public class FXMLSolicitudDeCambioController implements Initializable {
    
    private Integer idResposable;
    private ObservadorSolicitudesDeCambio observador;
    private SolicitudDeCambio solicitudDeCambio;
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  

    public void inicializarVentana(Integer idResposable, SolicitudDeCambio solicitud, 
            ObservadorSolicitudesDeCambio observador){
        this.idResposable = idResposable;
        this.solicitudDeCambio = solicitud;
        cargarInformacionDeSolicitud(); //NullPointerException
        this.observador = observador;
    }
    
    @FXML
    private void hoverOutSalir(MouseEvent event) {
        ivSalir.setImage(new Image(Utilidades.getInputStream(
                "/recursos/imagenes/logoSalir2.png")));
    }

    @FXML
    private void hoverInSalir(MouseEvent event) {
        ivSalir.setImage(new Image(Utilidades.getInputStream(
                "/recursos/imagenes/logoSalir.png")));

    }
    
    private void cargarInformacionDeSolicitud(){
        lbDefectoAsociado.setText(solicitudDeCambio.getDefecto());
        lbNombreSolicitud.setText(solicitudDeCambio.getTitulo());
        lbFecha.setText(solicitudDeCambio.getFechaCreacion());
        lbNumSolicitud.setText("" + solicitudDeCambio.getIdDefecto());
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

    @FXML
    private void ClicSalir(MouseEvent event) {
        cerrarVentana();
    }

    @FXML
    private void clicAprobarSolicitud(ActionEvent event) {
        boolean confirmacion = Utilidades.mostrarAlertaConfirmacion("Confirmar registro",
                    "¿Estas Seguro de aprobar esta solicitud?");
            if (confirmacion) {
                registrarEvaluacion(1);
                cerrarVentana();
            }
    }
    
    private void registrarEvaluacion(int evaluacion){
        HashMap<String,Object> respuesta = SolicitudDeCambioDAO.registrarEvaluacionDeSolicitud
                    (solicitudDeCambio.getIdSolicitudDeCambio(), 
                    Utilidades.obtenerFechaActual(), 
                    evaluacion);
        if(!(Boolean) respuesta.get("error")){
            Utilidades.mostrarAlertaSimple("Confirmación correcta", "" + respuesta.get("mensaje"), Alert.AlertType.INFORMATION);
            observador.operacionExitosa("Registro", solicitudDeCambio.getTitulo());
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexion", "" + respuesta.get("mensaje"), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void ClicRechazarSolicitud(ActionEvent event) {
        boolean confirmacion = Utilidades.mostrarAlertaConfirmacion("Confirmar registro",
                    "¿Estas Seguro de rechazar esta solicitud?");
            if (confirmacion) {
                registrarEvaluacion(2);
                cerrarVentana();
            }
    }

    @FXML
    private void clicVolver(ActionEvent event) {
        cerrarVentana();
    }
}
