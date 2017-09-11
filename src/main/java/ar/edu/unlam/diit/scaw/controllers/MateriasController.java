package ar.edu.unlam.diit.scaw.controllers;


import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.services.MateriaService;
import ar.edu.unlam.diit.scaw.services.impl.MateriaServiceImpl;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "materiasController", eager = true)
@RequestScoped
public class MateriasController implements Serializable{

    private static final long serialVersionUID = 1L;
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @ManagedProperty(value = "#{materia}")
    private Materia materia = null;

    MateriaService materiaService;

    public MateriasController() {
        super();
        materiaService = new MateriaServiceImpl();
    }

    public String save(Materia materia){
        materiaService.save(materia);
        return "materia/index";
    }

    public String update(Materia materia){
        materiaService.update(materia);
        return "materia/index";
    }

    public String delete(Integer materiaId){
        materiaService.delete(materiaId);
        return "materia/index";
    }

    public List<Materia> getAll(){
        return materiaService.getAll();
    }

}
