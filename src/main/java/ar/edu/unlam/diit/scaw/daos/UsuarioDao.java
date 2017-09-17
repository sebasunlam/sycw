package ar.edu.unlam.diit.scaw.daos;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface UsuarioDao {

	/**
	 * Obtiene un usuario en base al id
	 * @param usuarioId id del usuario a obtener
	 * @return usuario obtenido
	 */
	Usuario get(Integer usuarioId);

	/**
	 * Obtiene un listado de todos los usuarios
	 * @return listado de usuarios
	 */
	List<Usuario> findAll();

	/**
	 * Metodo que obtiene el usuario en base a su inicio de session, el mismo vuelve con sus respectivos roles
	 * @param usuario usuario a buscar
	 * @return Usuario y roles
	 */
	Usuario login(Usuario usuario);

	/**
	 * Inserta un nuevo usuario
	 * @param usuario usuario a insertar
	 */
	void save(Usuario usuario);

	/**
	 * Actualiza los usuarios en base al usuario que se suministra, de la entidad es de la que se obtiene el id a actualizar y cualquier
	 * estado que sea modificado
	 * @param usuario usuario a actualizar
	 */
	void update(Usuario usuario);

	/**
	 * Elimina un usuario
	 * @param usuarioId id del usuario a eliminar
	 */
	void delete(Integer usuarioId);
	/**
	 * Checkea si existe el usuario
	 * @param email email del usuario
	 */
	Boolean userExist(String email);

	void cambiarEstado(Integer usuarioId,Integer estadoId);

}
