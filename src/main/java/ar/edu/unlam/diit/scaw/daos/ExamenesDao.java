package ar.edu.unlam.diit.scaw.daos;

import ar.edu.unlam.diit.scaw.entities.Examen;

import java.util.List;
import java.util.Map;

public interface ExamenesDao {
    /**
     * Inserta un nuevo registro en la tabla examen
     * @param examen Examen a insertar
     */
    void save(Examen examen);

    /**
     * Actualiza el examen que se pasa por objeto, match del id en la base de datos y le asigna todos los campos
     * por lo tanto es posible cambiar cualquier estado de la misma
     * @param examen materia a actualizar
     */
    void update(Examen examen);

    /**
     * Elimina el examen
     * @param examenId id del usuario "examen" a eliminar
     */
    void delete(int examenId);

    /**
     * Obtiene un examen en base a su id
     * @param examenId Id de la materia
     * @return Examen correspondiente
     */
    Examen get(int examenId);

    List<Examen> getAll();

    List<Examen> getAptoParaRendir(Integer alumnoId);

}
