package co.edu.uniquindio.progiii.subastasquindio.model;

import java.io.File;
import java.io.Serializable;

public class Articulo implements Serializable {

    public Articulo() {

    }

    public Articulo(String nombre, TipoPublicacion tipo, String descripcion, File foto, Vendedor vendedor) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.vendedor = vendedor;
    }

    private String nombre;
    private TipoPublicacion tipo;
    private String descripcion;
    private File foto;
    private Vendedor vendedor;

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

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public String toString() {
        return
                "nombre='" + nombre +
                ", tipo=" + tipo +
                ", descripcion='" + descripcion  +
                ", foto=" + foto.getAbsolutePath() +
                ", vendedor=" + vendedor +
                '}';
    }
}
