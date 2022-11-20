package co.edu.uniquindio.progiii.subastasquindio.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import co.edu.uniquindio.progiii.subastasquindio.controllers.SingletonController;
import co.edu.uniquindio.progiii.subastasquindio.exceptions.TooManyBidsException;
import co.edu.uniquindio.progiii.subastasquindio.exceptions.UsuarioException;

public class Publicacion implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

    
    public int registrarPuja(int valorPuja) {
        /* Valores de realizado: 0. Error
                                 1. Correcto
                                 2. Error usuario vendedor
                                 3. Error muchas pujas
                                 4. Error puja no puede ser menor a la anterior
                                 5. Error ya tiene una puja con el mismo valor

        */
        int realizado = 0;
        SingletonController control = SingletonController.getInstance();
        try {
        	
            Comprador comprador = (Comprador) control.getSubastasQuindio().getUsuarioLogeado();
            if (comprador.getPujas().size() > 3) {
                throw new TooManyBidsException("El usuario " + comprador.getNombreUsuario() + " tiene 3 pujas");
            }
            if (valorPuja < 
            	this.getPujas().get( this.getPujas().size() - 1 ).getDineroOfrecido())
            	return 4;
            
            if (revisarDuplicado(valorPuja) != -1) return 5;
            
            Puja puja = new Puja(this, comprador, valorPuja);
            comprador.getPujas().add(puja);
            this.getPujas().add(puja);
            control.guardarNuevaPujaLog(puja);
            realizado = 1;
            control.serializarXMLServidor();
            control.cargarXMLServidor();
        } catch (ClassCastException e) {
            try {
            	System.out.println(control.getUsuarioLogeado().getNombreUsuario());
                realizado = 2;
                throw new UsuarioException("El usuario es un vendedor, no un comprador");
            } catch (UsuarioException uE) {
            }
        } catch (TooManyBidsException e) {
            try {
                realizado = 3;
                throw new TooManyBidsException();
            } catch (TooManyBidsException e1) {
            }

        }
        return realizado;
    }

    // Devuelve el indice de la posicion de la puja en el array
    public int filtrarPuja(Puja puja) {
    	int indice = -1;
    	for (int i = 0; i < pujas.size(); i++) {
			if (pujas.get(i).equals(puja))
				indice = i;
		}
    	return indice;
    }
    
    // Comprueba un duplicado basado en el valor a pujar.
    private int revisarDuplicado(int valorPuja) {
    	// devuelve -1 si no encuentra
    	int indice = -1;
    	for (Puja puja: pujas) {
    		// Si ya hay una puja de mismo valor, sumar 1 al indice
    		System.out.println(indice);
			if (puja.getDineroOfrecido() == valorPuja) indice++;
		}
    	return indice;
    }
    
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
