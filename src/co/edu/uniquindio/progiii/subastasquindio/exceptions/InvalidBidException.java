package co.edu.uniquindio.progiii.subastasquindio.exceptions;


// Exvepcion para cuando se ingresa un valor
// invalido como puja (para comprador y vendedor)

public class InvalidBidException extends Exception{
	
	public InvalidBidException() {
		super();
	}

	public InvalidBidException(String message) {
		super(message);
	}

}
