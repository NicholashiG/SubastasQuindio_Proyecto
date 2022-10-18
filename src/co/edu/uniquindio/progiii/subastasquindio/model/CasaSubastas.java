package co.edu.uniquindio.progiii.subastasquindio.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CasaSubastas implements Serializable {

	
	
	
	ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	Usuario usuarioLogeado;

	
	public CasaSubastas() {

	}
	
	
	public boolean login (String usuario, String contra) {
		
//		for (Usuario user: listaUsuarios) {
//			
//			if (user.getNombreUsuario() == usuario && user.getContrasena() == contra) {
//				usuarioLogeado = user;
							//- ---- SOLO PARA DEBUGGING DEL LOGIN
						usuarioLogeado = new Usuario(usuario, contra);
				return true;
//			}
//			
//		}
//		return false;
	}


	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}


	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}


	public Usuario getUsuarioLogeado() {
		return usuarioLogeado;
	}


	public void setUsuarioLogeado(Usuario usuarioLogeado) {
		this.usuarioLogeado = usuarioLogeado;
	}
	
	
	
}
