package ar.edu.unlam.diit.scaw.daos;

import ar.edu.unlam.diit.scaw.entities.EstadoUsuario;

import java.util.List;

public interface EstadoUsuarioDao {
    /**
     * Obtiene un estado para los usuarios
     * @param estadoUsuarioId id del estado
     * @return estado obtenido
     */
    EstadoUsuario get (Integer estadoUsuarioId);

    /**
     *  Obtiene una lista de estados para los usuarios
     * @return listado de estados completo
     */
    List<EstadoUsuario> getAll();

}
