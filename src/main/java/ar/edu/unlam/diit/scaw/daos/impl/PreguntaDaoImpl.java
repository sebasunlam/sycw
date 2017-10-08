package ar.edu.unlam.diit.scaw.daos.impl;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.PreguntaDao;
import ar.edu.unlam.diit.scaw.entities.Pregunta;
import ar.edu.unlam.diit.scaw.entities.Respuesta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PreguntaDaoImpl implements PreguntaDao {

    HsqlDataSource dataSource = new HsqlDataSource();
    Connection conn;

    @Override
    public void save(Pregunta pregunta) {
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO PREGUNTAS (IDEXAMEN,PREGUNTA)VALUES(?, ?)");

            stmt.setInt(1, pregunta.getIdExamen());
            stmt.setString(2, pregunta.getPregunta());

            stmt.executeUpdate();
            stmt = conn.prepareStatement("SELECT ID FROM PREGUNTAS WHERE IDEXAMEN=? AND PREGUNTA=?");
            stmt.setInt(1, pregunta.getIdExamen());
            stmt.setString(2, pregunta.getPregunta());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                setRespuestas(pregunta.getRespuestas(), rs.getInt("ID"));
            }

            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Pregunta pregunta) {
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("UPDATE PREGUNTAS SET IDEXAMEN=?,PREGUNTA=? WHERE ID=?");

            stmt.setInt(1, pregunta.getIdExamen());
            stmt.setString(2, pregunta.getPregunta());
            stmt.setInt(3, pregunta.getId());

            setRespuestas(pregunta.getRespuestas(),pregunta.getId());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void setRespuestas(List<Respuesta> respuestas,Integer preguntaId) throws SQLException {

        PreparedStatement stmtDelete = conn.prepareStatement("DELETE FROM RESPUESTAS WHERE IDPREGUNTA=?");
        stmtDelete.setInt(1, preguntaId);
        stmtDelete.executeUpdate();

        for (Respuesta respuesta:respuestas) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO RESPUESTAS (RESPUESTA,IDTIPORESPUESTA,IDPREGUNTA) VALUES (?,?,?)");
            stmt.setString(1, respuesta.getRespuesta());
            stmt.setInt(2, respuesta.getIdTipoRespuesta());
            stmt.setInt(3, preguntaId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int preguntaId) {
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM PREGUNTAS WHERE ID=?");
            stmt.setInt(1, preguntaId);

            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pregunta get(int preguntaId) {
        Pregunta pregunta = null;
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT ID,PREGUNTA,IDEXAMEN FROM PREGUNTAS WHERE ID = ?");
            stmt.setInt(1, preguntaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pregunta = new Pregunta();
                pregunta.setId(rs.getInt("id"));
                pregunta.setPregunta(rs.getString("PREGUNTA"));
                pregunta.setIdExamen(rs.getInt("IDEXAMEN"));

            }

            mapRespuestas(pregunta);

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pregunta;
    }

    @Override
    public List<Pregunta> getAll(int idExamen){
        List<Pregunta> list = new LinkedList<>();
        try {
            conn = (dataSource.dataSource()).getConnection();


            PreparedStatement stmt = conn.prepareStatement("SELECT ID,PREGUNTA,IDEXAMEN FROM PREGUNTAS WHERE IDEXAMEN=?");
            stmt.setInt(1, idExamen);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pregunta pregunta = new Pregunta();
                pregunta.setId(rs.getInt("id"));
                pregunta.setPregunta(rs.getString("PREGUNTA"));
                pregunta.setIdExamen(rs.getInt("IDEXAMEN"));
                list.add(pregunta);

                mapRespuestas(pregunta);
            }



            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void setRespuestasAlumno(Integer alumnoId, String[] respuestasId) {
        try {
            conn = (dataSource.dataSource()).getConnection();
            for (String respuestaid:respuestasId) {
                PreparedStatement stmtDelete = conn.prepareStatement("DELETE FROM RESPUESTAALUMNO WHERE IDALUMNO=? AND IDRESPUESTA =?");
                stmtDelete.setInt(1, alumnoId);
                stmtDelete.setInt(2, Integer.parseInt(respuestaid));
                stmtDelete.executeUpdate();

                PreparedStatement stmt = conn.prepareStatement("INSERT INTO RESPUESTAALUMNO (IDALUMNO,IDRESPUESTA) VALUES (?,?)");
                stmt.setInt(1, alumnoId);
                stmt.setInt(2, Integer.parseInt(respuestaid));
                stmt.executeUpdate();
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Integer> getRespuestasAlumnos(Integer alumnoId, Integer examenId) {
        List<Integer> respuestas = new ArrayList<>();
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT IDRESPUESTA FROM RESPUESTAALUMNO AS ra" +
                    " JOIN RESPUESTAs AS r ON ra.IDRESPUESTA = r.ID" +
                    " JOIN PREGUNTAS AS p ON r.IDPREGUNTA = p.ID" +
                    " WHERE ra.IDALUMNO = ? AND p.IDEXAMEN = ?");
            stmt.setInt(1, alumnoId);
            stmt.setInt(2, examenId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                respuestas.add(rs.getInt("IDRESPUESTA"));
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return respuestas;
    }

    private void mapRespuestas(Pregunta pregunta) throws SQLException {
        conn = (dataSource.dataSource()).getConnection();
        List<Respuesta> respuestas = new LinkedList<>();

        PreparedStatement stmt = conn.prepareStatement("SELECT ID,RESPUESTA,IDTIPORESPUESTA FROM RESPUESTAS WHERE IDPREGUNTA=?");
        stmt.setInt(1, pregunta.getId());

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Respuesta respuesta = new Respuesta();
            respuesta.setId(rs.getInt("id"));
            respuesta.setRespuesta(rs.getString("RESPUESTA"));
            respuesta.setIdTipoRespuesta(rs.getInt("IDTIPORESPUESTA"));

            respuestas.add(respuesta);
        }

        pregunta.setRespuestas(respuestas);
    }
}
