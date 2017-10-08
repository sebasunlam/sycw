package ar.edu.unlam.diit.scaw.daos.impl;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.ExamenesDao;
import ar.edu.unlam.diit.scaw.entities.EstadoMateria;
import ar.edu.unlam.diit.scaw.entities.Examen;

import java.sql.*;
import java.util.*;

public class ExamenDaoImpl implements ExamenesDao {

    HsqlDataSource dataSource = new HsqlDataSource();
    Connection conn;

    @Override
    public void save(Examen examen) {
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO EXAMENES (NOMBRE,IDMATERIA,IDESTADOEXAMEN)VALUES(?, ?, ?)");

            stmt.setString(1, examen.getNombre());
            stmt.setInt(2, examen.getIdMateria());
            stmt.setInt(3, examen.getIdEstadoExamen());

            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Examen examen) {
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("UPDATE EXAMENES SET NOMBRE=?,IDMATERIA=?,IDESTADOEXAMEN=? WHERE ID=?");

            stmt.setString(1, examen.getNombre());
            stmt.setInt(2, examen.getIdMateria());
            stmt.setInt(3, examen.getIdEstadoExamen());
            stmt.setInt(4, examen.getId());

            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int examenId) {
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM EXAMENES WHERE ID=?");
            stmt.setInt(1, examenId);

            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Examen get(int examenId) {
        Examen examen = null;
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT ID,NOMBRE,IDMATERIA,IDESTADOEXAMEN FROM EXAMENES WHERE ID = ?");
            stmt.setInt(1, examenId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                examen = new Examen();
                examen.setId(rs.getInt("id"));
                examen.setNombre(rs.getString("nombre"));
                examen.setIdMateria(rs.getInt("idmateria"));
                examen.setIdEstadoExamen(rs.getInt("idestadoexamen"));
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return examen;
    }

    @Override
    public List<Examen> getAll(){
        List<Examen> list = new LinkedList<>();
        try {
            conn = (dataSource.dataSource()).getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ID,NOMBRE,IDMATERIA,IDESTADOEXAMEN FROM EXAMENES");

            while (rs.next()) {
                Examen examen = new Examen();
                examen.setId(rs.getInt("id"));
                examen.setNombre(rs.getString("nombre"));
                examen.setIdMateria(rs.getInt("idmateria"));
                examen.setIdEstadoExamen(rs.getInt("idestadoexamen"));
                list.add(examen);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Examen> getAptoParaRendir(Integer alumnoId) {
        List<Examen> examenes = new ArrayList<>();
        try {
            conn = (dataSource.dataSource()).getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EXAMENES AS e " +
                    " JOIN MATERIAS AS m ON E.IDMATERIA = M.ID" +
                    " JOIN MATERIAALUMNO AS ma ON m.ID = ma.IDMATERIA" +
                    " WHERE ma.IDALUMNO = ? AND e.IDESTADOEXAMEN = 2");

            stmt.setInt(1,alumnoId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Examen examen = new Examen();
                examen.setId(rs.getInt("id"));
                examen.setNombre(rs.getString("nombre"));
                examen.setIdMateria(rs.getInt("idmateria"));
                examen.setIdEstadoExamen(rs.getInt("idestadoexamen"));
                examenes.add(examen);
            }

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return examenes;
    }

    @Override
    public List<Map<String, String>> getRespuestaDeAlumnos(Integer examenId) {
        List<Map<String, String>> notas = new ArrayList<>();

        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM RESPUESTAALUMNO AS ra" +
                    " JOIN RESPUESTAs AS r ON ra.IDRESPUESTA = r.ID" +
                    " JOIN PREGUNTAS AS p ON r.IDPREGUNTA = p.ID" +
                    " WHERE p.IDEXAMEN = ?" +
                    " ORDER BY ra.IDALUMNO ASC");

            stmt.setInt(1, examenId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, String> respuesta = new HashMap<String, String>();
                respuesta.put(rs.getString("IDALUMNO"), rs.getString("IDRESPUESTA"));
                notas.add(respuesta);
            }

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return notas;
    }
}
