package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import modelo.dao.EstudianteDAO;
import modelo.pojo.Actividad;
import modelo.pojo.Estudiante;
import utilidades.Utilidades;


//De verdad es necesario cerrar todo luego de una excepcion o al terminar el CU?
public class FXMLAsignarActividadController implements Initializable {

    private ObservableList<Actividad> actividadesSinAsignar;
    private ObservableList<Estudiante> estudiantes;

    @FXML
    private TableView<Actividad> tvActividades;

    @FXML
    private ImageView ivSalir;

    @FXML
    private TableColumn colTitulo;

    @FXML
    private TableColumn colDescripcion;

    @FXML
    private ComboBox<Estudiante> cbEstudiantes;

    @FXML
    private Button btnAsignar;

    @FXML
    private Button btnVolver;

    @FXML
    private void btnAsignarClic() {

        Actividad actividad = tvActividades.getSelectionModel().getSelectedItem();
        Estudiante estudiante = cbEstudiantes.getSelectionModel().getSelectedItem();

        HashMap<String, Object> respuesta = ActividadDAO.asignarActividad(actividad.getIdActividad(), estudiante.getIdEstudiante());

        if (!(boolean) respuesta.get("error")) {

            Utilidades.mostrarAlertaSimple("Asignacion exitosa",
                    (String) respuesta.get("mensaje"),
                    Alert.AlertType.INFORMATION);

            obtenerActividadesSinAsignarProyecto(estudiante.getIdProyecto());
            cargarEstudiantes(estudiante.getIdProyecto());

        } else {
            Utilidades.mostrarAlertaSimple("Error",
                    (String) respuesta.get("mensaje"),
                    Alert.AlertType.ERROR);
        }

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
        cargarEstudiantes(idProyecto);

    }

    public void cargarEstudiantes(int idProyecto) {

        HashMap<String, Object> respuesta = EstudianteDAO.recuperarEstudiantesProyectoFinalFinal(idProyecto);

        if (!(boolean) respuesta.get("error")) {

            estudiantes = FXCollections.observableArrayList();
            ArrayList<Estudiante> lista = (ArrayList) respuesta.get("estudiantes");
            estudiantes.addAll(lista);
            cbEstudiantes.setItems(estudiantes);
            btnAsignar.setDisable(true);
            cbEstudiantes.getSelectionModel().select(0);

        } else {
            Utilidades.mostrarAlertaSimple("Error",
                    (String) respuesta.get("mensaje"),
                    Alert.AlertType.ERROR);
        }

    }

    private void configurarTabla() {
        colTitulo.setCellValueFactory(new PropertyValueFactory("titulo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        
        tvActividades.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Actividad>() {
        
            @Override
            public void changed(ObservableValue<? extends Actividad> observable, Actividad oldValue, Actividad newValue) {
                if(newValue != null) {
                    btnAsignar.setDisable(false);
                }
            }
        
        });
    }

    private void obtenerActividadesSinAsignarProyecto(int idProyecto) {

        HashMap<String, Object> respuesta = ActividadDAO.obtenerActividadesSinAsignar(idProyecto);

        if (!(boolean) respuesta.get("error")) {

            actividadesSinAsignar = FXCollections.observableArrayList();
            ArrayList<Actividad> lista = (ArrayList) respuesta.get("actividades");
            actividadesSinAsignar.addAll(lista);
            tvActividades.setItems(actividadesSinAsignar);
            
        } else {
            Utilidades.mostrarAlertaSimple("Error",
                    (String) respuesta.get("mensaje"),
                    Alert.AlertType.ERROR);
        }
    }

}
