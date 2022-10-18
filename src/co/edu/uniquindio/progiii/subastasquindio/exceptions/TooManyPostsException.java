package co.edu.uniquindio.progiii.subastasquindio.exceptions;

// Excepcion cuando el anunciante ha excedido el
// maximo de anuncios permitido

import co.edu.uniquindio.progiii.subastasquindio.controllers.SingletonController;

public class TooManyPostsException extends Exception{

	public TooManyPostsException() {
		super();
	}

	public TooManyPostsException(String message) {
		super(message);
		SingletonController.guardarExcepcion(message, this.toString());
	}

}
