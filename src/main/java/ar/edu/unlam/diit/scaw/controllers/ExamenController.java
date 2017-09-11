package ar.edu.unlam.diit.scaw.controllers;

import ar.edu.unlam.diit.scaw.entities.Examen;
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


}
