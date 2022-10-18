package co.edu.uniquindio.progiii.subastasquindio.exceptions;


// Exvepcion para cuando se ingresa un valor
// invalido como puja (para comprador y vendedor)

import co.edu.uniquindio.progiii.subastasquindio.controllers.SingletonController;

public class InvalidBidException extends Exception{

	SingletonController control = SingletonController.getInstance();
	
	public InvalidBidException() {
		super();
	}

	public InvalidBidException(String message) {
		super(message);
		SingletonController.guardarExcepcion(message, this.toString());

	}

}
