package ar.edu.unlam.diit.scaw.services.impl;

import ar.edu.unlam.diit.scaw.daos.EstadoExamenDao;
import ar.edu.unlam.diit.scaw.daos.EstadoMateriaDao;
import ar.edu.unlam.diit.scaw.daos.impl.EstadoExamenDaoImpl;
import ar.edu.unlam.diit.scaw.daos.impl.EstadoMateriaDaoImpl;
import ar.edu.unlam.diit.scaw.entities.EstadoExamen;
import ar.edu.unlam.diit.scaw.entities.EstadoMateria;
import ar.edu.unlam.diit.scaw.services.EstadoExamenService;
import ar.edu.unlam.diit.scaw.services.EstadoMateriaService;

import java.util.List;

public class EstadoExamenServiceImpl implements EstadoExamenService {

    EstadoExamenDao estadoExamenDao;

    public EstadoExamenServiceImpl() {
        estadoExamenDao = new EstadoExamenDaoImpl();
    }

    @Override
    public EstadoExamen get(Integer estdoExamenId) {
        return estadoExamenDao.get(estdoExamenId);
    }

    @Override
    public List<EstadoExamen> getAll() {
        return estadoExamenDao.getAll();
    }
}
