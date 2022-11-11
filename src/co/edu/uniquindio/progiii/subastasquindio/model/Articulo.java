package co.edu.uniquindio.progiii.subastasquindio.model;

import java.io.File;
import java.io.Serializable;

public class Articulo implements Serializable {

    public Articulo() {

    }

    public Articulo(String nombre, TipoPublicacion tipo, String descripcion, String foto, Vendedor vendedor) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.vendedor = vendedor;
    }

    private String nombre;
    private TipoPublicacion tipo;
    private String descripcion;
    private String foto;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    // METODOS SET DE STRING
    public void setTipo(String string) {
        this.tipo = TipoPublicacion.valueOf(string);

    }


    public void setVendedor(String string) {
        Vendedor vendedor = new Vendedor();
        vendedor.setNombreUsuario(string);
        this.vendedor = vendedor;
    }


    public String toStringSerializable() {
        return nombre + "@@" + tipo.toString() + "@@" + descripcion + "@@" + foto + "@@" + vendedor.getNombreUsuario() + "@@";

    }

    public String toString1() {
        return
                "nombre='" + nombre +
                        ", tipo=" + tipo +
                        ", descripcion='" + descripcion +
                        ", foto=" + foto +
                        ", vendedor=" + vendedor +
                        '}';
    }

    @Override
    public String toString() {
        return nombre;
    }


}
