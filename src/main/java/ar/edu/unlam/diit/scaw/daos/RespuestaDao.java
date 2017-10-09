package ar.edu.unlam.diit.scaw.daos;

import ar.edu.unlam.diit.scaw.entities.AlumnoResultadoExamen;

import java.util.List;

public interface RespuestaDao {

    List<AlumnoResultadoExamen> getRespuestasExamen(Integer examenId);
}
