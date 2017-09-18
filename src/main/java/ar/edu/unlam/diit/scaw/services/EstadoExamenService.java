package ar.edu.unlam.diit.scaw.services;

import ar.edu.unlam.diit.scaw.entities.EstadoExamen;
import ar.edu.unlam.diit.scaw.entities.EstadoMateria;

import java.util.List;

public interface EstadoExamenService {
    EstadoExamen get(Integer estdoExamenId);
    List<EstadoExamen> getAll();
}
