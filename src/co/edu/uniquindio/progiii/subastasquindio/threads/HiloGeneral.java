package co.edu.uniquindio.progiii.subastasquindio.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import co.edu.uniquindio.progiii.subastasquindio.model.CasaSubastas;
import co.edu.uniquindio.progiii.subastasquindio.model.Usuario;

// Clase dedicada a ser un hilo multiproposito

public class HiloGeneral extends Thread {

	// Datos del servidor
	int PUERTO = 9999;
	String HOST = "localhost";

	// socket cliente
	Socket socketComunicacion;

	// flujos de entrada y salida
	ObjectOutputStream flujoSalida;
	ObjectInputStream flujoEntrada;
	
	
	// Objetos guias
	String accion;
	Object objeto;
	String respuesta;
	
	// CONSTRUCTORES
	
	public HiloGeneral() {
		super();
	}
	
	
	public HiloGeneral(String accion) {
		super();
		this.accion = accion;
	}

	
	public HiloGeneral(String accion, Object objeto) {
		super();
		this.accion = accion;
		this.objeto = objeto;
	}
	
	
	@Override
	public void run() {
	
			
		
		try {
			crearConexion();
			flujoSalida = new ObjectOutputStream(socketComunicacion.getOutputStream());
			flujoEntrada = new ObjectInputStream(socketComunicacion.getInputStream());
			
			// Que hacer en cada accion.
			switch (accion) {
			case "ValidarUsuario":

				enviarObjeto((Usuario) objeto);
				break;
				
			case "Serializar":
				enviarObjeto((CasaSubastas) objeto);
				break;
			
			case "CargarXML":
				recibirObjeto();
				break;
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + accion);
			}
			
		
		} 
		
		catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			// Cierra las conexiones y muere el hilo
		} finally {
			try {
				flujoEntrada.close();
				socketComunicacion.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	private void enviarObjeto(Object objecto) throws IOException, ClassNotFoundException {
		flujoSalida.writeObject(accion);
		flujoSalida.writeObject(objeto);
		respuesta = (String) flujoEntrada.readObject();
	}
	
	
	private void recibirObjeto() throws IOException, ClassNotFoundException {
		flujoSalida.writeObject(accion);
		objeto = flujoEntrada.readObject();
	}


	private void crearConexion() throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		socketComunicacion = new Socket(HOST,PUERTO);	
	}




	// GETTERS Y SETTERS

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}


	public String getRespuesta() {
		return respuesta;
	}


	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	
	public CasaSubastas getXML() {
		return (CasaSubastas) objeto;
	}
	
}
