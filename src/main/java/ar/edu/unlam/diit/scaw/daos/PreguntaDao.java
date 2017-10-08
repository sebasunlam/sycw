package ar.edu.unlam.diit.scaw.daos;

import ar.edu.unlam.diit.scaw.entities.Pregunta;

import java.util.List;

public interface PreguntaDao {
    void save(Pregunta pregunta);

    void update(Pregunta pregunta);

    void delete(int preguntaId);

    Pregunta get(int preguntaId);

    List<Pregunta> getAll(int idExamen);

    void setRespuestasAlumno(Integer alumnoId, String[] respuestasId);

    List<Integer> getRespuestasAlumnos(Integer alumnoId, Integer examenId);
}
