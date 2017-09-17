package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.*;

import java.util.LinkedList;
import java.util.List;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.UsuarioDao;
import ar.edu.unlam.diit.scaw.entities.Role;
import ar.edu.unlam.diit.scaw.entities.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {

    HsqlDataSource dataSource = new HsqlDataSource();
    Connection conn;

    @Override
    public Usuario login(Usuario usuario) {
        Usuario logueado = null;
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Usuarios WHERE eMail = ? AND password = ?");
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getContraseña());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String eMail = rs.getString("eMail");
                String contraseña = rs.getString("password");
                Integer id = rs.getInt("id");
                String apellido = rs.getString("apellido");
                String nombre = rs.getString("nombre");
                Integer estadoId = rs.getInt("idEstadoUsuario");


                logueado = new Usuario();
                logueado.setEmail(eMail);
                logueado.setContraseña(contraseña);
                logueado.setId(id);
                logueado.setApellido(apellido);
                logueado.setNombre(nombre);
                logueado.setEstadoId(estadoId);

                stmt = conn.prepareStatement("SELECT idrol FROM rolesusuarios WHERE idusuario =?");
                stmt.setInt(1, id);
                ResultSet rsRolesUsuario = stmt.executeQuery();

                List<Role> roles = new LinkedList<>();

                while (rsRolesUsuario.next()) {
                    stmt = conn.prepareStatement("SELECT id,descripcion FROM roles WHERE id =?");
                    stmt.setInt(1, rsRolesUsuario.getInt("idrol"));

                    ResultSet rsRole = stmt.executeQuery();
                    while (rsRole.next()) {
                        Role role = new Role();
                        role.setId(rsRole.getInt("id"));
                        role.setDescripcion(rsRole.getString("descripcion"));
                        roles.add(role);
                    }
                }

                logueado.setRole(roles);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return logueado;
    }

    @Override
    public Usuario get(Integer usuarioId) {
        Usuario usuario = null;
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT EMAIL,PASSWORD,ID,APELLIDO,NOMBRE,IDESTADOUSUARIO FROM Usuarios WHERE id = ?");
            stmt.setInt(1, usuarioId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                usuario = new Usuario();

                usuario.setEmail(rs.getString("eMail"));
                usuario.setContraseña(rs.getString("password"));
                usuario.setId(rs.getInt("id"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEstadoId(rs.getInt("idEstadoUsuario"));

            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> ll = new LinkedList<Usuario>();

        try {
            conn = (dataSource.dataSource()).getConnection();

            Statement query;

            query = conn.createStatement();

            ResultSet rs = query.executeQuery("SELECT EMAIL,PASSWORD,id,APELLIDO,NOMBRE,IDESTADOUSUARIO FROM Usuarios");

            while (rs.next()) {

                String eMail = rs.getString("eMail");
                String contraseña = rs.getString("password");
                Integer id = rs.getInt("id");
                String apellido = rs.getString("apellido");
                String nombre = rs.getString("nombre");
                Integer estadoUsuarioId = rs.getInt("idEstadoUsuario");

                Usuario usuario = new Usuario();
                usuario.setEmail(eMail);
                usuario.setContraseña(contraseña);
                usuario.setId(id);
                usuario.setApellido(apellido);
                usuario.setNombre(nombre);
                usuario.setEstadoId(estadoUsuarioId);

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

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Usuarios (email,password,apellido,nombre,idestadousuario)VALUES(?, ?, ?, ?, 1)");
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getContraseña());
            stmt.setString(3, usuario.getApellido());
            stmt.setString(4, usuario.getNombre());

            stmt.executeUpdate();
            stmt = conn.prepareStatement("SELECT ID FROM USUARIOS WHERE EMAIL=?");
            stmt.setString(1, usuario.getEmail());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                addRoles(usuario.getRole(), rs.getInt("ID"));
            }

            conn.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    private void addRoles(List<Role> roles, Integer usuarioId) throws SQLException {

        PreparedStatement stmtDelete = conn.prepareStatement("DELETE FROM ROLESUSUARIOS WHERE IDUSUARIO=?");
        stmtDelete.setInt(1, usuarioId);
        stmtDelete.executeUpdate();

        for (Integer i = 0; i < roles.size(); i++) {

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO ROLESUSUARIOS (IDUSUARIO,IDROL) VALUES (?,?)");
            stmt.setInt(1, usuarioId);
            Role role = roles.get(i);
            Integer roleId = Integer.parseInt(roles.get(i).toString());
            stmt.setInt(2, roleId);
            stmt.executeUpdate();
        }
    }


    @Override
    public void update(Usuario usuario) {

        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("UPDATE Usuarios SET email=?,password=?,apellido=?,nombre=?,idestadousuario=? WHERE id=?");

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getContraseña());
            stmt.setString(3, usuario.getApellido());
            stmt.setString(4, usuario.getNombre());
            stmt.setInt(5, usuario.getEstadoId());
            stmt.setInt(6, usuario.getId());

            stmt.executeUpdate();

            addRoles(usuario.getRole(), usuario.getId());

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer usuarioId) {
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("UPDATE Usuarios SET idestadousuario=4 WHERE id=?");
            stmt.setInt(1, usuarioId);

            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean userExist(String email) {
        Boolean exist = false;
        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Usuarios WHERE EMAIL = ?");
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    exist = true;
                }
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exist;
    }

}
