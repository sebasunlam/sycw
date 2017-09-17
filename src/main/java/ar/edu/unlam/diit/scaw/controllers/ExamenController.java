package ar.edu.unlam.diit.scaw.controllers;

import ar.edu.unlam.diit.scaw.entities.Examen;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.ExamenService;
import ar.edu.unlam.diit.scaw.services.impl.ExamenServiceImpl;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "examenController", eager = true)
@RequestScoped
public class ExamenController implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{examen}")
    private Examen examen = null;

    ExamenService examenService;

    public ExamenController(){
        super();
        examenService = new ExamenServiceImpl();
    }

    public String save(){

        examenService.save(this.examen);

        return "/examen/index";
    }

    public String delete(Integer Id) {

        this.examen = examenService.get(Id);

        if (examen == null) {
            return "/notfound";
        }

        examenService.delete(Id);

        return "/examen/index";
    }

    public String update(String path) {

        examenService.update(this.examen);

        return path;
    }

    public String get(Integer Id, String path) {
        this.examen = examenService.get(Id);

        if (examen == null) {
            return "/notfound";
        }
        return path;
    }

    public List<Examen> getAll(){
        return examenService.findAll();
    }

    public String editView(Integer id) {
        examen = examenService.get(id);
        return "/examenes/update";
    }


}
