package co.edu.uniquindio.progiii.subastasquindio.exceptions;

// Excepcion cuando la fecha de la publicacion es invalidad (Fecha de ayer por ejemplo)

import co.edu.uniquindio.progiii.subastasquindio.controllers.SingletonController;

public class InvalidDateException extends Exception{

	public InvalidDateException() {
		super();
	}

	public InvalidDateException(String message) {
		super(message);
		SingletonController.guardarExcepcion(message, this.toString());
	}

}
