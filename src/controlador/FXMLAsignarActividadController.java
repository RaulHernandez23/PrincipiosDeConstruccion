package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;



public class FXMLAsignarActividadController {
    
    @FXML
    private TableView<String> tvActividades;
    
    @FXML
    private TableColumn<String, String> colTitulo;
    
    @FXML
    private TableColumn<String, String> colDescripcion;
    
    @FXML
    private ComboBox<String> cbEstudiantes;
    
    @FXML
    private Button btnAsignar;
    
    @FXML
    private Button btnVolver;
    
    @FXML
    private void btnAsignar() {
        // TODO: Implement button action
    }
    
    @FXML
    private void btnVolver() {
        // TODO: Implement button action
    }
    
    // Add more event handlers and methods as needed
    @FXML
    private void btnSalir(MouseEvent event) {
        // TODO: Implement the logic for exiting the application
    }

    @FXML
    private void hoverInSalir(MouseEvent event) {
        // TODO: Implement the logic for hover in effect on the exit button
    }

    @FXML
    private void hoverOutSalir(MouseEvent event) {
        // TODO: Implement the logic for hover out effect on the exit button
    }
}
