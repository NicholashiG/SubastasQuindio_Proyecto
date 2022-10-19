package co.edu.uniquindio.progiii.subastasquindio.model;

import java.io.Serializable;
import java.util.ArrayList;

import co.edu.uniquindio.progiii.subastasquindio.services.IUsuario;

public class Vendedor extends Usuario implements Serializable, IUsuario {

    //Constructor vacío
    public Vendedor() {
    }

    public Vendedor(String nombreUsuario, String contrasena, String email, int edad, String id) {
        super(nombreUsuario, contrasena, email, edad);
        this.id = id;
    }

    //Variables globales
    private ArrayList<Publicacion> publicaciones = new ArrayList<>();
    private ArrayList<Articulo> articulos = new ArrayList<>();

    private String id;

    public void publicar() {

    }

    public void escogerPuja() {

    }

    public void editarPublicacion() {

    }

    public void borrarPublicacion() {

    }

    //Getters y setters
    public ArrayList<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(ArrayList<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(ArrayList<Articulo> articulos) {
        this.articulos = articulos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //Funciones de IUsuario
    @Override
    public void login() {

    }

    @Override
    public void logout() {

    }
}
