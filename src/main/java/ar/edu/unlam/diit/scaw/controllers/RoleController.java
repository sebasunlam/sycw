package ar.edu.unlam.diit.scaw.controllers;

import ar.edu.unlam.diit.scaw.entities.Role;
import ar.edu.unlam.diit.scaw.services.RoleService;
import ar.edu.unlam.diit.scaw.services.impl.RoleServiceImpl;
import ar.edu.unlam.diit.scaw.utls.Authorize;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "roleController", eager = true)
@RequestScoped
public class RoleController implements Serializable {

    private static final long serialVersionUID = 1L;

    //@ManagedProperty(value = "#{role}")

    RoleService roleService;

    public RoleController() {
        super();
        roleService = new RoleServiceImpl();
    }

    @Authorize(roles = "Administrador,Docente,Alumno")
    public List<Role> getFindAll() {
        List<Role> list = roleService.getFindAll();
        return list;
    }
}
