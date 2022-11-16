package co.edu.uniquindio.progiii.subastasquindio.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerRegistro implements Initializable {

    SingletonController control = SingletonController.getInstance();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
    }

    @FXML
    TextField usuario;
    @FXML
    PasswordField contra;
    @FXML
    TextField email;
    @FXML
    TextField edad;
    @FXML
    Text feedback;

    public void registrar() throws IOException {
        if (usuario.getText() != "" && contra.getText() != "" && email.getText() != "" && edad.getText() != "") {

            if (email.getText().contains("@") && isNumeric(edad.getText())) {
                // Se envia el Stage al Singleton para que lo controle todo.
                // Se puede reutilizae el mismo Stage de Login y Registro, porque solo se usa uno a la vez
                control.setLoginStage((Stage) usuario.getScene().getWindow());
                control.registrarUsuario(usuario.getText(), contra.getText(), email.getText(), Integer.parseInt(edad.getText()));
                try {
                    control.guardarCasaSubastasXML(control.subastasQuindio);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else feedback.setText("El correo o edad son erroneos");
        } else {
            feedback.setText("Los campos no pueden estar vacios");
        }
    }

    // Funcion que determina si un string es numerico o no
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
