package ar.edu.unlam.diit.scaw.controllers;

import ar.edu.unlam.diit.scaw.entities.Examen;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.ExamenService;
import ar.edu.unlam.diit.scaw.services.impl.ExamenServiceImpl;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;

@ManagedBean(name = "examenController", eager = true)
@RequestScoped
public class ExamenController implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{examen}")
    private Examen examen = null;

    ExamenService service;

    public ExamenController(){
        super();
        service = (ExamenService) new ExamenServiceImpl();
    }

    public String save(){
        service.save(this.examen);
        return "examen/index";
    }

    public String delete(Integer Id) {
        service.delete(Id);
        return "examen/index";
    }

    public String update(String path) {

        service.update(this.examen);

        return path;
    }

    public String get(Integer Id, String path) {
        this.examen = service.get(Id);

        if (examen == null) {
            return "notfound";
        }
        return path;
    }

    public String cargarExamen(Integer idMateria){
        this.examen.setIdMateria(idMateria);
        return "examen/save";
    }

    public String agregarPregunta(Integer idExamen){
        return null;
    }
}
