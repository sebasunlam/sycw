package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;
import java.util.List;

public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String pregunta;
    private List<Respuesta> respuestas;
    private Integer idExamen;


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
}
