package co.edu.uniquindio.progiii.subastasquindio.controllers;

import co.edu.uniquindio.progiii.subastasquindio.application.Main;
import co.edu.uniquindio.progiii.subastasquindio.model.*;
import co.edu.uniquindio.progiii.subastasquindio.persistencia.Persistencia;
import javafx.stage.Stage;

import java.io.IOException;

public class SingletonController {

	CasaSubastas subastasQuindio;
	Stage loginStage;
	Stage mainStage;

	Stage articuloStage;
	Stage anunciosStage;



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

	public static void guardarRegistroUsuarioLog(String nombre, String email) {
		Persistencia.guardaRegistroLog("Se ha registrado un nuevo usuario", 1, nombre+"_"+email);
	}
	public static void guardarCambiosCrudLog(String mensaje, String nombreObjeto) {
		Persistencia.guardaRegistroLog(mensaje, 1, nombreObjeto);
	}
	public static void guardarInicioSesionUsuarioLog(String nombre) {
		Persistencia.guardaRegistroLog("Ha ingresado un usuario", 1, nombre);
	}

	public static void guardarExcepcion(String mensaje, String tipoExcepcion) {
		Persistencia.guardaRegistroLog("¡Ha ocurrido una excepción!: ", 2, mensaje+" "+tipoExcepcion);
	}


	public void openLogin() {
    	Main.openLogin();
    }
	public void openCrudAnuncios() {
		Main.openCrudAnuncios();
	}
	public void openCrudArticulos() {
		Main.openCrudArticulos();
	}public void closeVentana() {
		Main.closeWindow(mainStage);
	}
	public void openRegistro() {
    	Main.openRegistroUsuarios();
	}
	public void openRegistroVendedores() {
    	Main.openRegistroVendedores();
	}

    public String login(String usuario, String contra) throws IOException {

    	if (subastasQuindio.login(usuario, contra)) {
			guardarCasaSubastasXML(subastasQuindio);
    		Main.refreshMain(mainStage);
    		Main.closeWindow(loginStage);
    	} else { // Aqui va la excepcion de Usuario no existe
    		return "Usuario no existe";

		}
    	return "";
    }

	public void nuevoArticuloRefresh() throws IOException {
			guardarCasaSubastasXML(subastasQuindio);
			guardarCasaSubastasBinario(subastasQuindio);
			cargarCasaSubastasAnunciosXML();
			Main.refreshArticulos(articuloStage);
			Main.closeWindow(articuloStage);
			Main.refreshAnuncios(anunciosStage);
	}
	public void nuevoAnuncioRefresh() throws IOException {
			guardarCasaSubastasXML(subastasQuindio);
			guardarCasaSubastasBinario(subastasQuindio);
		    cargarCasaSubastasAnunciosXML();
			Main.refreshAnuncios(anunciosStage);
	}
	public void atrasAnuncios() throws IOException {
			guardarCasaSubastasXML(subastasQuindio);
			guardarCasaSubastasBinario(subastasQuindio);
		    cargarCasaSubastasAnunciosXML();
			Main.closeWindow(anunciosStage);
			Main.refreshMain(mainStage);
	}
	public void atrasArticulos() throws IOException {
			guardarCasaSubastasXML(subastasQuindio);
			guardarCasaSubastasBinario(subastasQuindio);
		    cargarCasaSubastasAnunciosXML();
			Main.closeWindow(articuloStage);
			Main.openCrudAnuncios();
	}
    	
    // REGISTRA UN USUARIO, NO UN COMPRADOR O VENDEDOR
    // 	CAMBIAR LUEGO
	public void registrarUsuario(String nombre, String contra, String email, int edad) throws IOException {
		subastasQuindio.registrarComprador(new Comprador(nombre, contra, email, edad));
		SingletonController.guardarRegistroUsuarioLog(nombre,email);
		Main.closeWindow(loginStage);
	}

	public static void guardarUsuario(Usuario usuario) throws IOException {
		Persistencia.guardarUsuarios(SingletonController.getInstance().subastasQuindio.getListaUsuarios());
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

	public Stage getArticuloStage() {
		return articuloStage;
	}

	public void setArticuloStage(Stage articuloStage) {
		this.articuloStage = articuloStage;
	}

	public Stage getAnunciosStage() {
		return anunciosStage;
	}

	public void setAnunciosStage(Stage anunciosStage) {
		this.anunciosStage = anunciosStage;
	}

	public Usuario getUsuarioLogeado() {
		return subastasQuindio.getUsuarioLogeado();
		
	}


	public void registrarVendedor(String nombre, String contrasena, String email, int edad, String id) {
		subastasQuindio.registrarVendedor(new Vendedor(nombre, contrasena, email, edad, id));
		SingletonController.guardarRegistroUsuarioLog(nombre,email);
		Main.closeWindow(loginStage);
	}

	public void registrarArticulo(Articulo articulo){
		Vendedor vendedor = (Vendedor) subastasQuindio.getUsuarioLogeado();
		vendedor.getArticulos().add(articulo);
	}

	public void registrarPublicacion(Publicacion publicacion){
		Vendedor vendedor = (Vendedor) subastasQuindio.getUsuarioLogeado();
		vendedor.getPublicaciones().add(publicacion);
	}

	public void guardarAnunciosXML(CasaSubastas subastasQuindio) throws IOException {
		Persistencia.guardarPublicaciones(subastasQuindio);
	}

	public CasaSubastas cargarCasaSubastasAnunciosXML() throws IOException {
		CasaSubastas casaSubastas = Persistencia.cargarRecursoCasaSubastasXML();
		return casaSubastas;
	}
	public CasaSubastas cargarCasaSubastasAnunciosBinario() throws IOException {
		CasaSubastas casaSubastas = Persistencia.cargarRecursoCasaSubastasBinario();
		return casaSubastas;
	}
	public void guardarCasaSubastasXML(CasaSubastas subastasQuindio) throws IOException {
		Persistencia.guardarRecursoCasaSubastasXML(subastasQuindio);
	}
	public void guardarCasaSubastasBinario(CasaSubastas subastasQuindio) throws IOException {
		Persistencia.guardarRecursoCasaSubastasBinario(subastasQuindio);
	}


}
	

