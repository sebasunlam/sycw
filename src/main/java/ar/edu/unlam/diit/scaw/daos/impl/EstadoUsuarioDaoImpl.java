package ar.edu.unlam.diit.scaw.daos.impl;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.EstadoUsuarioDao;
import ar.edu.unlam.diit.scaw.entities.EstadoUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class EstadoUsuarioDaoImpl implements EstadoUsuarioDao {
    HsqlDataSource dataSource = new HsqlDataSource();
    Connection conn;

    @Override
    public EstadoUsuario get(Integer estadoUsuarioId) {
        EstadoUsuario estadoUsuario = null;
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT id,descripcion FROM EstadosUsuarios WHERE id=?");
            stmt.setInt(1, estadoUsuarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                estadoUsuario = new EstadoUsuario();
                estadoUsuario.setId(rs.getInt("id"));
                estadoUsuario.setDescripcion(rs.getString("descripcion"));

            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return estadoUsuario;
    }

    @Override
    public List<EstadoUsuario> getAll() {
        List<EstadoUsuario> list = new LinkedList<>();
        try {
            conn = (dataSource.dataSource()).getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id,descripcion FROM EstadosUsuarios");

            while (rs.next()) {
                EstadoUsuario estadoUsuario = new EstadoUsuario();
                estadoUsuario.setId(rs.getInt("id"));
                estadoUsuario.setDescripcion(rs.getString("descripcion"));
                list.add(estadoUsuario);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
