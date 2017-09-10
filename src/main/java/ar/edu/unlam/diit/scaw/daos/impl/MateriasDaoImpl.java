package ar.edu.unlam.diit.scaw.daos.impl;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.MateriasDao;
import ar.edu.unlam.diit.scaw.entities.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MateriasDaoImpl implements MateriasDao{

    HsqlDataSource dataSource = new HsqlDataSource();
    Connection conn;


    @Override
    public void save(Materia materia) {

        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Materias (nombre,idestadomateria,iddocentetitular)VALUES(?, ?,?)");

            stmt.setString(1, materia.getNombre());
            stmt.setInt(2, materia.getEstadoId());
            stmt.setInt(3, materia.getDocenteId());

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

            PreparedStatement stmt = conn.prepareStatement("UPDATE Materias SET nombre=?,password=?,idestadomateria=?,iddocentetitular=? WHERE id=?");

            stmt.setString(1, materia.getNombre());
            stmt.setInt(2, materia.getEstadoId());
            stmt.setInt(3, materia.getDocenteId());
            stmt.setInt(4, materia.getId());

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

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Materias WHERE id=?");
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

            PreparedStatement stmt = conn.prepareStatement("SELECT id,nombre,idestadomateria,iddocentetitular FROM Materias WHERE id=?");
            stmt.setInt(1,materiaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                materia = new Materia();
                materia.setId(rs.getInt("id"));
                materia.setNombre(rs.getString("nombre"));
            }

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
                Materia materia = new Materia();
                materia.setId(rs.getInt("id"));
                materia.setNombre(rs.getString("nombre"));
                materias.add(materia);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return materias;
    }
}
