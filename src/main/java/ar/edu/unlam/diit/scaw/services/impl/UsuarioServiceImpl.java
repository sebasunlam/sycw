package ar.edu.unlam.diit.scaw.services.impl;

import java.util.List;

import ar.edu.unlam.diit.scaw.daos.impl.UsuarioDaoImpl;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {

    UsuarioDaoImpl usuarioHsql;

    public UsuarioServiceImpl() {
        usuarioHsql = new UsuarioDaoImpl();
    }

    @Override
    public Usuario login(Usuario usuario) {
        return usuarioHsql.login(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioHsql.findAll();
    }

    public UsuarioDaoImpl getUsuarioHsql() {
        return usuarioHsql;
    }

    public void setUsuarioHsql(UsuarioDaoImpl personHsql) {
        this.usuarioHsql = personHsql;
    }

    @Override
    public void save(Usuario usuario) {
        usuarioHsql.save(usuario);
    }

    @Override
    public void update(Usuario usuario) {
        usuarioHsql.update(usuario);
    }

    @Override
    public void delete(int usuarioId) {
        usuarioHsql.delete(usuarioId);
    }

    @Override
    public Usuario get(int usuarioId) {
       return usuarioHsql.get(usuarioId);
    }

    @Override
    public void cambiarPassword(int usuarioId, String newPassword) {

        Usuario usuario = usuarioHsql.get(usuarioId);
        usuario.setContraseña(newPassword);
        usuarioHsql.update(usuario);

    }

    @Override
    public void cambiarEstadoUsuario(int usuarioId, int estadoUsuarioId) {

        Usuario usuario = usuarioHsql.get(usuarioId);
        usuario.setEstadoId(estadoUsuarioId);
        usuarioHsql.update(usuario);

    }
}
