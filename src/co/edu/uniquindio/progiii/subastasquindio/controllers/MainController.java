package co.edu.uniquindio.progiii.subastasquindio.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.progiii.subastasquindio.model.Usuario;

public class MainController implements Initializable{

	SingletonController control = SingletonController.getInstance();
	
    @FXML
    private Button btnIniciarSesion;
    @FXML
    private Button btnRegistrarse;
    @FXML
    private Button btnVerOfertas;
    @FXML
    private Text bienvenida;
    @FXML
    private Hyperlink hyperlinkVendedor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    Usuario usuarioLogeado = control.getUsuarioLogeado();
    if (usuarioLogeado != null)   {
    		btnIniciarSesion.setVisible(false);
    		btnRegistrarse.setVisible(false);
    		hyperlinkVendedor.setVisible(false);
    		bienvenida.setText("Bienvenido " + usuarioLogeado.getNombreUsuario() + "!  ");
    	}
    }
    
    
    public void iniciarSesion() {
    	// ESTOY ENVIANDO EL STAGE AL SINGLETON PARA QUE LO MANEJE
    		// PORQUE NO SE ME OCURRIO MEJOR IDEA KJDKAJSDKAJ
    control.setMainStage((Stage) btnIniciarSesion.getScene().getWindow());
    control.openLogin();
    }
}
 