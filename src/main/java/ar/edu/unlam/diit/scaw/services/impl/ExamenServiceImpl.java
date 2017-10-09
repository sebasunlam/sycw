package ar.edu.unlam.diit.scaw.services.impl;

import ar.edu.unlam.diit.scaw.daos.*;
import ar.edu.unlam.diit.scaw.daos.impl.*;
import ar.edu.unlam.diit.scaw.entities.AlumnoResultadoExamen;
import ar.edu.unlam.diit.scaw.entities.Examen;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.ExamenService;

import java.util.List;
import java.util.Map;

public class ExamenServiceImpl implements ExamenService {

    ExamenDaoImpl examenHsql;
    EstadoExamenDao estadoExamenDao;
    MateriasDao materiasDao;
    RespuestaDao respuestaDao;
    PreguntaDao preguntaDao;
    UsuarioDao usuarioDao;

    public ExamenServiceImpl() {

        examenHsql = new ExamenDaoImpl();
        estadoExamenDao = new EstadoExamenDaoImpl();
        materiasDao = new MateriasDaoImpl();
        respuestaDao = new RespuestaDaoImpl();
        preguntaDao = new PreguntaDaoImpl();
        usuarioDao = new UsuarioDaoImpl();
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

    @Override
    public List<Examen> getAptoParaRendir(Integer alumnoId) {
        List<Examen> examenes = examenHsql.getAptoParaRendir(alumnoId);

        for (Examen examen : examenes) {
            examen.setEstado(estadoExamenDao.get(examen.getIdEstadoExamen()));
            examen.setMateria(materiasDao.get(examen.getIdMateria()));
        }
        return examenes;
    }

    @Override
    public List<AlumnoResultadoExamen> calcularNotas(Integer examenId) {

        List<AlumnoResultadoExamen> respuestas = respuestaDao.getRespuestasExamen(examenId);

        Examen examen = get(examenId);
        examen.setPreguntas(preguntaDao.getAll(examen.getId()));
        Integer cantidadPreguntas = examen.getPreguntas().size();

        for (AlumnoResultadoExamen instanciaExamen : respuestas) {
            Integer porcentaje = (instanciaExamen.getRespuestasCorrectas() * 100) / cantidadPreguntas;
            Usuario usuario = usuarioDao.get(instanciaExamen.getAlumnoId());
            instanciaExamen.setNombre(usuario.getNombre() + ", " + usuario.getApellido());
            if (porcentaje == 100) {
                instanciaExamen.setNota(10);
            } else if (porcentaje >= 80 && porcentaje < 100) {
                instanciaExamen.setNota(7);
            } else if (porcentaje >= 60 && porcentaje < 80) {
                instanciaExamen.setNota(4);
            } else {
                instanciaExamen.setNota(2);
            }
        }
        return respuestas;
    }

    public ExamenDaoImpl getExamenHsql() {
        return examenHsql;
    }

    public void setExamenHsql(ExamenDaoImpl examenHsql) {
        this.examenHsql = examenHsql;
    }
}
