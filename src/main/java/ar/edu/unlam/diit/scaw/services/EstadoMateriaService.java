package ar.edu.unlam.diit.scaw.services;

import ar.edu.unlam.diit.scaw.entities.EstadoMateria;
import ar.edu.unlam.diit.scaw.entities.EstadoUsuario;

import java.util.List;

public interface EstadoMateriaService {
    EstadoMateria get(Integer estadoMateriaId);
    List<EstadoMateria> getAll();
}
