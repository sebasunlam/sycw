package ar.edu.unlam.diit.scaw.services.impl;

import ar.edu.unlam.diit.scaw.daos.impl.ExamenDaoImpl;
import ar.edu.unlam.diit.scaw.entities.Examen;
import ar.edu.unlam.diit.scaw.services.ExamenService;

import java.util.List;

public class ExamenServiceImpl implements ExamenService {

    ExamenDaoImpl examenHsql;
    //EstadoUsuarioDao estadoUsuarioDao;

    public ExamenServiceImpl() {
        examenHsql = new ExamenDaoImpl();
        //estadoUsuarioDao = new EstadoUsuarioDaoImpl();
    }

    @Override
    public List<Examen> findAll() {
        return null;
    }

    @Override
    public Examen get(int examenId) {
        Examen examen = examenHsql.get(examenId);

        //examen.setIdEstadoExamen(EstadoExamenDato.get(examen.getIdEstadoExamen()));
        //examen.setIdMateria();
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
