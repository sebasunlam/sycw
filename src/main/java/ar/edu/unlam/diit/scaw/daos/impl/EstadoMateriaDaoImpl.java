package ar.edu.unlam.diit.scaw.daos.impl;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.EstadoMateriaDao;
import ar.edu.unlam.diit.scaw.daos.EstadoUsuarioDao;
import ar.edu.unlam.diit.scaw.entities.EstadoMateria;
import ar.edu.unlam.diit.scaw.entities.EstadoUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class EstadoMateriaDaoImpl implements EstadoMateriaDao {
    HsqlDataSource dataSource = new HsqlDataSource();
    Connection conn;

    @Override
    public EstadoMateria get(Integer estadoUsuarioId) {
        EstadoMateria estadoMateria = null;
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT id,descripcion FROM EstadoMateria WHERE id=?");
            stmt.setInt(1, estadoUsuarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                estadoMateria = new EstadoMateria();
                estadoMateria.setId(rs.getInt("id"));
                estadoMateria.setDescripcion(rs.getString("descripcion"));

            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return estadoMateria;
    }

    @Override
    public List<EstadoMateria> getAll() {
        List<EstadoMateria> list = new LinkedList<>();
        try {
            conn = (dataSource.dataSource()).getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id,descripcion FROM EstadoMateria");

            while (rs.next()) {
                EstadoMateria estadoMateria = new EstadoMateria();
                estadoMateria.setId(rs.getInt("id"));
                estadoMateria.setDescripcion(rs.getString("descripcion"));
                list.add(estadoMateria);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
