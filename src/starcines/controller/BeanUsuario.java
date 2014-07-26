package starcines.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import starcines.model.entities.Usuario;
import starcines.model.manager.ManagerGestionApp;


@SessionScoped
@ManagedBean
public class BeanUsuario {
	
	private ManagerGestionApp manager;
	private String nick;
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	private String contrasena;
	
	public BeanUsuario(){
		manager=new ManagerGestionApp();
	}
	
	public List<Usuario> getListUsuarios(){
		return manager.findAllUsuarios();
	}
	
	/**
	 * 
	 * @return
	 */
	public String accionCrearUsuario() {
		// Creamos un objeto temporal con los datos del formulario:
		Usuario u = new Usuario();
		u.setUsuNick(nick);
		u.setUsuPass(contrasena);
		try {
			// guardamos en la bdd:
			manager.crearUsuario(u);
			// limpiamos los datos del formulario:
			nick=null;
			contrasena=null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 
	 * @param u
	 * @return
	 */
	public String accionEliminar(Usuario u) {
		try {
			manager.eliminarUsuario(u.getUsuNick());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 
	 * @param u
	 * @return
	 */
	public String accionCargar(Usuario u) {
		nick=u.getUsuNick();
		contrasena=u.getUsuPass();
		return "usuarios_actualizacion";
	}
	
	/**
	 * 
	 * @return
	 */
	public String accionActualizar(){
		Usuario u;
		//creamos un objeto temporal con los datos a actualizar:
		u=new Usuario();
		u.setUsuNick(nick);
		u.setUsuPass(contrasena);
		try {
		//hacemos la actualizacion:
		manager.actualizarUsuario(u);
		//inicializamos las propiedades para limpiar el formulario:
		nick=null;
		contrasena=null;
		} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		e.printStackTrace();
		} //ir ala pagina productos.xhtml:
		return "usuarios";
		}
	
	/**
	 * Cerrar sesion
	 * @return
	 */
	public String CerrarUsuario(){
		nick="";
		contrasena="";
		return "index";
	}
	
}
