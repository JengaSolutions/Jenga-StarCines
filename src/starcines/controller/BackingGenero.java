package starcines.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import starcines.model.entities.Genero;
import starcines.model.manager.ManagerGestionApp;

@ManagedBean
@SessionScoped
public class BackingGenero {
	
	private ManagerGestionApp manager;
	
	public BackingGenero() {
		manager = new ManagerGestionApp();
	}
	
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	private String genero;
	
	/**
	 * 
	 * @return
	 */
	public List<Genero> getListaGeneros(){
		return manager.findAllGeneros();
	}
	
	/**
	 * 
	 * @return
	 */
	public String accionInsertar() {
		// Creamos un objeto temporal con los datos del formulario:
		Genero g=new Genero();		
		g.setGenTipo(genero);
		try {
			// guardamos en la bdd:
			manager.insertarGenero(g);
			// limpiamos los datos del formulario:
			genero=null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 * @param g
	 * @return
	 */
	public String accionEliminar(Genero g) {
		try {
			manager.eliminarGenero(g.getGenId());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 
	 * @param g
	 * @return
	 */
	public String accionCargar(Genero g) {
		id=g.getGenId();
		genero=g.getGenTipo();
		return "generos_actualizacion";
	}
	
	/**
	 * 
	 * @return
	 */
	public String accionActualizar(){
		Genero g;
		//creamos un objeto temporal con los datos a actualizar:
		g=new Genero();
		g.setGenTipo(genero);
		try {
		//hacemos la actualizacion:
		manager.actualizarGenero(g);
		//inicializamos las propiedades para limpiar el formulario:
		genero=null;
		} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		e.printStackTrace();
		} //ir ala pagina productos.xhtml:
		return "generos";
		}
}
