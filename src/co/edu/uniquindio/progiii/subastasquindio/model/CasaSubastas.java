package co.edu.uniquindio.progiii.subastasquindio.model;

import co.edu.uniquindio.progiii.subastasquindio.controllers.SingletonController;
import co.edu.uniquindio.progiii.subastasquindio.exceptions.UserNotFoundException;
import co.edu.uniquindio.progiii.subastasquindio.exceptions.WrongPasswordException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class CasaSubastas implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//SingletonController control = SingletonController.getInstance();
    ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
    ArrayList<Publicacion> listaPublicaciones = new ArrayList<Publicacion>();
    ArrayList<Transaccion> listaTransacciones = new ArrayList<Transaccion>();
    Usuario usuarioLogeado;

    Publicacion publicacionSeleccionada;


    public CasaSubastas() {

    }


    public void cargarPublicaciones() {

    }


    public void login(String usuario, String contra) throws UserNotFoundException, WrongPasswordException {

        // Variable que contabiliza si existe el usuario
        // y si la contraseña es valida
        // Dependiendo del numero, tira una excepcion u otra.


        int nivel = 0;
        // Buscar en la lista de usuarios la equivalencia
        for (Usuario user : listaUsuarios) {
            if (user.getNombreUsuario().equals(usuario)) {
                nivel++;
                if (user.getContrasena().equals(contra)) {
                    usuarioLogeado = user;
                    nivel++;
                }
            }
        }

        // Lanza las excepciones
        if (nivel == 0) throw new UserNotFoundException("Usuario ingresado no existe en la base de datos");
        else if (nivel == 1) throw new WrongPasswordException("Contraseña incorrecta");
    }

    // Registra a un usuario al la base de datos
    public void registrarUsuario(Usuario user) throws IOException {
        listaUsuarios.add(user);
        // Llama a la funcion que guarda usuarios
        SingletonController.guardarUsuarios();
    }


    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }


    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public ArrayList<Publicacion> getListaPublicaciones() {
        return listaPublicaciones;
    }

    public void setListaPublicaciones(ArrayList<Publicacion> listaPublicaciones) {
        this.listaPublicaciones = listaPublicaciones;
    }

    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }
    
    // Funcion busca a un usuario con el mismo nombre y contraseña y
    // lo setea como logueado.
    public void setUsuarioLogeado(Usuario usuario) {
        for (Usuario user : listaUsuarios) {
            if (user.getNombreUsuario().equals(usuario.getNombreUsuario())) {
                if (user.getContrasena().equals(usuario.getContrasena())) {
                    usuarioLogeado = user;
                }
            }
        }
    }

    public void desloguear() {
    	this.usuarioLogeado = null;
    }
    
    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }


    public void setListaTransacciones(ArrayList<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }

    public Publicacion getPublicacionSeleccionada() {
        return publicacionSeleccionada;
    }

    public void setPublicacionSeleccionada(Publicacion publicacionSeleccionada) {
        this.publicacionSeleccionada = publicacionSeleccionada;
    }
}
