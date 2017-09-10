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
	
	UsuarioService usuarioService;

	public UsuarioController() {
		super();
		usuarioService = new UsuarioServiceImpl();
	}
	
	public String save(Usuario usuario) {

		usuario.setEstadoId(1);
		usuarioService.save(usuario);
		
		return "usuario/save";
	}
	
	public List<Usuario> getFindAll() {
		List<Usuario> list = usuarioService.findAll();
		return list;
	}
	
	public String login(){
		Usuario logueado = usuarioService.login(this.usuario);
		if(logueado!=null) 
		{
			return "welcome";			
		}
		else
		{
			return "index";
		}		
	}

	public String update(Usuario usuario){

		usuarioService.update(usuario);

		return "usuario/update";
	}

	public String aprobarUsuario(Integer usuarioId){

		Usuario usuario = usuarioService.get(usuarioId);
		//Si el usuario no existe se devulve un not found para que el sistema no lance una excepcion
		if(usuario != null){
			usuarioService.cambiarEstadoUsuario(usuarioId,2);
			return "usuario/index";
		}
		return "notfound";
	}

	public String rechazarUsuario(Integer usuarioId){

		Usuario usuario = usuarioService.get(usuarioId);
		//Si el usuario no existe se devulve un not found para que el sistema no lance una excepcion
		if(usuario != null){
			usuarioService.cambiarEstadoUsuario(usuarioId,3);
			return "usuario/index";
		}

		return "notfound";
	}

	public String delete(Integer usuarioId){
		usuarioService.delete(usuarioId);
		return "usuario/index";
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
