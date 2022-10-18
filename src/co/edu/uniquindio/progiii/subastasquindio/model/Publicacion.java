package co.edu.uniquindio.progiii.subastasquindio.model;

import java.io.File;

import java.io.Serializable;
import java.util.Date;

public class Publicacion implements Serializable {

    //Constructor vacío
    public Publicacion() {

    }

    public Publicacion(Date fechaInicial, Date fechaFinal, int valorInicial, Puja pujaGanadora, Estado estado) {
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.valorInicial = valorInicial;
        this.pujaGanadora = pujaGanadora;
        this.estado = estado;
    }

    //Variables globales
    private Date fechaInicial;
    private Date fechaFinal;
    private int valorInicial;
    private Puja pujaGanadora;

    private Estado estado;

    //Getters y Setters
    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
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

    @Override
    public String toString() {
        return
                "fechaInicial=" + fechaInicial +
                ", fechaFinal=" + fechaFinal +
                ", valorInicial=" + valorInicial +
                ", pujaGanadora=" + pujaGanadora +
                ", estado=" + estado;
    }
}
