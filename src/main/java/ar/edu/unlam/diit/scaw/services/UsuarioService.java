package ar.edu.unlam.diit.scaw.services;

import java.util.List;
import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface UsuarioService {

	Usuario login(Usuario usuario);
	List<Usuario> findAll();
	Usuario get(int usuarioId);
	void save(Usuario usuario);
	void update(Usuario usuario);
	void delete(int usuarioId);
	void cambiarPassword(int usuarioId,String newPassword);
	void  cambiarEstadoUsuario(int usuarioId,int estadoUsuarioId);

}
