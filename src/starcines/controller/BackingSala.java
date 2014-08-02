package starcines.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import starcines.model.entities.Sala;
import starcines.model.manager.ManagerGestionApp;

@ManagedBean
@SessionScoped
public class BackingSala {

	private ManagerGestionApp manager;
	
	public BackingSala(){
		manager = new ManagerGestionApp();
	}
	
	private Integer id;
	private String nombre;
	private String formato;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Sala> getListaSalas(){
		return manager.findAllSalas();
	}
	
	/**
	 * 
	 * @return
	 */
	public String accionInsertar() {
		// Creamos un objeto temporal con los datos del formulario:
		Sala s=new Sala();	
		s.setSalNombre(nombre);
		s.setSalFormato(formato);
		try {
			// guardamos en la bdd:
			manager.insertarSala(s);
			// limpiamos los datos del formulario:
			nombre=null;
			formato=null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public String accionEliminar(Sala s) {
		try {
			manager.eliminarSala(s.getSalId());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			e.printStackTrace();
		}
		return "";
	}
	
	
	public String accionCargar(Sala s) {
		id=s.getSalId();
		nombre=s.getSalNombre();
		formato=s.getSalFormato();
		return "salas_actualizacion";
	}
	
	/**
	 * 
	 * @return
	 */
	public String accionActualizar(){
		Sala s;
		//creamos un objeto temporal con los datos a actualizar:
		s=new Sala();
		s.setSalNombre(nombre);
		s.setSalFormato(formato);
		try {
		//hacemos la actualizacion:
		manager.actualizarSala(s);
		//inicializamos las propiedades para limpiar el formulario:
		nombre=null;
		formato=null;
		} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		e.printStackTrace();
		} //ir ala pagina productos.xhtml:
		return "salas";
		}
}
