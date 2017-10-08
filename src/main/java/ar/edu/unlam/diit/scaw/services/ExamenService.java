package ar.edu.unlam.diit.scaw.services;

import ar.edu.unlam.diit.scaw.entities.Examen;

import java.util.List;

public interface ExamenService {

    List<Examen> findAll();
    Examen get(int examenId);
    void save(Examen examen);
    void update(Examen examen);
    void delete(int examenId);

    List<Examen> getAptoParaRendir(Integer alumnoId);
}
