package co.edu.uniquindio.progiii.subastasquindio.exceptions;


//Excepcion cuando un comprador ha superado
//el maximo de pujas permitidas por cuenta

public class TooManyBidsException extends Exception{

	public TooManyBidsException() {
		super();
	}

	public TooManyBidsException(String message) {
		super(message);
	}

}
