package co.edu.uniquindio.progiii.subastasquindio.exceptions;
import co.edu.uniquindio.progiii.subastasquindio.controllers.SingletonController;

public class CannotSelectPujaGanadora extends Exception{

    public CannotSelectPujaGanadora() {
    }

    public CannotSelectPujaGanadora(String message) {
        super(message);
        SingletonController.guardarExcepcion(message, this.toString());
    }
}
