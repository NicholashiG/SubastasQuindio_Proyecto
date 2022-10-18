package co.edu.uniquindio.progiii.subastasquindio.controllers;

import java.io.IOException;

import co.edu.uniquindio.progiii.subastasquindio.application.Main;
import co.edu.uniquindio.progiii.subastasquindio.model.CasaSubastas;
import co.edu.uniquindio.progiii.subastasquindio.model.Usuario;
import javafx.stage.Stage;

public class SingletonController {

	CasaSubastas subastasQuindio;
	Stage loginStage;
	Stage mainStage;



	// Static variable single_instance of type Singleton 
    private static SingletonController instancia = null; 
  
    // Declaring a variable of type String 
  
    // Constructor of this class 
    // Here private constructor is used to 
    // restricted to this class itself 
    private SingletonController() 
    { 
    	subastasQuindio = new CasaSubastas();
    } 
  
    // Method 
    // Static method to create instance of Singleton class 
    public static SingletonController getInstance() 
    { 
        // To ensure only one instance is created 
        if (instancia == null) { 
        	instancia = new SingletonController(); 
        } 
        return instancia; 
    } 
    
    
    public void openLogin() {
    	Main.openLogin();
    }
    
	public void openRegistro() {
    	Main.openRegistro();
	}
    
    public String login(String usuario, String contra) {

    	if (subastasQuindio.login(usuario, contra)) {

    		Main.refreshMain(mainStage);
    		Main.closeLogin(loginStage);
    	} else { // Aqui va la excepcion de Usuario no existe
    		return "Usuario no existe";
    	}
    	return "";
    }
    	
    // REGISTRA UN USUARIO, NO UN COMPRADOR O VENDEDOR
    // 	CAMBIAR LUEGO
	public void registrar(String nombre, String contra, String email, int edad) {

		subastasQuindio.registrarUsuario(new Usuario(nombre, contra, email, edad));
		Main.closeLogin(loginStage);
	}

	public CasaSubastas getSubastasQuindio() {
		return subastasQuindio;
	}


    public void setSubastasQuindio(CasaSubastas subastasQuindio) {
    	this.subastasQuindio = subastasQuindio;
    }
    
	public Stage getLoginStage() {
		return loginStage;
	}

	public void setLoginStage(Stage loginStage) {
		this.loginStage = loginStage;
	}

	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	public Usuario getUsuarioLogeado() {
		return subastasQuindio.getUsuarioLogeado();
		
	}




	
	
	
} 
	

