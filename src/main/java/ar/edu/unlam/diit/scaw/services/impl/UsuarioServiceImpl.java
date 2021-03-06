package ar.edu.unlam.diit.scaw.services.impl;

import ar.edu.unlam.diit.scaw.daos.EstadoUsuarioDao;
import ar.edu.unlam.diit.scaw.daos.impl.EstadoUsuarioDaoImpl;
import ar.edu.unlam.diit.scaw.daos.impl.MateriasDaoImpl;
import ar.edu.unlam.diit.scaw.daos.impl.UsuarioDaoImpl;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.UsuarioService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.ValidationException;

public class UsuarioServiceImpl implements UsuarioService {

    UsuarioDaoImpl usuarioHsql;
    EstadoUsuarioDao estadoUsuarioDao;
    MateriasDaoImpl materiasDao;

    public UsuarioServiceImpl() {
        usuarioHsql = new UsuarioDaoImpl();
        estadoUsuarioDao = new EstadoUsuarioDaoImpl();
        materiasDao = new MateriasDaoImpl();
    }

    @Override
    public Usuario login(Usuario usuario) {
        //por ejemplo modificar algo del usuario, el nombre en mayuscula

        Usuario dbUser = usuarioHsql.login(usuario);
        if (dbUser != null)
            try {
                if (BCrypt.checkpw(usuario.getContraseña(), dbUser.getContraseña())) {
                    return dbUser;
                } else {
                    return null;
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        return null;
    }

    @Override
    public List<Usuario> findAll() {

        List<Usuario> usuarios = usuarioHsql.findAll();
        for (Usuario usuario : usuarios) {
            usuario.setEstado(estadoUsuarioDao.get(usuario.getEstadoId()));
        }

        return usuarios;
    }

    public UsuarioDaoImpl getUsuarioHsql() {
        return usuarioHsql;
    }

    public void setUsuarioHsql(UsuarioDaoImpl personHsql) {
        this.usuarioHsql = personHsql;
    }

    @Override
    public void save(Usuario usuario) {

        try {

            String passwordHashed = BCrypt.hashpw(usuario.getContraseña(), BCrypt.gensalt());

            usuario.setContraseña(passwordHashed);

            String nombre = ESAPI.validator().getValidInput("LoginPage_nombreFild", usuario.getNombre(), "SafeString", 255, true);
            String apellido = ESAPI.validator().getValidInput("LoginPage_apellidoFild", usuario.getApellido(), "SafeString", 255, true);
            String email = ESAPI.validator().getValidInput("LoginPage_emailFild", usuario.getEmail(), "Email", 255, false);


            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(email);


            usuarioHsql.save(usuario);
        } catch (ValidationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Usuario usuario) {
        Usuario old = get(usuario.getId());

        if (usuario.getContraseña().equals("") || usuario.getContraseña() == null) {
            usuario.setContraseña(old.getContraseña());
        } else {
            String passwordHashed = BCrypt.hashpw(usuario.getContraseña(), BCrypt.gensalt());

            usuario.setContraseña(passwordHashed);
        }

        usuarioHsql.update(usuario);
    }

    @Override
    public void delete(Integer usuarioId) {
        usuarioHsql.delete(usuarioId);
    }

    @Override
    public Usuario get(Integer usuarioId) {

        Usuario usuario = usuarioHsql.get(usuarioId);
        usuario.setEstado(estadoUsuarioDao.get(usuario.getEstadoId()));
        return usuario;
    }

    @Override
    public void cambiarPassword(Integer usuarioId, String newPassword) {

        String newPasswordHashed = BCrypt.hashpw(newPassword, BCrypt.gensalt());

        Usuario usuario = usuarioHsql.get(usuarioId);
        usuario.setContraseña(newPasswordHashed);
        usuarioHsql.update(usuario);

    }

    @Override
    public void cambiarEstadoUsuario(Integer usuarioId, Integer estadoUsuarioId) {
        usuarioHsql.cambiarEstado(usuarioId, estadoUsuarioId);
    }

    @Override
    public Boolean userExist(String email) {
        return usuarioHsql.userExist(email);
    }
}
