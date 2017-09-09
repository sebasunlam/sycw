package ar.edu.unlam.diit.scaw.daos;

import ar.edu.unlam.diit.scaw.entities.EstadoUsuario;

import java.util.List;

public interface EstadoUsuarioDao {
    EstadoUsuario get (int estadoUsuarioId);
    List<EstadoUsuario> getAll();

}
