package ar.edu.unlam.diit.scaw.daos.impl;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.ExamenesDao;
import ar.edu.unlam.diit.scaw.entities.Examen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamenDaoImpl implements ExamenesDao {

    HsqlDataSource dataSource = new HsqlDataSource();
    Connection conn;

    @Override
    public void save(Examen examen) {
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO EXAMENES (ID,NOMBRE,IDMATERIA,IDESTADOEXAMEN)VALUES(?, ?, ?, ?)");
            stmt.setInt(1, examen.getId());
            stmt.setString(2, examen.getNombre());
            stmt.setInt(3, examen.getIdMateria());
            stmt.setInt(4, examen.getIdEstadoExamen());

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

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EXAMENES WHERE ID = ?");
            stmt.setInt(1, examenId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                examen = new Examen();
                examen.setId(rs.getInt("id"));
                examen.setNombre(rs.getString("nombre"));
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return examen;
    }
}
