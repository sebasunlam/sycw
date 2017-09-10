package ar.edu.unlam.diit.scaw.daos;

import ar.edu.unlam.diit.scaw.entities.EstadoMateria;
import ar.edu.unlam.diit.scaw.entities.EstadoUsuario;

import java.util.List;

public interface EstadoMateriaDao {
    /**
     * Obtiene un estado para las materias
     * @param estadoUsuarioId id del estado
     * @return estado obtenido
     */
    EstadoMateria get(int estadoUsuarioId);

    /**
     *  Obtiene una lista de estados para las materias
     * @return listado de estados completo
     */
    List<EstadoMateria> getAll();

}
