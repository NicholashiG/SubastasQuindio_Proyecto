package co.edu.uniquindio.progiii.subastasquindio.model;

import java.io.Serializable;

public class Puja implements Serializable {

    //Constructor vacío
    public Puja() {
    }

    //Constructor con variables
    public Puja(Publicacion publicacion, Comprador comprador, int dineroOfrecido) {
        this.publicacion = publicacion;
        this.comprador = comprador;
        this.dineroOfrecido = dineroOfrecido;
    }

    //Variables globales
    private Publicacion publicacion;
    private Comprador comprador;
    private int dineroOfrecido;

    private boolean isGanadora;

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

    public int getDineroOfrecido() {
        return dineroOfrecido;
    }

    public void setDineroOfrecido(int dineroOfrecido) {
        this.dineroOfrecido = dineroOfrecido;
    }

    public boolean isGanadora() {
        return isGanadora;
    }

    public void setGanadora(boolean ganadora) {
        isGanadora = ganadora;
    }

    public String toStringLog() {
        return "Puja hecha por: " + comprador.getNombreUsuario() + " a el artículo " + publicacion.getArticulo().getNombre() + " por " + dineroOfrecido;
    }

    @Override
    public String toString() {
        return comprador.getNombreUsuario() + " $" + dineroOfrecido;
    }
}
