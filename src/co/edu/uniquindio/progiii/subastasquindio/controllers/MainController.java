package co.edu.uniquindio.progiii.subastasquindio.controllers;

import co.edu.uniquindio.progiii.subastasquindio.model.Vendedor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.progiii.subastasquindio.model.Usuario;

import static co.edu.uniquindio.progiii.subastasquindio.persistencia.Persistencia.guardarPublicaciones;

public class MainController implements Initializable{

	SingletonController control = SingletonController.getInstance();
	
    @FXML
    private Button btnIniciarSesion;
    @FXML
    private Button btnRegistrarse;
    @FXML
    private Button btnVerOfertas;
    @FXML
    private Button btnVerAnuncios;
    @FXML
    private Text bienvenida;
    @FXML
    private Hyperlink hyperlinkRegistroVendedor;

    @Override
    
    // ESTA FUNCION SE EJECUTA AL INCIO DE LA APLICACION
    
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnVerAnuncios.setVisible(false);

        Usuario usuarioLogeado = control.getUsuarioLogeado();
        // Pregunta si hay un usuario logeado
    if (usuarioLogeado != null)   {
    		// si esta logeado hace invisible los botones
    		// de registro y demas.
    		btnIniciarSesion.setVisible(false);
    		btnRegistrarse.setVisible(false);
            hyperlinkRegistroVendedor.setVisible(false);
    		bienvenida.setText("Bienvenido, " + usuarioLogeado.getNombreUsuario() + "!");
            if (usuarioLogeado.getClass() == Vendedor.class){
            	// si es vendedor agrega opciones propias del vendedor
                btnVerAnuncios.setVisible(true);
            }
    	}

    	// SERIALIZACION AL INICIAR.
    	
        try {
            control.setSubastasQuindio(control.cargarCasaSubastasAnunciosXML());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            control.guardarCasaSubastasXML(control.subastasQuindio);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            control.guardarCasaSubastasXMLRespaldo(control.subastasQuindio);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            control.setSubastasQuindio(control.cargarCasaSubastasAnunciosXML());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    
    
    public void iniciarSesion() {
    	// ENVIO EL STAGE AL SINGLETON
    control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
    control.openLogin();
    control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
    }
    
    public void registrarse() {
    	// ENVIO EL STAGE AL SINGLETON
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
        control.openRegistro();
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
    }

    public void registrarseVendedor() {
    	// ENVIO EL STAGE AL SINGLETON
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
        control.openRegistroVendedores();
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
    }

    public void verAnuncios(){
    	// ENVIO EL STAGE AL SINGLETON
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
        control.openCrudAnuncios();
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());

    }
}
 