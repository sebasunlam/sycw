package ar.edu.unlam.diit.scaw.entities;

public class AlumnoResultadoExamen {
    private Integer AlumnoId;
    private Integer RespuestasCorrectas;
    private Integer ExamenId;
    private Integer nota;
    private String nombre;

    public Integer getAlumnoId() {
        return AlumnoId;
    }

    public void setAlumnoId(Integer alumnoId) {
        AlumnoId = alumnoId;
    }

    public Integer getRespuestasCorrectas() {
        return RespuestasCorrectas;
    }

    public void setRespuestasCorrectas(Integer respuestasCorrectas) {
        RespuestasCorrectas = respuestasCorrectas;
    }

    public Integer getExamenId() {
        return ExamenId;
    }

    public void setExamenId(Integer examenId) {
        ExamenId = examenId;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
