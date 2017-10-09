package ar.edu.unlam.diit.scaw.daos.impl;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.RespuestaDao;
import ar.edu.unlam.diit.scaw.entities.AlumnoResultadoExamen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class RespuestaDaoImpl implements RespuestaDao {

    HsqlDataSource dataSource = new HsqlDataSource();
    Connection conn;

    @Override
    public List<AlumnoResultadoExamen> getRespuestasExamen(Integer examenId) {
        List<AlumnoResultadoExamen> respuestas = new LinkedList<>();

        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT IDALUMNO,COUNT(*) AS Correcta FROM RESPUESTAALUMNO AS ra" +
                    " JOIN RESPUESTAS AS r ON ra.IDRESPUESTA = r.ID" +
                    " JOIN PREGUNTAS AS p ON r.IDPREGUNTA= p.ID" +
                    " WHERE p.IDEXAMEN = ? AND r.IDTIPORESPUESTA = 1" +
                    " GROUP BY ra.IDALUMNO");

            stmt.setInt(1, examenId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AlumnoResultadoExamen respuesta = new AlumnoResultadoExamen();

                respuesta.setAlumnoId(rs.getInt("IDALUMNO"));
                respuesta.setRespuestasCorrectas(rs.getInt("Correcta"));
                respuesta.setExamenId(examenId);


                respuestas.add(respuesta);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return respuestas;
    }
}
