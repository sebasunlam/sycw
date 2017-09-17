package ar.edu.unlam.diit.scaw.daos;

import ar.edu.unlam.diit.scaw.entities.EstadoExamen;

import java.util.List;

public interface EstadoExamenDao {
    EstadoExamen get(Integer id);
    List<EstadoExamen> getAll();
}
