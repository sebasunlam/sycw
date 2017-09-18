package ar.edu.unlam.diit.scaw.services.impl;

import ar.edu.unlam.diit.scaw.daos.EstadoExamenDao;
import ar.edu.unlam.diit.scaw.daos.MateriasDao;
import ar.edu.unlam.diit.scaw.daos.PreguntaDao;
import ar.edu.unlam.diit.scaw.daos.impl.EstadoExamenDaoImpl;
import ar.edu.unlam.diit.scaw.daos.impl.ExamenDaoImpl;
import ar.edu.unlam.diit.scaw.daos.impl.MateriasDaoImpl;
import ar.edu.unlam.diit.scaw.daos.impl.PreguntaDaoImpl;
import ar.edu.unlam.diit.scaw.entities.Examen;
import ar.edu.unlam.diit.scaw.entities.Pregunta;
import ar.edu.unlam.diit.scaw.services.ExamenService;
import ar.edu.unlam.diit.scaw.services.PreguntaService;

import java.util.List;

public class PreguntaServiceImpl implements PreguntaService {

    private PreguntaDao preguntaDao;
    private ExamenService examenService;

    public PreguntaServiceImpl(){
        preguntaDao = new PreguntaDaoImpl();
        examenService = new ExamenServiceImpl();
    }


    @Override
    public void save(Pregunta pregunta) {
        preguntaDao.save(pregunta);
    }

    @Override
    public void update(Pregunta pregunta) {
preguntaDao.update(pregunta);
    }

    @Override
    public void delete(int preguntaId) {
preguntaDao.delete(preguntaId);
    }

    @Override
    public Pregunta get(int preguntaId) {
        Pregunta pregunta = preguntaDao.get(preguntaId);
        pregunta.setExamen(examenService.get(pregunta.getIdExamen()));
        //todo:set respuestas;

        return pregunta;
    }

    @Override
    public List<Pregunta> getAll(int idExamen) {
        List<Pregunta> preguntas = preguntaDao.getAll(idExamen);
        for (Pregunta pregunta: preguntas) {
            pregunta.setExamen(examenService.get(pregunta.getIdExamen()));
            //todo:set respuestas;
        }

        return preguntas;
    }
}
