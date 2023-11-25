package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import modelo.dao.EstudianteDAO;
import modelo.dao.ResponsableProyectoDAO;
import modelo.pojo.RespuestaInicioSesion;
import utilidades.Utilidades;

public class FXMLInicioSesionController implements Initializable {

    @FXML
    private VBox vboxIniciarSesion;
    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Pane paneMensaje;
    @FXML
    private Label lblMensaje;
    @FXML
    private Button btnIniciarSesion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paneMensaje.setVisible(false);
    }

    @FXML
    private void btnIniciarSesionClic(ActionEvent event) {

        if (!verificarCamposLlenos()) {

            lblMensaje.setText("Por favor digita el usuario y/o la contraseña");

            ObservableList<String> estilo = paneMensaje.getStyleClass();

            if (estilo.contains("mensajeError")) {

                estilo.remove("mensajeError");
                estilo.add("mensajeAdvertencia");

            }

            paneMensaje.setVisible(true);

        } else {
            verificarConexion(tfUsuario.getText(), pfPassword.getText());
        }

    }

    private boolean verificarCamposLlenos() {
        return !tfUsuario.getText().isEmpty() && !pfPassword.getText().isEmpty();
    }

    private void verificarConexion(String usuario, String password) {

        RespuestaInicioSesion usuarioAutenticado = null;

        if (usuario.toLowerCase().startsWith("s")) {
            usuarioAutenticado = EstudianteDAO.iniciarSesionEstudiante(usuario, password);
        } else {
            usuarioAutenticado = ResponsableProyectoDAO.iniciarSesionResponsable(usuario,
                    password);
        }

        if (!usuarioAutenticado.isCorrecto()) {

            lblMensaje.setText(usuarioAutenticado.getMensaje());

            ObservableList<String> estilo = paneMensaje.getStyleClass();

            if (estilo.contains("mensajeAdvertencia")) {

                estilo.remove("mensajeAdvertencia");
                estilo.add("mensajeError");

            }

            paneMensaje.setVisible(true);

        } else {

            if (usuario.toLowerCase().startsWith("s")) {
                abrirMenuEstudiante();
            } else {
                abrirMenuResponsable();
            }

        }

    }

    private void abrirMenuEstudiante() {

        Stage escenario = (Stage) vboxIniciarSesion.getScene().getWindow();

        Utilidades.inicializarVentana(escenario,
                "/vista/FXMLMenuEstudiante.fxml",
                "/vista/estilos/escenaMenu.css",
                "Menu Estudiante", false);

    }

    private void abrirMenuResponsable() {

        Stage escenario = (Stage) vboxIniciarSesion.getScene().getWindow();

        Utilidades.inicializarVentana(escenario,
                "/vista/FXMLMenuResponsable.fxml",
                "/vista/estilos/escenaMenu.css",
                "Menu Responsable", false);

    }

}
