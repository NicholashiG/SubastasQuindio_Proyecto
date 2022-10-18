package co.edu.uniquindio.progiii.subastasquindio.exceptions;


// Excepcion cuando el anuncio no existe

import co.edu.uniquindio.progiii.subastasquindio.controllers.SingletonController;

public class PostNotFoundException extends Exception{

	public PostNotFoundException() {
		super();
	}

	public PostNotFoundException(String message) {
		super(message);
		SingletonController.guardarExcepcion(message, this.toString());	}

}
