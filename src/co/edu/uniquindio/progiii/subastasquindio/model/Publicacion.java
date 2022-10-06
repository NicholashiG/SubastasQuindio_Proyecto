package co.edu.uniquindio.progiii.subastasquindio.model;

import java.io.File;

import java.io.Serializable;
import java.util.Date;

public class Publicacion implements Serializable {

    //Constructor vacío
    public Publicacion() {
    }

    //Variables globales
    private String nombre;
    private TipoPublicacion tipo;
    private String descripcion;
    private File foto;
    private Estado estado;
    private Vendedor vendedor;
    private Date fechaInicial;
    private Date fechaFinal;
    private int valorInicial;
    private Puja pujaGanadora;

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoPublicacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoPublicacion tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

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
}
