package co.edu.uniquindio.progiii.subastasquindio.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerLogin implements Initializable {

	SingletonController control = SingletonController.getInstance();
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

	@FXML TextField usuario;
	@FXML PasswordField contra;
	@FXML Text feedback;
	public void login() {
		if (usuario.getText() != "" && contra.getText() != "") {
	    	// ESTOY ENVIANDO EL STAGE AL SINGLETON PARA QUE MANEJE TODO
			control.setLoginStage( (Stage) usuario.getScene().getWindow() );
			control.login(usuario.getText(), contra.getText());  
		}
		else { feedback.setText("Los campos no pueden estar vacios"); }
	}
	
}