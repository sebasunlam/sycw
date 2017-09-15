package ar.edu.unlam.diit.scaw.services;

import ar.edu.unlam.diit.scaw.entities.Materia;

import java.util.List;

public interface MateriaService {
    /**
     * Inserta un nuevo registro en materia
     * @param materia Materia a insertar
     */
    void save(Materia materia);

    /**
     * Actualiza la materia que se pasa por objeto, match del id en la base de datos y le asigna todos los campos
     * por lo tanto es posible cambiar cualquier estado de la misma
     * @param materia materia a actualizar
     */
    void update(Materia materia);

    /**
     * Elimina la materia
     * @param materiaId id de la materia a eliminar
     */
    void delete(Integer materiaId);

    /**
     * Obtiene una materia en base a su id
     * @param materiaId Id de la materia
     * @return Docente correspondiente
     */
    Materia get(Integer materiaId);

    /**
     * Devuelve una lista de materias para un docente en particular
     * @param docenteId el id del docente para devolver las materias
     * @return la lista de materias para el docente especificado
     */
    List<Materia> getMateriasDocente(Integer docenteId);
    /**
     * Asigna las materias a los docentes
     * @param docenteId Id del docente a asignar la materia
     * @param materiaId Id de la materia para asignarle al docente
     */
    void asignarDocenteMateria(Integer docenteId,Integer materiaId);

    /**
     * Trae todas las materias
     * @return materias devueltas
     */
    List<Materia> getAll();

}
