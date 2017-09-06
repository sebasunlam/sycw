package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;
import java.util.List;

public class Materia implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nombre;
    private EstadoMateria estado;
    private List<Usuario> docentes;
    private List<Usuario> alumnos;
    private List<Examen> examenes;

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

    public EstadoMateria getEstado() {
        return estado;
    }

    public void setEstado(EstadoMateria estado) {
        this.estado = estado;
    }

    public List<Usuario> getDocentes() {
        return docentes;
    }

    public void setDocentes(List<Usuario> docentes) {
        this.docentes = docentes;
    }

    public List<Usuario> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Usuario> alumnos) {
        this.alumnos = alumnos;
    }

    public List<Examen> getExamenes() {
        return examenes;
    }

    public void setExamenes(List<Examen> examenes) {
        this.examenes = examenes;
    }
}

