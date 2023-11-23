package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
            paneMensaje.setStyle("-fx-background-radius: 10px; -fx-background-color: #FFF5A0;");
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

        if (usuario.startsWith("s")) {
            usuarioAutenticado = EstudianteDAO.iniciarSesionEstudiante(usuario, password);
        } else {
            usuarioAutenticado = ResponsableProyectoDAO.iniciarSesionResponsable(usuario,
                    password);
        }

        if (!usuarioAutenticado.isCorrecto()) {

            lblMensaje.setText(usuarioAutenticado.getMensaje());
            paneMensaje.setStyle("-fx-background-radius: 10px; -fx-background-color: #FFA0A0;");
            paneMensaje.setVisible(true);

        } else {

            String nombre = (usuario.startsWith("s"))
                    ? usuarioAutenticado.getEstudiante().getNombre()
                    : usuarioAutenticado.getResponsableProyecto().getNombre();

            lblMensaje.setText(usuarioAutenticado.getMensaje() + " " + nombre);
            paneMensaje.setStyle("-fx-background-radius: 10px; -fx-background-color: #A0CBFF;");
            paneMensaje.setVisible(true);

            if (usuario.startsWith("s")) {
                abrirMenuEstudiante();
            } else {
                abrirMenuResponsable();
            }

        }

    }

    private void abrirMenuEstudiante() {

        Stage escenario = (Stage) vboxIniciarSesion.getScene().getWindow();

        try {

            FXMLLoader fxmlLoader = Utilidades.getFXMLLoader("/vista/FXMLMenuEstudiante.fxml");
            Parent vista = fxmlLoader.load();
            Scene escena = new Scene(vista);
            String css = Utilidades.getURLString("/vista/estilos/FXMLInicioSesion.css");

            escena.getStylesheets().add(css);
            escenario.setScene(escena);
            escenario.setTitle("Menu Estudiante");
            escenario.setResizable(false);
            escenario.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void abrirMenuResponsable() {

        Stage escenario = (Stage) vboxIniciarSesion.getScene().getWindow();

        try {

            FXMLLoader fxmlLoader = Utilidades.getFXMLLoader("/vista/FXMLMenuResponsable.fxml");
            Parent vista = fxmlLoader.load();
            Scene escena = new Scene(vista);
            String css = Utilidades.getURLString("/vista/estilos/FXMLInicioSesion.css");

            escena.getStylesheets().add(css);
            escenario.setScene(escena);
            escenario.setTitle("Menu Responsable");
            escenario.setResizable(false);
            escenario.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
