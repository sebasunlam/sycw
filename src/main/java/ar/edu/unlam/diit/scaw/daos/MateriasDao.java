package ar.edu.unlam.diit.scaw.daos;

import ar.edu.unlam.diit.scaw.entities.Examen;
import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.entities.Usuario;

import java.util.List;

public interface MateriasDao {
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
     * Devuelve todas las materias
     * @return materias devueltas
     */
    List<Materia> getAll();

    /**
     * Verifica que si el alumno ya esta inscripto a la materia
     * @param materiaId
     * @param alumnoId
     * @return
     */
    boolean estaInscripto(Integer materiaId, Integer alumnoId);

    /**
     * guarda el id de materia y alumno en la table intermedia
     * @param materiaId
     * @param alumnoId
     */
    void inscribirAlumno(Integer materiaId, Integer alumnoId);

    List<Materia> getMAteriasAlumno(Integer id);

    void desinscribirAlumno(Integer materiaId, Integer alumnoId);
}
