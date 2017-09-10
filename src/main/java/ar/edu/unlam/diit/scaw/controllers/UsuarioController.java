package ar.edu.unlam.diit.scaw.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.UsuarioService;
import ar.edu.unlam.diit.scaw.services.impl.UsuarioServiceImpl;

@ManagedBean(name = "usuarioController", eager = true)
@RequestScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{usuario}")
	private Usuario usuario = null;
	
	
	UsuarioService service;
	
	public UsuarioController() {
		super();
		service = (UsuarioService) new UsuarioServiceImpl();
	}
	
//	public UsuarioBean(Usuario usuario) {
//		super();
//		this.usuario = usuario;
//	}
	
	public String save() {

		service.save(this.usuario);
		
		return "welcome";
	}
	
	public List<Usuario> getFindAll() {
		List<Usuario> list = service.findAll();
		return list;
	}
	
	public String login(){
		Usuario logueado = service.login(this.usuario);
		if(logueado!=null) 
		{
			return "welcome";			
		}
		else
		{
			return "index";
		}		
	}	

//	private Usuario buildUsuario() {
//		Usuario usuario = new Usuario();
//
//		usuario.setEmail(this.eMail);
//		usuario.setContraseña(contraseña);
//		usuario.setId(id);
//		usuario.setApellido(this.apellido);
//		usuario.setNombre(this.nombre);
//
//		return usuario;
//	}

	public UsuarioService getService() {
		return service;
	}

	public void setService(UsuarioService service) {
		this.service = service;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
