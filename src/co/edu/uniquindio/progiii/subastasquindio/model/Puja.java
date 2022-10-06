package co.edu.uniquindio.progiii.subastasquindio.model;

import java.io.Serializable;

public class Puja implements Serializable {

    //Constructor vacío
    public Puja() {
    }

    //Variables globales
    private Publicacion publicacion;
    private Comprador comprador;
    private Double dineroOfrecido;

    //Getters y Setters
    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Double getDineroOfrecido() {
        return dineroOfrecido;
    }

    public void setDineroOfrecido(Double dineroOfrecido) {
        this.dineroOfrecido = dineroOfrecido;
    }
}
