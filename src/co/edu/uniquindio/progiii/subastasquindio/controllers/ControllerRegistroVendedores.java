package co.edu.uniquindio.progiii.subastasquindio.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRegistroVendedores implements Initializable {
    SingletonController control = SingletonController.getInstance();


    @FXML
    TextField usuario;
    @FXML
    PasswordField contra;
    @FXML
    TextField email;
    @FXML
    TextField edad;
    @FXML
    TextField id;
    @FXML
    Text feedback;


    public void registrar() throws NumberFormatException, IOException {
        if (usuario.getText() != "" && contra.getText() != "" && email.getText() != "" && edad.getText() != "" && id.getText() != "") {

            if (email.getText().contains("@") && isNumeric(edad.getText()) && isNumeric(id.getText())) {
                // Se envia el Stage al Singleton para que lo controle todo.
                // Se puede reutilizae el mismo Stage de Login y Registro, porque solo se usa uno a la vez
                control.setLoginStage((Stage) usuario.getScene().getWindow());
                control.registrarVendedor(usuario.getText(), contra.getText(), email.getText(), Integer.parseInt(edad.getText()), id.getText());
                control.serializarXMLServidor();;

            } else feedback.setText("El correo, edad o id son erroneos");

        } else {
            feedback.setText("Los campos no pueden estar vacios");
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
