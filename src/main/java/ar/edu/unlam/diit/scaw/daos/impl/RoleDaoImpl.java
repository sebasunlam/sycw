package ar.edu.unlam.diit.scaw.daos.impl;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.MateriasDao;
import ar.edu.unlam.diit.scaw.daos.RoleDao;
import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.entities.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RoleDaoImpl implements RoleDao{

    HsqlDataSource dataSource = new HsqlDataSource();
    Connection conn;

    @Override
    public List<Role> getFindAll() {
        List<Role> roles = new LinkedList<Role>();

        try {
            conn = (dataSource.dataSource()).getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT ID, DESCRIPCION FROM ROLES");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                roles.add(mapRole(rs));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return roles;
    }

    private Role mapRole(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getInt("id"));
        role.setDescripcion(rs.getString("descripcion"));
        return role;
    }
}
