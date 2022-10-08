package co.edu.uniquindio.progiii.subastasquindio.exceptions;

//Excepcion cuando el titulo del anuncio es invalido (null, "", etc)
public class InvalidPostTitleException extends Exception{

	public InvalidPostTitleException() {
		super();
	}

	public InvalidPostTitleException(String message) {
		super(message);
	}

}
