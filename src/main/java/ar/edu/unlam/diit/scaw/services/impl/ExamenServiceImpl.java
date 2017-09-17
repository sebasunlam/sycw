package ar.edu.unlam.diit.scaw.services.impl;

import ar.edu.unlam.diit.scaw.daos.EstadoExamenDao;
import ar.edu.unlam.diit.scaw.daos.MateriasDao;
import ar.edu.unlam.diit.scaw.daos.impl.EstadoExamenDaoImpl;
import ar.edu.unlam.diit.scaw.daos.impl.ExamenDaoImpl;
import ar.edu.unlam.diit.scaw.daos.impl.MateriasDaoImpl;
import ar.edu.unlam.diit.scaw.entities.Examen;
import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.services.ExamenService;

import java.util.List;

public class ExamenServiceImpl implements ExamenService {

    ExamenDaoImpl examenHsql;
    EstadoExamenDao estadoExamenDao;
    MateriasDao materiasDao;

    public ExamenServiceImpl() {

        examenHsql = new ExamenDaoImpl();
        estadoExamenDao = new EstadoExamenDaoImpl();
        materiasDao = new MateriasDaoImpl();

    }


    @Override
    public List<Examen> findAll() {

        List<Examen> examenes = examenHsql.getAll();

        for (Examen examen : examenes) {
            examen.setEstado(estadoExamenDao.get(examen.getIdEstadoExamen()));
            examen.setMateria(materiasDao.get(examen.getIdMateria()));
        }
        return examenes;
    }

    @Override
    public Examen get(int examenId) {
        Examen examen = examenHsql.get(examenId);
        examen.setEstado(estadoExamenDao.get(examen.getIdEstadoExamen()));
        examen.setMateria(materiasDao.get(examen.getIdMateria()));
        return examen;
    }

    @Override
    public void save(Examen examen) {
        examenHsql.save(examen);
    }

    @Override
    public void update(Examen examen) {
        examenHsql.update(examen);
    }

    @Override
    public void delete(int examenId) {
        examenHsql.delete(examenId);
    }

    public ExamenDaoImpl getExamenHsql() {
        return examenHsql;
    }

    public void setExamenHsql(ExamenDaoImpl examenHsql) {
        this.examenHsql = examenHsql;
    }
}
