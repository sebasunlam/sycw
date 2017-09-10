package ar.edu.unlam.diit.scaw.services.impl;

import ar.edu.unlam.diit.scaw.daos.EstadoMateriaDao;
import ar.edu.unlam.diit.scaw.daos.EstadoUsuarioDao;
import ar.edu.unlam.diit.scaw.daos.impl.EstadoMateriaDaoImpl;
import ar.edu.unlam.diit.scaw.daos.impl.EstadoUsuarioDaoImpl;
import ar.edu.unlam.diit.scaw.entities.EstadoMateria;
import ar.edu.unlam.diit.scaw.entities.EstadoUsuario;
import ar.edu.unlam.diit.scaw.services.EstadoMateriaService;
import ar.edu.unlam.diit.scaw.services.EstadoUsuarioService;

import java.util.List;

public class EstadoMateriaServiceImpl implements EstadoMateriaService {

    EstadoMateriaDao estadoMateriaDao;

    public EstadoMateriaServiceImpl() {
        estadoMateriaDao = new EstadoMateriaDaoImpl();
    }

    @Override
    public EstadoMateria get(Integer estdoMateriaId) {
        return estadoMateriaDao.get(estdoMateriaId);
    }

    @Override
    public List<EstadoMateria> getAll() {
        return estadoMateriaDao.getAll();
    }
}
