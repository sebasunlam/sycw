package ar.edu.unlam.diit.scaw.daos;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface UsuarioDao {

	Usuario get(int usuarioId);
	List<Usuario> findAll();
	Usuario login(Usuario usuario);
	void save(Usuario usuario);
	void update(Usuario usuario);
	void delete(int usuarioId);

}
