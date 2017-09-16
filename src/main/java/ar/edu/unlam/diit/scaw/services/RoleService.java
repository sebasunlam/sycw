package ar.edu.unlam.diit.scaw.services;

import ar.edu.unlam.diit.scaw.entities.Role;

import java.util.List;

public interface RoleService {
    /**
     * Trae todos los roles
     * @return materias devueltas
     */
    List<Role> getFindAll();

}
