package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Vendedor extends Usuario implements Serializable {

    //Constructor vacío
    public Vendedor() {
    }

    //Variables globales
    private ArrayList<Publicacion> publicaciones = new ArrayList<>();

    public void publicar(){

    }

    public void escogerPuja(){

    }

    public void editarPublicacion(){

    }

    public void borrarPublicacion(){

    }

    //Getters y setters
    public ArrayList<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(ArrayList<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }
}
