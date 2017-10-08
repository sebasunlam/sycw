package ar.edu.unlam.diit.scaw.services.impl;

import ar.edu.unlam.diit.scaw.daos.EstadoExamenDao;
import ar.edu.unlam.diit.scaw.daos.MateriasDao;
import ar.edu.unlam.diit.scaw.daos.impl.EstadoExamenDaoImpl;
import ar.edu.unlam.diit.scaw.daos.impl.ExamenDaoImpl;
import ar.edu.unlam.diit.scaw.daos.impl.MateriasDaoImpl;
import ar.edu.unlam.diit.scaw.entities.Examen;
import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.services.ExamenService;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.ValidationException;

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
        try {
            String nombre = ESAPI.validator().getValidInput("ExamenSavePage_nombreField", examen.getNombre(), "SafeString", 255, false);

            examen.setNombre(nombre);

            examenHsql.save(examen);
        } catch (ValidationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Examen examen) {
        try {
            String nombre = ESAPI.validator().getValidInput("ExamenUpdatePage_nombreField", examen.getNombre(), "safeString", 255, false );
            examen.setNombre(nombre);
            examenHsql.update(examen);
        } catch (ValidationException e) {
            e.printStackTrace();
        }

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
