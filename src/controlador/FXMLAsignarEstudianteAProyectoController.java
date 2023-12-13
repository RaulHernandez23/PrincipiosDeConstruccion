/*
 * Nombre del archivo: FXMLAsignarEstudianteAProyectoController.java
 * Autor: Miguel Angel Morales Cruz
 * Paquete: controlador
 * Fecha de creación: 20/11/2023
 * Fecha de modificación: 11/12/2023
 * Descripción: Controlador para la ventana de asignar estudiante a proyecto.
 */
package controlador;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.dao.EstudianteDAO;
import modelo.pojo.Estudiante;
import utilidades.Constantes;
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

    private Integer idProyecto;
    @FXML
    private ImageView ivSalir;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnAgregarProyecto.setDisable(true);
        configurarListenerACampos();

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
    private void clicAgregarAProyecto(ActionEvent event) {

        if (validarCampos()) {

            boolean confirmacion = Utilidades
                    .mostrarAlertaConfirmacion("Confirmar registro",
                            "¿Estas seguro agregar el estudiante "
                                    + tfNombre.getText()
                                    + " " + tfApellidoPaterno.getText()
                                    + " " + tfApellidoMaterno.getText() + ".");

            if (confirmacion) {

                registrarEstudiante();
                cerrarVentana();

            }

        } else {
            Utilidades.mostrarAlertaSimple("Advertencia",
                    Constantes.MENSAJE_DATOS_INVALIDOS,
                    Alert.AlertType.WARNING);
        }

    }

    public void inicializarVentana(Integer idProyecto) {
        this.idProyecto = idProyecto;
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
        estudiante.setIdEstadoEstudiante(1);
        estudiante.setPassword(password);
        estudiante.setIdProyecto(idProyecto);

        HashMap<String, Object> respuesta = EstudianteDAO
                .registrarEstudiante(estudiante);

        if (!(Boolean) respuesta.get("error")) {

            Utilidades.mostrarAlertaSimple("Registro exitoso",
                    (String) respuesta.get("mensaje")
                            + "\nLa contraseña del estudiante es: " + password,
                    Alert.AlertType.INFORMATION);
            cerrarVentana();

        } else {
            Utilidades.mostrarAlertaSimple("Error en el registro",
                    (String) respuesta.get("mensaje"),
                    Alert.AlertType.ERROR);
        }

    }

    private void cerrarVentana() {

        Stage escenario = (Stage) tfNombre.getScene().getWindow();
        escenario.close();

    }

    private void configurarListenerACampos() {

        ChangeListener<String> cambiosEnCampos = (observable,
                oldValue,
                newValue) -> verificarCampos();

        tfNombre.textProperty().addListener(cambiosEnCampos);
        tfApellidoPaterno.textProperty().addListener(cambiosEnCampos);
        tfApellidoMaterno.textProperty().addListener(cambiosEnCampos);
        tfMatricula.textProperty().addListener(cambiosEnCampos);

    }

    private void verificarCampos() {

        btnAgregarProyecto.setDisable(
                tfNombre.getText().isEmpty()
                        || tfApellidoPaterno.getText().isEmpty()
                        || tfApellidoMaterno.getText().isEmpty()
                        || tfMatricula.getText().isEmpty()
                        || tfMatricula.getText().isEmpty());

    }

    private boolean validarCampos() {

        boolean esValido = true;
        limpiarEstiloCampos();

        if (tfNombre.getText().isEmpty()
                || !tfNombre.getText()
                        .matches("[a-zA-ZáéíóúÁÉÍÓÚüÜ]+"
                                + "( [a-zA-ZáéíóúÁÉÍÓÚüÜ]+)?")) {
            tfNombre.setStyle("-fx-border-color: red;");
            esValido = false;
        }

        if (tfApellidoPaterno.getText().isEmpty()
                || !tfApellidoPaterno.getText()
                        .matches("[a-zA-ZáéíóúÁÉÍÓÚüÜ]+")) {

            tfApellidoPaterno.setStyle("-fx-border-color: red;");
            esValido = false;

        }

        if (!tfApellidoMaterno.getText().isEmpty()
                && !tfApellidoMaterno.getText()
                        .matches("[a-zA-ZáéíóúÁÉÍÓÚüÜ]+")) {

            tfApellidoMaterno.setStyle("-fx-border-color: red;");
            esValido = false;

        }

        if (tfMatricula.getText().isEmpty()
                || !tfMatricula.getText().matches("[sS]\\d+")) {

            tfMatricula.setStyle("-fx-border-color: red;");
            esValido = false;

        }

        return esValido;

    }

    private String crearPasswordEstudiante() {
        return tfNombre.getText().toLowerCase() + tfApellidoPaterno.getText();
    }

}
