/*
* Autor: Cesar Gonzalez Lopez
* Fecha de creación: 24/11/2023
* Fecha de modificación: 24/11/2023
* Descripción: clase para reasignar actividades a los estudiantes
*/

package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelo.dao.EstudianteDAO;
import modelo.pojo.Actividad;
import modelo.pojo.Estudiante;

public class FXMLReasignarActividadController implements Initializable {
    @FXML
    private TableView<Actividad> tvActividadesPendientes;

    @FXML
    private TableColumn<Actividad, String> colTitulo;

    @FXML
    private TableColumn<Actividad, String> colEstudiante;

    @FXML
    private TableColumn<Actividad, Date> colFechaInicio;

    @FXML
    private ComboBox<Estudiante> cbEstudiantes;

    @FXML
    private Button btnReasignarActividad;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mostrarDatos();

    }

    private void mostrarDatos() {

        ObservableList<Actividad> actividades = FXCollections.observableArrayList();

        try {

            ArrayList<Actividad> listaActividades = modelo.dao.ActividadDAO.consultarActividades();
            actividades.addAll(listaActividades);

            tvActividadesPendientes.setItems(actividades);
            colTitulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
            colEstudiante
                    .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstudiante()));
            colFechaInicio
                    .setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaInicio()));

        } catch (Exception e) {

            utilidades.Alertas.mostrarAlerta("Error de conexion", 
                "No se pudo consultar la lista de cambios", 
                Alert.AlertType.ERROR);

        }

        ObservableList<Estudiante> estudiantes = FXCollections.observableArrayList();

        try {

            ArrayList<Estudiante> listaEstudiantes = EstudianteDAO.consultarListaEstudiante();
            estudiantes.addAll(listaEstudiantes);

            cbEstudiantes.setItems(estudiantes);

        } catch (Exception e) {

            utilidades.Alertas.mostrarAlerta("Error de conexion", 
                "No se pudo consultar la lista de cambios", 
                Alert.AlertType.ERROR);

        }
    }

    @FXML
    private void btnReasignarActividadClic(ActionEvent event) {
        
        TableView<Actividad> tableView = tvActividadesPendientes; 
        Actividad actividadSeleccionada = tableView.getSelectionModel().getSelectedItem();

        Estudiante estudianteSeleccionado = cbEstudiantes.getValue();

        if (modelo.dao.ActividadDAO.reasignarActividad(actividadSeleccionada.getIdActividad(), estudianteSeleccionado.getIdEstudiante())) {
            
            utilidades.Alertas.mostrarAlerta("Actividad reasignada", 
                "La actividad se reasigno correctamente", 
                Alert.AlertType.INFORMATION);
            
        }else{

            utilidades.Alertas.mostrarAlerta("Error al reasignar", 
                "No se pudo reasignar la actividad", 
                Alert.AlertType.ERROR);

        }

    }

}
