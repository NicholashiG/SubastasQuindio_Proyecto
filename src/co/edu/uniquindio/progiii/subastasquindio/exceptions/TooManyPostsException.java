package co.edu.uniquindio.progiii.subastasquindio.exceptions;

// Excepcion cuando el anunciante ha excedido el
// maximo de anuncios permitido

public class TooManyPostsException extends Exception{

	public TooManyPostsException() {
		super();
	}

	public TooManyPostsException(String message) {
		super(message);
	}

}
