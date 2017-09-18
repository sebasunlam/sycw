package ar.edu.unlam.diit.scaw.controllers;

import ar.edu.unlam.diit.scaw.entities.EstadoExamen;
import ar.edu.unlam.diit.scaw.entities.EstadoMateria;
import ar.edu.unlam.diit.scaw.services.EstadoExamenService;
import ar.edu.unlam.diit.scaw.services.EstadoMateriaService;
import ar.edu.unlam.diit.scaw.services.impl.EstadoExamenServiceImpl;
import ar.edu.unlam.diit.scaw.services.impl.EstadoMateriaServiceImpl;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "estadoExamenController", eager = true)
@RequestScoped
public class EstadoExamenController {

    private static final long serialVersionUID = 1L;

    //@ManagedProperty(value = "#{role}")

    EstadoExamenService estadoExamenService;

    public EstadoExamenController() {
        super();
        estadoExamenService = new EstadoExamenServiceImpl();
    }

    public List<EstadoExamen> getAll() {
        List<EstadoExamen> list = estadoExamenService.getAll();
        return list;
    }
}
