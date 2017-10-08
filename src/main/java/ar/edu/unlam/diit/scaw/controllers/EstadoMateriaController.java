package ar.edu.unlam.diit.scaw.controllers;

import ar.edu.unlam.diit.scaw.entities.EstadoMateria;
import ar.edu.unlam.diit.scaw.entities.Role;
import ar.edu.unlam.diit.scaw.services.EstadoMateriaService;
import ar.edu.unlam.diit.scaw.services.RoleService;
import ar.edu.unlam.diit.scaw.services.impl.EstadoMateriaServiceImpl;
import ar.edu.unlam.diit.scaw.services.impl.RoleServiceImpl;
import ar.edu.unlam.diit.scaw.utls.Authorize;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "estadoMateriaController", eager = true)
@RequestScoped
public class EstadoMateriaController {

    private static final long serialVersionUID = 1L;

    //@ManagedProperty(value = "#{role}")

    EstadoMateriaService estadoMateriaService;

    public EstadoMateriaController() {
        super();
        estadoMateriaService = new EstadoMateriaServiceImpl();
    }

    @Authorize(roles = "Docente,Alumno")
    public List<EstadoMateria> getAll() {
        List<EstadoMateria> list = estadoMateriaService.getAll();
        return list;
    }
}
