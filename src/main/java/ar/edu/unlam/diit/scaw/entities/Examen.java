package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;
import java.util.List;

public class Examen implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nombre;
    private EstadoExamen estado;
    private List<Pregunta> preguntas;

    private Integer idMateria;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoExamen getEstado() {
        return estado;
    }

    public void setEstado(EstadoExamen estado) {
        this.estado = estado;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public Integer getIdMateria() {
        return idMateria;
    }
}
