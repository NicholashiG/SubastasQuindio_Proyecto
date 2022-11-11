package co.edu.uniquindio.progiii.subastasquindio.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Transaccion implements Serializable {

    int id;
    Usuario comprador;
    Usuario vendedor;
    Publicacion publicacion;
    LocalDate fecha;
    LocalDateTime hora;


    public Transaccion() {

    }

    public Transaccion(Usuario comprador, Usuario vendedor, Publicacion publicacion) {
        super();
        this.id = (int) (Math.random() * 1000);
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.publicacion = publicacion;
        this.fecha = LocalDate.now();
        this.hora = LocalDateTime.now();
    }


    @Override
    public String toString() {
        return id + "@@" + fecha + "@@" + hora.getHour() + ":" + hora.getMinute() + "@@" + comprador.getNombreUsuario() + "@@" + publicacion.getArticulo().getNombre() + "@@" + vendedor.getNombreUsuario();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transaccion other = (Transaccion) obj;
        return id == other.id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setFecha(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.fecha = LocalDate.parse(string, formatter);
    }

    public void setHora(String string) {
        LocalDateTime dateTime = LocalDateTime.parse(string, DateTimeFormatter.ofPattern("HH:mm"));
        this.hora = dateTime;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public void setComprador(String string) {
        Comprador comprador = new Comprador();
        comprador.setNombreUsuario(string);
        this.comprador = comprador;
    }

    public void setVendedor(String string) {
        Vendedor vendedor = new Vendedor();
        vendedor.setNombreUsuario(string);
        this.vendedor = vendedor;
    }


    public void setPublicacion(String string) {
        Articulo articulo = new Articulo();
        articulo.setNombre(string);
        Publicacion publicacion = new Publicacion();
        publicacion.setArticulo(articulo);
        this.publicacion = publicacion;
    }


}
