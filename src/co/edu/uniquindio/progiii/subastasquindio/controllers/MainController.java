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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnVerAnuncios.setVisible(false);

       /* try {
            guardarPublicaciones(SingletonController.getInstance().getSubastasQuindio());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        Usuario usuarioLogeado = control.getUsuarioLogeado();
    if (usuarioLogeado != null)   {
    		btnIniciarSesion.setVisible(false);
    		btnRegistrarse.setVisible(false);
            hyperlinkRegistroVendedor.setVisible(false);
    		bienvenida.setText("Bienvenido, " + usuarioLogeado.getNombreUsuario() + "!");
            if (usuarioLogeado.getClass() == Vendedor.class){
                btnVerAnuncios.setVisible(true);
            }
    	}

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
            control.setSubastasQuindio(control.cargarCasaSubastasAnunciosXML());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }





    }
    
    
    public void iniciarSesion() {
    	// ESTOY ENVIANDO EL STAGE AL SINGLETON PARA QUE LO MANEJE
    		// PORQUE NO SE ME OCURRIO MEJOR IDEA KJDKAJSDKAJ
    control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
    control.openLogin();
    control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
    }
    
    public void registrarse() {
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
        control.openRegistro();
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
    }

    public void registrarseVendedor() {
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
        control.openRegistroVendedores();
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
    }

    public void verAnuncios(){
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
        control.openCrudAnuncios();
        control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());

    }
}
 