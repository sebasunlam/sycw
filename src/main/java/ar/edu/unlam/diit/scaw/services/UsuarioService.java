package ar.edu.unlam.diit.scaw.services;

import java.util.List;
import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface UsuarioService {

	Usuario login(Usuario usuario);
	List<Usuario> findAll();
	Usuario get(Integer usuarioId);
	void save(Usuario usuario);
	void update(Usuario usuario);
	void delete(Integer usuarioId);
	void cambiarPassword(Integer usuarioId,String newPassword);
	void  cambiarEstadoUsuario(Integer usuarioId,Integer estadoUsuarioId);

}
