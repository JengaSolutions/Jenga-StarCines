package starcines.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import starcines.model.entities.Usuario;
import starcines.model.manager.ManagerDAO;


@SessionScoped
@ManagedBean
public class BeanUsuario {
	
	private ManagerDAO manager;
	private List<Usuario> lista;
	
	
	public BeanUsuario() {
		manager = new ManagerDAO();
	}
	
	public void actionLista()
	{
		lista = (List<Usuario>)manager.findAll(Usuario.class, null);	
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}
	
	

}
