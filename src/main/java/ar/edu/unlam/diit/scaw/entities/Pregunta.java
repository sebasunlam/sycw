package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String pregunta;
    private List<Respuesta> respuestas;
    private Integer idExamen;
    private Examen examen;

    public Pregunta() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public Integer getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Integer idExamen) {
        this.idExamen = idExamen;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }
}
