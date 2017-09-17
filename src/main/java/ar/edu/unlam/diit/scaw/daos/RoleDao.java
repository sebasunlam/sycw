package ar.edu.unlam.diit.scaw.daos;

import ar.edu.unlam.diit.scaw.entities.Role;

import java.util.List;

public interface RoleDao {

    /**
     * Devuelve todos los roles
     * @return materias devueltas
     */
    List<Role> getFindAll();
}
