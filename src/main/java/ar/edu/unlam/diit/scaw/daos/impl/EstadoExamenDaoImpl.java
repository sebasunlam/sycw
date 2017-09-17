package ar.edu.unlam.diit.scaw.daos.impl;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.EstadoExamenDao;
import ar.edu.unlam.diit.scaw.entities.EstadoExamen;
import ar.edu.unlam.diit.scaw.entities.EstadoMateria;
import ar.edu.unlam.diit.scaw.entities.Examen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class EstadoExamenDaoImpl implements EstadoExamenDao {
    HsqlDataSource dataSource = new HsqlDataSource();
    Connection conn;

    @Override
    public EstadoExamen get(Integer id) {
        EstadoExamen estadoExamen = null;
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT id,descripcion FROM ESTADOSEXAMENES WHERE id=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                estadoExamen = new EstadoExamen();
                estadoExamen.setId(rs.getInt("id"));
                estadoExamen.setDescripcion(rs.getString("descripcion"));

            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return estadoExamen;
    }

    @Override
    public List<EstadoExamen> getAll() {
        List<EstadoExamen> list = new LinkedList<>();
        try {
            conn = (dataSource.dataSource()).getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id,descripcion FROM ESTADOSEXAMENES");

            while (rs.next()) {
                EstadoExamen estadoExamen = new EstadoExamen();
                estadoExamen.setId(rs.getInt("id"));
                estadoExamen.setDescripcion(rs.getString("descripcion"));
                list.add(estadoExamen);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
