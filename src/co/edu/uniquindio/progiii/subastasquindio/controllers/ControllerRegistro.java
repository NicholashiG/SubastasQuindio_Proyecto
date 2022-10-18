package co.edu.uniquindio.progiii.subastasquindio.controllers;

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

	@FXML TextField usuario;
	@FXML PasswordField contra;
	@FXML TextField email;
	@FXML TextField edad;
	@FXML Text feedback;
	public void registrar() {
		if (usuario.getText() != "" && contra.getText() != "" && email.getText() != "" && edad.getText() != "") {
			
	    	if (email.getText().contains("@") && isNumeric(edad.getText())) {
				// ESTOY ENVIANDO EL STAGE AL SINGLETON PARA QUE MANEJE TODO
	    		// VOY A USAR EL MISMO LOGIN STAGE PORQUE SOLO SE PUEDE ABRIR
	    			// UNO A LA VEZ Y ADEMAS ME DA PEREZA HACER OTRO
				control.setLoginStage( (Stage) usuario.getScene().getWindow() );
				control.registrar(usuario.getText(), contra.getText(), email.getText(), Integer.parseInt(edad.getText()));

	    	} else feedback.setText("El correo o edad son erroneos");
			

			

		}
		else { feedback.setText("Los campos no pueden estar vacios"); }
	}
	
	// funcion copiada hehe
	public static boolean isNumeric(String str) { 
		  try {  
		    Integer.parseInt(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	
}
