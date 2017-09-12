package ar.edu.unlam.diit.scaw.services;

import ar.edu.unlam.diit.scaw.entities.EstadoUsuario;

import java.util.List;

public interface EstadoUsuarioService {
    EstadoUsuario get(Integer estadoUsuarioId);
    List<EstadoUsuario> getAll();
}
