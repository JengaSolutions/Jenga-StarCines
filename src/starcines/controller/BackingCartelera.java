package starcines.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import starcines.model.entities.Cartelera;
import starcines.model.entities.Pelicula;
import starcines.model.entities.Sala;
import starcines.model.manager.ManagerGestionApp;

@ManagedBean
@SessionScoped
public class BackingCartelera {

	private ManagerGestionApp manager;
	
	public BackingCartelera(){
		manager = new ManagerGestionApp();
	}
	
	private Integer id;
	private Integer pel_id;
	private Integer sal_id;
	private Date desde;
	private Date hasta;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPel_id() {
		return pel_id;
	}
	public void setPel_id(Integer pel_id) {
		this.pel_id = pel_id;
	}
	public Integer getSal_id() {
		return sal_id;
	}
	public void setSal_id(Integer sal_id) {
		this.sal_id = sal_id;
	}
	public Date getDesde() {
		return desde;
	}
	public void setDesde(Date desde) {
		this.desde = desde;
	}
	public Date getHasta() {
		return hasta;
	}
	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Cartelera> getListaCarteleras(){
		return manager.findAllCartelera();
	}
	
	/**
	 * 
	 * @return
	 */
	public String accionInsertar() {
		// Creamos un objeto temporal con los datos del formulario:
		Cartelera c=new Cartelera();	
		c.setCarDesde(desde);
		c.setCarHasta(hasta);

		try {
			Pelicula p = manager.findPeliculaById(pel_id);
			c.setPelicula(p);
			Sala s=manager.findSalaById(sal_id);
			c.setSala(s);
			// guardamos en la bdd:
			manager.insertarCartelera(c);
			// limpiamos los datos del formulario:
			desde=null;
			hasta=null;
			pel_id=null;
			sal_id=null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public String accionEliminar(Cartelera c) {
		try {
			manager.eliminarCartelera(c.getCarId());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public String accionCargar(Cartelera c) {
		id=c.getCarId();
		desde=c.getCarDesde();
		hasta=c.getCarHasta();
		pel_id=c.getPelicula().getPelId();
		sal_id=c.getSala().getSalId();
		return "carteleras_actualizacion";
	}
	
	/**
	 * 
	 * @return
	 */
	public String accionActualizar(){
		Cartelera c;
		//creamos un objeto temporal con los datos a actualizar:
		c=new Cartelera();
		c.setCarDesde(desde);
		c.setCarHasta(hasta);
		
		try {
			Pelicula p = manager.findPeliculaById(pel_id);
			c.setPelicula(p);
			Sala s=manager.findSalaById(sal_id);
			c.setSala(s);
		//hacemos la actualizacion:
		manager.actualizarCartelera(c);
		//inicializamos las propiedades para limpiar el formulario:
		desde=null;
		hasta=null;
		pel_id=null;
		sal_id=null;
		} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		e.printStackTrace();
		} //ir ala pagina productos.xhtml:
		return "carteleras";
		}
	
	public List<SelectItem> getListaPeliculas(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<Pelicula> listadoPeliculas=manager.findAllPelicula();
		
		for(Pelicula p:listadoPeliculas){
			SelectItem item=new SelectItem(p.getPelId(), p.getPelNombre());
			listadoSI.add(item);
		}
		return listadoSI;
	}
	
	public List<SelectItem> getListaSalas(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<Sala> listadoSalas=manager.findAllSalas();
		
		for(Sala s:listadoSalas){
			SelectItem item=new SelectItem(s.getSalId(), s.getSalNombre());
			listadoSI.add(item);
		}
		return listadoSI;
	}
}
