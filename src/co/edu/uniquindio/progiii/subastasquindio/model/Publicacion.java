package co.edu.uniquindio.progiii.subastasquindio.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Publicacion implements Serializable {

    //Constructor vacío
    public Publicacion() {

    }

    public Publicacion(LocalDate fechaInicial, LocalDate fechaFinal, int valorInicial, Puja pujaGanadora, Estado estado, Articulo articulo) {
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.valorInicial = valorInicial;
        this.pujaGanadora = pujaGanadora;
        this.estado = estado;
        this.articulo = articulo;
    }

    //Variables globales
    private
    LocalDate fechaInicial;
    private
    LocalDate fechaFinal;
    private int valorInicial;
    private Puja pujaGanadora;

    private Estado estado;

    private Articulo articulo;

    //Getters y Setters
    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(int valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Puja getPujaGanadora() {
        return pujaGanadora;
    }

    public void setPujaGanadora(Puja pujaGanadora) {
        this.pujaGanadora = pujaGanadora;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    @Override
    public String toString() {
        return
                "Estado: " + estado.toString().toLowerCase() +
                ", articulo: " + articulo;
    }
}
