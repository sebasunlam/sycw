package ar.edu.unlam.diit.scaw.daos.impl;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.MateriasDao;
import ar.edu.unlam.diit.scaw.entities.Examen;
import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.entities.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MateriasDaoImpl implements MateriasDao{

    HsqlDataSource dataSource = new HsqlDataSource();
    Connection conn;


    @Override
    public void save(Materia materia) {

        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Materias (nombre,idestadomateria,iddocentetitular)VALUES(?, 1,?)");

            stmt.setString(1, materia.getNombre());
            stmt.setInt(2, materia.getDocenteId());

            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Materia materia) {

        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("UPDATE Materias SET NOMBRE=?,IDESTADOMATERIA=?,IDDOCENTETITULAR=? WHERE id=?");

            stmt.setString(1, materia.getNombre());
            stmt.setInt(2, materia.getEstadoId());
            stmt.setInt(3, materia.getDocenteId());
            stmt.setInt(4, materia.getId());

            if (!materia.getAlumnos().isEmpty()) {

            }

            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer mateiriaId) {
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("UPDATE Materias SET idestadomateria=2 WHERE id=?");
            stmt.setInt(1, mateiriaId);

            stmt.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Materia get(Integer materiaId) {
        Materia materia = null;
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT ID,NOMBRE,IDESTADOMATERIA,IDDOCENTETITULAR FROM MATERIAS WHERE id=?");
            stmt.setInt(1,materiaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                materia = new Materia();
                materia.setId(rs.getInt("id"));
                materia.setNombre(rs.getString("nombre"));
                materia.setEstadoId(rs.getInt("IDESTADOMATERIA"));
                materia.setDocenteId(rs.getInt("IDDOCENTETITULAR"));
            }

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return materia;
    }

    @Override
    public List<Materia> getMateriasDocente(Integer docenteId) {
        List<Materia> materias = new LinkedList<>();

        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT id,nombre,idestadomateria,iddocentetitular FROM Materias WHERE iddocentetitular=?");
            stmt.setInt(1,docenteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                materias.add(mapMateria(rs));
            }

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return materias;
    }

    @Override
    public List<Materia> getAll() {
        List<Materia> materias = new LinkedList<Materia>();

        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT ID,NOMBRE,IDDOCENTETITULAR,IDESTADOMATERIA FROM MATERIAS");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                materias.add(mapMateria(rs));
            }

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return materias;
    }

    @Override
    public boolean estaInscripto(Integer materiaId, Integer alumnoId) {
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT IDMATERIA, IDALUMNO FROM MATERIAALUMNO WHERE IDALUMNO = ? AND IDMATERIA = ?");
            stmt.setInt(1,alumnoId);
            stmt.setInt(2,materiaId);

            ResultSet rs = stmt.executeQuery();

            Boolean result = false;
            if (rs.next()) {
               result = true;
            }
            conn.close();
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void inscribirAlumno(Integer materiaId, Integer alumnoId) {
        try {
            conn = (dataSource.dataSource()).getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO MATERIAALUMNO(IDALUMNO, IDMATERIA) VALUES (?,?)");
            stmt.setInt(1,alumnoId);
            stmt.setInt(2,materiaId);

            stmt.executeUpdate();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Materia> getMAteriasAlumno(Integer alumnoId) {
        List<Materia> materias = new LinkedList<>();

        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT id,nombre,idestadomateria,iddocentetitular " +
                    "FROM MATERIAS AS m JOIN MATERIAALUMNO AS ma ON m.ID = ma.IDMATERIA" +
                    " WHERE ma.IDALUMNO=?");
            stmt.setInt(1,alumnoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                materias.add(mapMateria(rs));
            }

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return materias;
    }

    @Override
    public void desinscribirAlumno(Integer materiaId, Integer alumnoId) {
        try {
            conn = (dataSource.dataSource()).getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM MATERIAALUMNO WHERE IDALUMNO = ? AND IDMATERIA = ?");
            stmt.setInt(1,alumnoId);
            stmt.setInt(2,materiaId);

            stmt.executeUpdate();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Materia mapMateria(ResultSet rs) throws SQLException {
        Materia materia = new Materia();
        materia.setId(rs.getInt("id"));
        materia.setNombre(rs.getString("nombre"));
        materia.setDocenteId(rs.getInt("iddocentetitular"));
        materia.setEstadoId(rs.getInt("idestadomateria"));
        return materia;
    }
}
