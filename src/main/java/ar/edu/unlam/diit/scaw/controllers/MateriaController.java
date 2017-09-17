package ar.edu.unlam.diit.scaw.controllers;


import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.MateriaService;
import ar.edu.unlam.diit.scaw.services.impl.MateriaServiceImpl;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name = "materiaController", eager = true)
@RequestScoped
public class MateriaController implements Serializable{

    private static final long serialVersionUID = 1L;

    //@ManagedProperty(value = "#{materia}")
    private Materia materia = new Materia();



    MateriaService materiaService;
    private List<String> errors = new LinkedList<>();

    public MateriaController() {
        super();
        materiaService = new MateriaServiceImpl();
    }

    public Materia getMateria() {
        return materia;
    }

    public String save(){
        materiaService.save(this.materia);
        return "/materia/index";
    }

    public String update(){
        materiaService.update(this.materia);
        return "/materia/index";
    }

    public String delete(Integer materiaId){
        materiaService.delete(materiaId);
        return "/materia/index";
    }

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

    public String editView(Integer id) {
        materia = materiaService.get(id);
        return "/materia/update";
    }
}
