package co.edu.uniquindio.progiii.subastasquindio.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Publicacion implements Serializable {

    //Constructor vacío
    public Publicacion() {

    }

    public Publicacion(String fechaInicial, String fechaFinal, int valorInicial, Puja pujaGanadora, Estado estado, Articulo articulo) {
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.valorInicial = valorInicial;
        this.pujaGanadora = pujaGanadora;
        this.estado = estado;
        this.articulo = articulo;
    }

    //Variables globales
    private
    String fechaInicial;
    private
    String fechaFinal;
    private int valorInicial;
    private Puja pujaGanadora;

    private Estado estado;

    private Articulo articulo;

    private ArrayList<Puja> pujas = new ArrayList<>();

    //Getters y Setters
    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
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

    public ArrayList<Puja> getPujas() {
        return pujas;
    }

    public void setPujas(ArrayList<Puja> pujas) {
        this.pujas = pujas;
    }

    @Override
    public String toString() {
        return
                "Estado: " + estado.toString().toLowerCase() +
                        ", articulo: " + articulo;
    }

}
