package ar.edu.unlam.diit.scaw.controllers;


import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.services.MateriaService;
import ar.edu.unlam.diit.scaw.services.impl.MateriaServiceImpl;
import ar.edu.unlam.diit.scaw.utls.Authorize;
import ar.edu.unlam.diit.scaw.utls.SessionUtils;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name = "materiaController", eager = true)
@RequestScoped
public class MateriaController implements Serializable{

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{materia}")
    private Materia materia = null;
    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    MateriaService materiaService;
    private List<String> errors = new LinkedList<>();

    public MateriaController() {
        super();
        materiaService = new MateriaServiceImpl();
    }

    @Authorize(roles = "Docente")
    public String save(){
        materiaService.save(this.materia);
        return "/materia/index";
    }

    @Authorize(roles = "Docente")
    public String update(){
        materiaService.update(this.materia);
        return "/materia/index";
    }

    @Authorize(roles = "Docente")
    public String delete(Integer materiaId){
        materiaService.delete(materiaId);
        return "/materia/index";
    }

    @Authorize(roles = "Docente")
    public List<Materia> getAll(){
        return materiaService.getAll();
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Authorize(roles = "Docente")
    public String editView(Integer id) {
        materia = materiaService.get(id);
        return "/materia/update";
    }

    @Authorize(roles = "Alumno")
    public String inscribirMateria(Integer materiaID) {
        Integer alumnoId = SessionUtils.getUser().getId();
        materiaService.asignarAlumnoMateria(alumnoId, materiaID);
        return "/materia/index";
    }

    @Authorize(roles = "Alumno")
    public Boolean cursaMateria(Integer materiaId) {
        Integer alumnoId = SessionUtils.getUser().getId();
        return materiaService.cursaMateria(alumnoId, materiaId);
    }

    @Authorize(roles = "Alumno")
    public String desinscribirMateria(Integer materiaID) {
        Integer alumnoId = SessionUtils.getUser().getId();
        materiaService.desasignarAlumnoMateria(alumnoId, materiaID);
        return "/materia/index";
    }
}
