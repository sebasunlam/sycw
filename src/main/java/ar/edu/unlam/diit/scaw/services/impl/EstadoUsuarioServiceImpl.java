package ar.edu.unlam.diit.scaw.services.impl;

import ar.edu.unlam.diit.scaw.daos.EstadoUsuarioDao;
import ar.edu.unlam.diit.scaw.daos.impl.EstadoUsuarioDaoImpl;
import ar.edu.unlam.diit.scaw.daos.impl.UsuarioDaoImpl;
import ar.edu.unlam.diit.scaw.entities.EstadoUsuario;
import ar.edu.unlam.diit.scaw.services.EstadoUsuarioService;

import java.util.List;

public class EstadoUsuarioServiceImpl implements EstadoUsuarioService {

    EstadoUsuarioDao estadoUsuarioDao;

    public EstadoUsuarioServiceImpl() {
        estadoUsuarioDao = new EstadoUsuarioDaoImpl();
    }

    @Override
    public EstadoUsuario get(Integer estadoUsuarioId) {
        return estadoUsuarioDao.get(estadoUsuarioId);
    }

    @Override
    public List<EstadoUsuario> getAll() {
        return estadoUsuarioDao.getAll();
    }
}
