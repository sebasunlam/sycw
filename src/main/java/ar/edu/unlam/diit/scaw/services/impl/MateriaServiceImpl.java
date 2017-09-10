package ar.edu.unlam.diit.scaw.services.impl;

import ar.edu.unlam.diit.scaw.daos.MateriasDao;
import ar.edu.unlam.diit.scaw.daos.impl.MateriasDaoImpl;
import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.services.EstadoMateriaService;
import ar.edu.unlam.diit.scaw.services.MateriaService;
import ar.edu.unlam.diit.scaw.services.UsuarioService;

import java.util.List;

public class MateriaServiceImpl implements MateriaService {
    MateriasDao materiasDao;
    UsuarioService usuarioService;
    EstadoMateriaService estadoMateriaService;

    public MateriaServiceImpl() {
        materiasDao = new MateriasDaoImpl();
        usuarioService = new UsuarioServiceImpl();
        estadoMateriaService = new EstadoMateriaServiceImpl();
    }

    @Override
    public void save(Materia materia) {
        materiasDao.save(materia);
    }

    @Override
    public void update(Materia materia) {
        materiasDao.update(materia);
    }

    @Override
    public void delete(int materiaId) {
        materiasDao.delete(materiaId);
    }

    @Override
    public Materia get(int materiaId) {
        Materia materia = materiasDao.get(materiaId);
        materia.setDocenteTitular(usuarioService.get(materia.getDocenteId()));
        materia.setEstado(estadoMateriaService.get(materia.getEstadoId()));
        //todo: deben asignarse el resto de las clases que le corresponden a la materia, examenes alumnos
        return materia;
    }

    @Override
    public List<Materia> getMateriasDocente(int docenteId) {
        return materiasDao.getMateriasDocente(docenteId);
    }

    @Override
    public void asignarDocenteMateria(int docenteId, int materiaId) {
        Materia materia = materiasDao.get(materiaId);
        materia.setDocenteId(docenteId);
        materiasDao.update(materia);
    }

    @Override
    public void cambiarEstadoMateria(int estadoId, int materiaId) {
        Materia materia = materiasDao.get(materiaId);
        materia.setEstadoId(estadoId);
        materiasDao.update(materia);
    }
}