package ar.edu.unlam.diit.scaw.services.impl;

import ar.edu.unlam.diit.scaw.daos.RoleDao;
import ar.edu.unlam.diit.scaw.daos.impl.RoleDaoImpl;
import ar.edu.unlam.diit.scaw.entities.Role;
import ar.edu.unlam.diit.scaw.services.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    RoleDao roleDao;

    public RoleServiceImpl() {
        roleDao = new RoleDaoImpl();
    }

    @Override
    public List<Role> getFindAll() {
        return roleDao.getFindAll();
    }
}
