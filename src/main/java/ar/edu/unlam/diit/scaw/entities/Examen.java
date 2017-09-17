package ar.edu.unlam.diit.scaw.entities;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "examen",eager = true)
@RequestScoped
public class Examen implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer idMateria;
    private Integer idEstadoExamen;
    private String nombre;
    private EstadoExamen estado;
    private List<Pregunta> preguntas;
    private Materia materia;


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
        return this.idMateria;
    }

    public Integer getIdEstadoExamen() {
        return this.idEstadoExamen;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public void setIdEstadoExamen(Integer idEstadoExamen) {
        this.idEstadoExamen = idEstadoExamen;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
