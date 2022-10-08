package co.edu.uniquindio.progiii.subastasquindio.exceptions;


// Excepcion cuando el anuncio no existe

public class PostNotFoundException extends Exception{

	public PostNotFoundException() {
		super();
	}

	public PostNotFoundException(String message) {
		super(message);
	}

}
