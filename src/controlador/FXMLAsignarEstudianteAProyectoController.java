
package controlador;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.dao.EstudianteDAO;
import modelo.pojo.Estudiante;
import utilidades.Utilidades;

public class FXMLAsignarEstudianteAProyectoController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfMatricula;
    @FXML
    private Button btnAgregarProyecto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAgregarProyecto.setDisable(true);
        configurarListenerACampos();
    }

    @FXML
    private void btnVolver(ActionEvent event) {
        cerrarVentana();
    }

    @FXML
    private void btnAgregarAProyecto(ActionEvent event) {
        if (validarCampos()) {
            boolean confirmacion = Utilidades.mostrarAlertaConfirmacion("Confirmar registro",
                    "¿Estas seguro agregar el estudiante " + tfNombre.getText()
                            + " " + tfApellidoPaterno.getText()
                            + " " + tfApellidoMaterno.getText() + ".");

            if (confirmacion) {
                registrarEstudiante();
                cerrarVentana();
            }
        } else {
            Utilidades.mostrarAlertaSimple("Advertencia", "Ingrese datos validos", Alert.AlertType.WARNING);
        }
    }

    private void limpiarEstiloCampos() {
        tfNombre.setStyle("");
        tfApellidoPaterno.setStyle("");
        tfApellidoMaterno.setStyle("");
        tfMatricula.setStyle("");
    }

    private void registrarEstudiante() {
        String password = crearPasswordEstudiante();

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(tfNombre.getText());
        estudiante.setApellidoPaterno(tfApellidoPaterno.getText());
        estudiante.setApellidoMaterno(tfApellidoMaterno.getText());
        estudiante.setMatricula(tfMatricula.getText());
        estudiante.setIdEstadoEstudiante(2);
        estudiante.setPassword(password);

        HashMap<String, Object> respuesta = EstudianteDAO.registrarEstudiante(estudiante);
        if (!(Boolean) respuesta.get("error")) {
            Utilidades.mostrarAlertaSimple("Registro exitoso",
                    (String) respuesta.get("mensaje") + "\nLa contraseña del estudiante es: " + password,
                    Alert.AlertType.INFORMATION);
            cerrarVentana();
        } else {
            Utilidades.mostrarAlertaSimple("Error en el registro",
                    (String) respuesta.get("mensaje"), Alert.AlertType.ERROR);
        }
    }

    private void cerrarVentana() {
        Stage escenario = (Stage) tfNombre.getScene().getWindow();
        escenario.close();
    }

    private void configurarListenerACampos() {

        ChangeListener<String> cambiosEnCampos = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Verificar si todos los campos están llenos y habilitar/deshabilitar el botón
                // en consecuencia
                btnAgregarProyecto.setDisable(
                        tfNombre.getText().isEmpty() ||
                                tfApellidoPaterno.getText().isEmpty() ||
                                tfApellidoMaterno.getText().isEmpty() ||
                                tfMatricula.getText().isEmpty() ||
                                tfMatricula.getText().isEmpty());
            }
        };

        tfNombre.textProperty().addListener(cambiosEnCampos);
        tfApellidoPaterno.textProperty().addListener(cambiosEnCampos);
        tfApellidoMaterno.textProperty().addListener(cambiosEnCampos);
        tfMatricula.textProperty().addListener(cambiosEnCampos);
    }

    private boolean validarCampos() {
        boolean esValido = true;
        limpiarEstiloCampos();

        if (tfNombre.getText().isEmpty()
                || !tfNombre.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚüÜ]+( [a-zA-ZáéíóúÁÉÍÓÚüÜ]+)?")) {
            tfNombre.setStyle("-fx-border-color: red;");
            esValido = false;
        }

        if (tfApellidoPaterno.getText().isEmpty() || !tfApellidoPaterno.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚüÜ]+")) {
            tfApellidoPaterno.setStyle("-fx-border-color: red;");
            esValido = false;
        }

        if (!tfApellidoMaterno.getText().isEmpty() && !tfApellidoMaterno.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚüÜ]+")) {
            tfApellidoMaterno.setStyle("-fx-border-color: red;");
            esValido = false;
        }

        if (tfMatricula.getText().isEmpty() || !tfMatricula.getText().matches("[sS]\\d+")) {
            tfMatricula.setStyle("-fx-border-color: red;");
            esValido = false;
        }

        return esValido;

    }

    private String crearPasswordEstudiante() {
        return tfNombre.getText().toLowerCase() + tfMatricula.getText();
    }
}
