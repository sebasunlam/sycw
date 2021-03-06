package ar.edu.unlam.diit.scaw.services.impl;

import ar.edu.unlam.diit.scaw.daos.MateriasDao;
import ar.edu.unlam.diit.scaw.daos.UsuarioDao;
import ar.edu.unlam.diit.scaw.daos.impl.MateriasDaoImpl;
import ar.edu.unlam.diit.scaw.daos.impl.UsuarioDaoImpl;
import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.EstadoMateriaService;
import ar.edu.unlam.diit.scaw.services.MateriaService;
import ar.edu.unlam.diit.scaw.services.UsuarioService;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.ValidationException;

import java.util.List;

public class MateriaServiceImpl implements MateriaService {
    MateriasDao materiasDao;
    UsuarioDao usuarioDao;
    UsuarioService usuarioService;
    EstadoMateriaService estadoMateriaService;

    public MateriaServiceImpl() {
        materiasDao = new MateriasDaoImpl();
        usuarioDao = new UsuarioDaoImpl();
        usuarioService = new UsuarioServiceImpl();
        estadoMateriaService = new EstadoMateriaServiceImpl();
    }

    @Override
    public void save(Materia materia) {
        try {
            String nombre = ESAPI.validator().getValidInput("NuevaMateriaPage_nombreField", materia.getNombre(), "SafeString", 255, false);

            materia.setNombre(nombre);

            materiasDao.save(materia);
        } catch (ValidationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Materia materia) {
        materiasDao.update(materia);
    }

    @Override
    public void delete(Integer materiaId) {
        materiasDao.delete(materiaId);
    }

    @Override
    public Materia get(Integer materiaId) {
        Materia materia = materiasDao.get(materiaId);
        if (materia.getDocenteId() != null){
            materia.setDocenteTitular(usuarioService.get(materia.getDocenteId()));
        }
        if (materia.getEstadoId() != null){
            materia.setEstado(estadoMateriaService.get(materia.getEstadoId()));
        }
        //todo: deben asignarse el resto de las clases que le corresponden a la materia, examenes alumnos
        return materia;
    }

    @Override
    public List<Materia> getMateriasDocente(Integer docenteId) {
        return materiasDao.getMateriasDocente(docenteId);
    }

    @Override
    public void asignarDocenteMateria(Integer docenteId, Integer materiaId) {
        Materia materia = materiasDao.get(materiaId);
        materia.setDocenteId(docenteId);
        materiasDao.update(materia);
    }

    @Override
    public void asignarAlumnoMateria( Integer alumnoId, Integer materiaId) {
        if (!materiasDao.estaInscripto(materiaId, alumnoId))
            materiasDao.inscribirAlumno(materiaId, alumnoId);
    }

    @Override
    public List<Materia> getAll() {
        List<Materia> materias = materiasDao.getAll();
        for (Materia materia: materias) {
            materia.setEstado(estadoMateriaService.get(materia.getEstadoId()));
            materia.setDocenteTitular(usuarioService.get(materia.getDocenteId()));
        }
        return materias;
    }

    @Override
    public void desasignarAlumnoMateria(Integer alumnoId, Integer materiaId) {
        if (materiasDao.estaInscripto(materiaId, alumnoId))
            materiasDao.desinscribirAlumno(materiaId, alumnoId);
    }

    @Override
    public Boolean cursaMateria(Integer alumnoId, Integer materiaId) {
        return materiasDao.estaInscripto(materiaId, alumnoId);
    }
}
