package co.edu.uniquindio.progiii.subastasquindio.controllers;

import co.edu.uniquindio.progiii.subastasquindio.application.Main;
import co.edu.uniquindio.progiii.subastasquindio.exceptions.UserNotFoundException;
import co.edu.uniquindio.progiii.subastasquindio.exceptions.UsuarioException;
import co.edu.uniquindio.progiii.subastasquindio.exceptions.WrongPasswordException;
import co.edu.uniquindio.progiii.subastasquindio.model.*;
import co.edu.uniquindio.progiii.subastasquindio.persistencia.Persistencia;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class SingletonController {

    CasaSubastas subastasQuindio;
    // El login stage es el Stage del login
    // y del registro, debido a que solo uno
    // puede estar abierto al tiempo.
    // En caso de ser necesario se debe separar.
    Stage loginStage;

    // Stage de la ventana inicial y principal
    Stage mainStage;

    Stage articuloStage;
    Stage anunciosStage;


    // instancia única del controlador
    private static SingletonController instancia = null;

    // constructor privado
    private SingletonController() {
        subastasQuindio = new CasaSubastas();
    }

    // Metodo estatico para obtener la instancia

    public static SingletonController getInstance() {
        // Asegura una unica instancia
        if (instancia == null) {
            instancia = new SingletonController();
        }
        return instancia;
    }


    // Funcion de login
    public String login(String usuario, String contra) throws IOException {

        // el string de feedback es el mensaje que se muestra
        // en la ventana de login.
        String feedback = "";
        try {
            // Si esta funcion no da excepcion,
            // el usuario y contrasena son correctos
            subastasQuindio.login(usuario, contra);
            guardarCasaSubastasXML(subastasQuindio);
            guardarInicioSesionUsuarioLog(usuario);
            Main.refreshMain(mainStage);
            Main.closeWindow(loginStage);

        } catch (UserNotFoundException e) {
            feedback = "Usuario no existe!";
        } catch (WrongPasswordException e) {
            feedback = "Contraseña incorrecta";
        }
        return feedback;

    }

    // refresca la ventana de artículos cada que se crea uno, además se guarda de nuevo en persistencia
    public void nuevoArticuloRefresh() throws IOException {
        // Cada vez que se crea un articulo
        // se guarda el modelo
        guardarCasaSubastasXML(subastasQuindio);
        guardarCasaSubastasBinario(subastasQuindio);
        cargarCasaSubastasAnunciosXML();
        // Recarga las ventanas
        Main.refreshArticulos(articuloStage);
        Main.closeWindow(articuloStage);
        Main.refreshAnuncios(anunciosStage);
    }

    // refresca la ventana de anuncios cada que se crea uno, además se guarda de nuevo en persistencia
    public void nuevoAnuncioRefresh() throws IOException {
        // Cada vez que se crea un Anuncio
        // se guarda el modelo
        guardarCasaSubastasXML(subastasQuindio);
        guardarCasaSubastasBinario(subastasQuindio);
        cargarCasaSubastasAnunciosXML();
        // Recarga las ventanas
        Main.refreshAnuncios(anunciosStage);
    }

    // se guarda de nuevo en persistencia y se cierra la ventana de anuncios para que se abra la
    // ventana principal
    public void atrasAnuncios() throws IOException {

        // se guarda el modelo al salir
        guardarCasaSubastasXML(subastasQuindio);
        guardarCasaSubastasBinario(subastasQuindio);
        cargarCasaSubastasAnunciosXML();
        // Recarga las ventanas
        Main.closeWindow(anunciosStage);
        Main.refreshMain(mainStage);
    }

    public void atrasArticulos() throws IOException {
        // se guarda el modelo al salir
        guardarCasaSubastasXML(subastasQuindio);
        guardarCasaSubastasBinario(subastasQuindio);
        cargarCasaSubastasAnunciosXML();
        // Recarga las ventanas
        Main.closeWindow(articuloStage);
        Main.openCrudAnuncios();
    }

    // REGISTRA UN COMPRADOR
    public void registrarUsuario(String nombre, String contra, String email, int edad) throws IOException {

        subastasQuindio.registrarUsuario(new Comprador(nombre, contra, email, edad));
        SingletonController.guardarRegistroUsuarioLog(nombre, email);
        Main.closeWindow(loginStage);
    }

    // REGISTRA VENDEDOOR
    public void registrarVendedor(String nombre, String contrasena, String email, int edad, String id) throws IOException {
        subastasQuindio.registrarUsuario(new Vendedor(nombre, contrasena, email, edad, id));
        SingletonController.guardarRegistroUsuarioLog(nombre, email);
        Main.closeWindow(loginStage);
    }

    // Esta funcion se llama en el objeto de CasaSubastas
    public static void guardarUsuarios() throws IOException {
        Persistencia.guardarUsuarios(SingletonController.getInstance().subastasQuindio.getListaUsuarios());
    }

    // Esta función guarda cada artículo en persistencia
    public static void guardarArticulos(Articulo articulo) throws IOException {
        Persistencia.guardarArticulo(articulo);
    }

    // Esta función carga cada artículo desde persistencia
    public static void cargarArticulos(Articulo articulo) throws IOException {
        Persistencia.cargarArticulos();
    }


    // Método que crea una transacción y la guarda en persistencia y log
    public static void crearTransaccion(Comprador comprador, Vendedor vendedor, Publicacion publicacion) throws IOException {
        Transaccion transaccion = new Transaccion(comprador, vendedor, publicacion);
        SingletonController.getInstance().subastasQuindio.getListaTransacciones().add(transaccion);
        Persistencia.guardaRegistroLog("Transaccion hecha", 1, transaccion.toString());
        Persistencia.guardarTransacciones(SingletonController.getInstance().subastasQuindio.getListaTransacciones());
    }


    // CREA ARTICULO
    public void registrarArticulo(Articulo articulo) {
        Vendedor vendedor = (Vendedor) subastasQuindio.getUsuarioLogeado();
        vendedor.getArticulos().add(articulo);
    }

    // CREA UNA PUBLICACION Y LO AÑADE AL VENDEDOR
    public void registrarPublicacion(Publicacion publicacion) {
        Vendedor vendedor = (Vendedor) subastasQuindio.getUsuarioLogeado();
        vendedor.getPublicaciones().add(publicacion);
        // AÑADIR LA MISMA PUBICACION AL ARRAYLIST GENERAL DEL PROYECTO
        subastasQuindio.getListaPublicaciones().add(publicacion);
    }

    // CREA UNA PUJA Y LA GUARDA EN LA PUBLICACIÓN Y EN EL USUARIO
    public boolean registrarPuja(int valorPuja) {
        boolean realizado = false;
        try {
            Comprador comprador = (Comprador) subastasQuindio.getUsuarioLogeado();
            Publicacion publicacionSeleccionada = subastasQuindio.getPublicacionSeleccionada();
            Puja puja = new Puja(publicacionSeleccionada, comprador, valorPuja);
            comprador.getPujas().add(puja);
            publicacionSeleccionada.getPujas().add(puja);
            guardarNuevaPujaLog(puja);
            realizado = true;
            try {
                this.guardarCasaSubastasXML(this.subastasQuindio);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassCastException e) {
            try {
                realizado = false;
                throw new UsuarioException("El usuario es un vendedor, no un comprador");
            } catch (UsuarioException uE) {
            }
        }
        return realizado;
    }
    // LOGS

    public static void guardarRegistroUsuarioLog(String nombre, String email) {
        Persistencia.guardaRegistroLog("Se ha registrado un nuevo usuario", 1, nombre + "_" + email);
    }

    public static void guardarCambiosCrudLog(String mensaje, String nombreObjeto) {
        Persistencia.guardaRegistroLog(mensaje, 1, nombreObjeto);
    }

    public static void guardarInicioSesionUsuarioLog(String nombre) {
        Persistencia.guardaRegistroLog("Ha ingresado un usuario", 1, nombre);
    }

    public static void guardarNuevaPujaLog(Puja puja) {
        Persistencia.guardaRegistroLog("Se ha hecho una nueva puja", 1, puja.toStringLog());
    }

    public static void guardarExcepcion(String mensaje, String tipoExcepcion) {
        Persistencia.guardaRegistroLog("¡Ha ocurrido una excepción!: ", 2, mensaje + " " + tipoExcepcion);
    }


    // ABRIR Y CERRAR VENTANAS

    // abre la ventana de login
    public void openLogin() {
        Main.openLogin();
    }

    // abre la ventana de crudAnuncios
    public void openCrudAnuncios() {
        Main.openCrudAnuncios();
    }

    // abre la ventana de crudArticulos
    public void openCrudArticulos() {
        Main.openCrudArticulos();
    }

    // abre la ventana de registro de compradores
    public void openRegistro() {
        Main.openRegistroUsuarios();
    }

    // abre la ventana de registro de vendedores
    public void openRegistroVendedores() {
        Main.openRegistroVendedores();
    }

    // cierra cualquier ventana
    public void closeVentana() {
        Main.closeWindow(mainStage);
    }


    // GETTERS Y SETTERS


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

    // SERIALIZACION XML Y TEXTO PLANO

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

    public void guardarCasaSubastasXMLRespaldo(CasaSubastas subastasQuindio) throws IOException {
        Persistencia.guardarRecursoCasaSubastasXMLRespaldo(subastasQuindio);
    }

    public void guardarCasaSubastasBinario(CasaSubastas subastasQuindio) throws IOException {
        Persistencia.guardarRecursoCasaSubastasBinario(subastasQuindio);
    }


}
	

