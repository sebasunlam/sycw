package ar.edu.unlam.diit.scaw.controllers;

import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.entities.Pregunta;
import ar.edu.unlam.diit.scaw.entities.Respuesta;
import ar.edu.unlam.diit.scaw.entities.TipoRespuesta;
import ar.edu.unlam.diit.scaw.services.PreguntaService;
import ar.edu.unlam.diit.scaw.services.impl.PreguntaServiceImpl;
import ar.edu.unlam.diit.scaw.utls.SessionUtils;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ManagedBean(name = "preguntaController", eager = true)
@SessionScoped
public class PreguntaController {

    private Pregunta pregunta = new Pregunta();
    private Integer examenId = null;
    private PreguntaService preguntaService;
    private List<Respuesta> respuestas = null;
    private Respuesta currentRespuesta = new Respuesta();
    private List<Pregunta> preguntas = null;
    private String[] respuestasSeleccionadas;

    public String[] getRespuestasSeleccionadas() {
        return respuestasSeleccionadas;
    }

    public void setRespuestasSeleccionadas(String[] respuestasSeleccionadas) {
        this.respuestasSeleccionadas = respuestasSeleccionadas;
    }

    public PreguntaController() {
        preguntaService = new PreguntaServiceImpl();
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public String getAll(int materiaId) {
        preguntas = preguntaService.getAll(materiaId);
        return "/pregunta/index";
    }

    public String rendirExamen(int examenId) {
        Integer alumnoId = SessionUtils.getUser().getId();

        preguntas = preguntaService.getAll(examenId);

        List<Integer> respuestas = preguntaService.getRespuestasAlumnos(alumnoId, examenId);
        Integer i = 0;
        this.respuestasSeleccionadas = new String[respuestas.size()];
        for (Integer idRespuesta: respuestas) {
            this.respuestasSeleccionadas[i] = String.valueOf(idRespuesta);
            i++;
        }
        return "/pregunta/rendirexamen";
    }

    public String addRespuesta(String path) {

        if (respuestas == null) {
            respuestas = new LinkedList<>();
        }

        if (this.currentRespuesta.getCorrecta()) {
            currentRespuesta.setIdTipoRespuesta(1);
        } else {
            currentRespuesta.setIdTipoRespuesta(2);
        }

        respuestas.add(this.currentRespuesta);

        this.currentRespuesta = new Respuesta();

        return path;
    }

    public String save() {

        this.pregunta.setRespuestas(respuestas);
        preguntaService.save(this.pregunta);

        return "/pregunta/index";
    }

    public String update() {

        this.pregunta.setRespuestas(respuestas);
        preguntaService.update(this.pregunta);

        return "/pregunta/index";
    }

    public String navigateSave() {
        this.pregunta = new Pregunta();
        this.respuestas = new LinkedList<>();
        return "/pregunta/save";
    }

    public String navigateEdita(int preguntaId) {
        this.pregunta = preguntaService.get(preguntaId);
        this.respuestas = this.pregunta.getRespuestas();

        for (Respuesta respuesta : this.respuestas) {
            if (respuesta.getIdTipoRespuesta().equals(1)) {
                respuesta.setCorrecta(true);
            } else {
                respuesta.setCorrecta(false);
            }
        }
        return "/pregunta/update";
    }

    public Integer getExamenId() {
        return examenId;
    }

    public void setExamenId(Integer examenId) {
        this.examenId = examenId;
    }

    public Respuesta getCurrentRespuesta() {
        return currentRespuesta;
    }

    public void setCurrentRespuesta(Respuesta currentRespuesta) {
        this.currentRespuesta = currentRespuesta;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public String saveRespuestasAlumno() {
        Integer alumnoId = SessionUtils.getUser().getId();

        Map<String,String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String examenId = requestParams.get("examenId");

        preguntaService.setRespuestasAlumno(alumnoId, this.respuestasSeleccionadas);

        List<Integer> respuestas = preguntaService.getRespuestasAlumnos(alumnoId, Integer.parseInt(examenId));
        Integer i = 0;
        this.respuestasSeleccionadas = new String[respuestas.size()];
        for (Integer idRespuesta: respuestas) {
            this.respuestasSeleccionadas[i] = String.valueOf(idRespuesta);
            i++;
        }
        return "/pregunta/rendirexamen";
    }
}
