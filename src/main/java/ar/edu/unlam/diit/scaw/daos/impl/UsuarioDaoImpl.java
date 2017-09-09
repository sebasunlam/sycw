package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.*;

import java.util.LinkedList;
import java.util.List;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.UsuarioDao;
import ar.edu.unlam.diit.scaw.entities.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {

    HsqlDataSource dataSource = new HsqlDataSource();
    Connection conn;

    @Override
    public Usuario login(Usuario usuario) {
        Usuario logueado = null;
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Usuarios WHERE eMail = ? AND password = ? AND idEstadoUsuario = 2");
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getContraseña());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String eMail = rs.getString("eMail");
                String contraseña = rs.getString("password");
                Integer id = rs.getInt("id");
                String apellido = rs.getString("apellido");
                String nombre = rs.getString("nombre");

                logueado = new Usuario();
                logueado.setEmail(eMail);
                logueado.setContraseña(contraseña);
                logueado.setId(id);
                logueado.setApellido(apellido);
                logueado.setNombre(nombre);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return logueado;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> ll = new LinkedList<Usuario>();

        try {
            conn = (dataSource.dataSource()).getConnection();

            Statement query;

            query = conn.createStatement();

            ResultSet rs = query.executeQuery("SELECT * FROM Usuarios WHERE idEstadoUsuario = 2");

            while (rs.next()) {

                String eMail = rs.getString("eMail");
                String contraseña = rs.getString("password");
                Integer id = rs.getInt("id");
                String apellido = rs.getString("apellido");
                String nombre = rs.getString("nombre");

                Usuario usuario = new Usuario();
                usuario.setEmail(eMail);
                usuario.setContraseña(contraseña);
                usuario.setId(id);
                usuario.setApellido(apellido);
                usuario.setNombre(nombre);

                ll.add(usuario);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ll;
    }

    @Override
    public void save(Usuario usuario) {

        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Usuarios (id,email,password,apellido,nombre,idestadousuario)VALUES(?, ?, ?, ?, ?, 1)");
            stmt.setInt(1, usuario.getId());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getContraseña());
            stmt.setString(4, usuario.getApellido());
            stmt.setString(5, usuario.getNombre());

            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
