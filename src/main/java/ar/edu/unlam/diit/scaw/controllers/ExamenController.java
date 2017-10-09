package ar.edu.unlam.diit.scaw.controllers;

import ar.edu.unlam.diit.scaw.entities.AlumnoResultadoExamen;
import ar.edu.unlam.diit.scaw.entities.Examen;
import ar.edu.unlam.diit.scaw.entities.Pregunta;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.ExamenService;
import ar.edu.unlam.diit.scaw.services.impl.ExamenServiceImpl;
import ar.edu.unlam.diit.scaw.utls.Authorize;
import ar.edu.unlam.diit.scaw.utls.SessionUtils;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "examenController", eager = true)
@RequestScoped
public class ExamenController implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{examen}")
    private Examen examen = null;

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    ExamenService examenService;

    public ExamenController() {
        super();
        examenService = new ExamenServiceImpl();
    }

    @Authorize(roles = "Docente")
    public String save() {

        examenService.save(this.examen);

        return "/examen/index";
    }

    @Authorize(roles = "Docente")
    public String delete(Integer Id) {

        this.examen = examenService.get(Id);

        if (examen == null) {
            return "/notfound";
        }

        examenService.delete(Id);

        return "/examen/index";
    }

    @Authorize(roles = "Docente")
    public String update() {

        examenService.update(this.examen);

        return "/examen/index";
    }

    @Authorize(roles = "Docente")
    public String get(Integer Id, String path) {
        this.examen = examenService.get(Id);

        if (this.examen == null) {
            return "/notfound";
        }
        return path;
    }

    @Authorize(roles = "Docente")
    public List<Examen> getAll() {
        return examenService.findAll();
    }

    @Authorize(roles = "Docente")
    public String editView(Integer id) {
        this.examen = examenService.get(id);
        return "/examen/update";
    }

    @Authorize(roles = "Alumno")
    public List<Examen> getAptoParaRendir(){
        Integer alumnoId = SessionUtils.getUser().getId();
        return examenService.getAptoParaRendir(alumnoId);
    }

    public List<AlumnoResultadoExamen>  calcularNotas() {
        List<AlumnoResultadoExamen>  notas =  examenService.calcularNotas(this.examen.getId());
        return notas;
    }
    public String navigateNota(Integer examenId){
        this.examen = examenService.get(examenId);
        return "/examen/notas";
    }
}
