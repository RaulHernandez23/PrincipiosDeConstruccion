package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.dao.ActividadDAO;
import modelo.pojo.Actividad;
import utilidades.Utilidades;

public class FXMLAsignarActividadController implements Initializable{

    private ObservableList<Actividad> actividadesSinAsignar;

    @FXML
    private TableView<Actividad> tvActividades;

    @FXML
    private ImageView ivSalir;

    @FXML
    private TableColumn colTitulo;

    @FXML
    private TableColumn colDescripcion;

    @FXML
    private ComboBox<String> cbEstudiantes;

    @FXML
    private Button btnAsignar;

    @FXML
    private Button btnVolver;

    @FXML
    private void btnAsignarClic() {

    }

    @FXML
    private void btnVolver() {
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

    public void salir() {

        Stage escenario = (Stage) tvActividades.getScene().getWindow();

        escenario.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        configurarTabla();
        
    }

    public void inicializarInformacion(int idProyecto) {

        obtenerActividadesSinAsignarProyecto(idProyecto);
        
    }

    private void configurarTabla() {
        colTitulo.setCellValueFactory(new PropertyValueFactory("titulo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        /*tvActividades.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Actividad>() {

            @Override
            public void changed(ObservableValue<? extends Actividad> observable, Actividad oldValue, Actividad newValue) {
                if(newValue != null) {
                    cbEstudiantes.setDisable(false);
                    btnAsignar.setDisable(false);
            }
            
        });*/
    }

    private void obtenerActividadesSinAsignarProyecto(int idProyecto) {

        HashMap<String, Object> respuesta = ActividadDAO.obtenerActividadesProyecto(idProyecto);

        if(!(boolean) respuesta.get("error")) {
            actividadesSinAsignar = FXCollections.observableArrayList();
            ArrayList<Actividad> lista = (ArrayList) respuesta.get("actividades");

            /*for(Actividad actividad : lista) {
                System.out.println(actividad.getTitulo() + " " + actividad.getEstudiante());
                if(actividad.getEstudiante() == null) {
                    actividadesSinAsignar.add(actividad);
                }
            }*/
            actividadesSinAsignar.addAll(lista);
            tvActividades.setItems(actividadesSinAsignar);
        } else {
            Utilidades.mostrarAlertaSimple("Error", 
                    (String) respuesta.get("mensaje"),
                    Alert.AlertType.ERROR);
        }
    }

    

}
