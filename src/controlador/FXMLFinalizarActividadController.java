package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FXMLFinalizarActividadController {
    @FXML
    private TableView<?> tvActividades;
    
    @FXML
    private TableColumn<?, ?> colTitulo;
    
    @FXML
    private TableColumn<?, ?> colFechaInicio;
    
    @FXML
    private TableColumn<?, ?> colFechaFin;
    
    @FXML
    private TableColumn<?, ?> colEstado;
    
    @FXML
    private TableColumn<?, ?> colEsfuerzo;
    
    @FXML
    private Button btnFinalizar;
    
    @FXML
    private Button btnVolver;
    
    @FXML
    private void btnFinalizar() {
        // TODO: Implement the logic for the "Finalizar" button
    }
    
    @FXML
    private void btnVolver() {
        // TODO: Implement the logic for the "Volver" button
    }

    @FXML
    private void btnSalir() {
        // Lógica para salir
    }

    @FXML
    private void hoverInSalir() {
        // Lógica para cuando el cursor entra en el botón de salir
    }

    @FXML
    private void hoverOutSalir() {
        // Lógica para cuando el cursor sale del botón de salir
    }
}
