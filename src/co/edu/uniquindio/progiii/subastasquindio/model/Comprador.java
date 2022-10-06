package co.edu.uniquindio.progiii.subastasquindio.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Comprador extends Usuario implements Serializable, IUsuario {

    //Constructor vacío
    public Comprador() {
    }

    //Variables globales
    private ArrayList<Puja> pujas = new ArrayList<>();

    public void pujar(){

    }

    public void borrarPuja(){

    }

    //Getters y Setters
    public ArrayList<Puja> getPujas() {
        return pujas;
    }

    public void setPujas(ArrayList<Puja> pujas) {
        this.pujas = pujas;
    }

    //Funciones de IUsuario
    @Override
    public void login() {

    }

    @Override
    public void logout() {

    }
}
