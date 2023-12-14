/*
 * Nombre del archivo: FXMLAsignarEstudianteAProyectoController.java
 * Autor: Miguel Angel Morales Cruz
 * Paquete: controlador
 * Fecha de creación: 20/11/2023
 * Fecha de modificación: 12/12/2023
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

    @FXML
    private ImageView ivSalir;

    private Integer idProyecto;

    private Integer idPeriodoProyecto;

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

    public void inicializarVentana(Integer idProyecto,
            Integer idPeriodoProyecto) {
        this.idProyecto = idProyecto;
        this.idPeriodoProyecto = idPeriodoProyecto;
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
                .registrarEstudianteYAsociarPeriodoEscolar(
                        estudiante, idPeriodoProyecto);

        if (!(Boolean) respuesta.get("error")) {

            Utilidades.mostrarAlertaSimple("Registro exitoso",
                    (String) respuesta.get("mensaje")
                            + "\nLa contraseña del estudiante es: "
                            + password,
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

        String nombre = tfNombre.getText().trim();
        if (nombre.isEmpty() || !nombre.matches(
                "^[a-zA-ZáéíóúÁÉÍÓÚüÜ]{3,20}$")) {
            tfNombre.setStyle("-fx-border-color: red;");
            esValido = false;
        }

        String apellidoPaterno = tfApellidoPaterno.getText().trim();
        if (apellidoPaterno.isEmpty() || !apellidoPaterno.matches(
                "^[a-zA-ZáéíóúÁÉÍÓÚüÜ]{3,40}$")) {
            tfApellidoPaterno.setStyle("-fx-border-color: red;");
            esValido = false;
        }

        String apellidoMaterno = tfApellidoMaterno.getText().trim();
        if (!apellidoMaterno.isEmpty() && !apellidoMaterno.matches(
                "^[a-zA-ZáéíóúÁÉÍÓÚüÜ]{3,40}$")) {
            tfApellidoMaterno.setStyle("-fx-border-color: red;");
            esValido = false;
        }

        String matricula = tfMatricula.getText().trim();
        if (matricula.isEmpty() || !matricula.matches("^[sS]\\d{8}$")) {
            tfMatricula.setStyle("-fx-border-color: red;");
            esValido = false;
        }

        return esValido;
    }

    private String crearPasswordEstudiante() {
        return tfNombre.getText().toLowerCase() + tfApellidoPaterno.getText();
    }

}
