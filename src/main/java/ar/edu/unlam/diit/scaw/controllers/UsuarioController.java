package ar.edu.unlam.diit.scaw.controllers;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.entities.Role;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.MateriaService;
import ar.edu.unlam.diit.scaw.services.UsuarioService;
import ar.edu.unlam.diit.scaw.services.impl.MateriaServiceImpl;
import ar.edu.unlam.diit.scaw.services.impl.UsuarioServiceImpl;
import ar.edu.unlam.diit.scaw.utls.AllowAnonymous;
import ar.edu.unlam.diit.scaw.utls.Authorize;
import ar.edu.unlam.diit.scaw.utls.SessionUtils;

@ManagedBean(name = "usuarioController", eager = true)
@RequestScoped
public class UsuarioController implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{usuario}")
    private Usuario usuario = null;
    private Role role = null;

    private List<String> errors = new LinkedList<>();
    private List<String> loginErrors = new LinkedList<>();

    UsuarioService usuarioService;


    public UsuarioController() {
        super();
        usuarioService = new UsuarioServiceImpl();
    }

    @Authorize(roles = "Administrador")
    public String save() {

        return saveUser("usuario/index", "usuario/save");
    }

    private String saveUser(String returnPath, String returnError) {
        if (usuarioService.userExist(this.usuario.getEmail())) {
            errors.add("El usuario ya existe");
        }

        if (!this.usuario.getContraseña().equals(this.usuario.getRepetirPassword())) {
            errors.add("Las contraseñas no coinciden");
        }

        if (errors.size() == 0) {
            usuarioService.save(this.usuario);
            return returnPath;
        }

        return returnError;
    }

    public String save(String path) {
        return saveUser(path, "login");
    }

    @Authorize(roles = "Administrador")
    public List<Usuario> getFindAll() {
        List<Usuario> list = usuarioService.findAll();
        return list;
    }

    @AllowAnonymous
    public String login() {
        Usuario logueado = usuarioService.login(this.usuario);
        if (logueado != null) {
            switch (logueado.getEstadoId()) {
                case 1:
                    loginErrors.add("Su usuario se encuentra pendiente de habilitacion");
                    return "login";
                case 2:
                    SessionUtils.setUser(logueado);
                    return "welcome";
                case 3:
                    loginErrors.add("Su usuario fue rechazado");
                    return "login";
                case 4:
                    loginErrors.add("Su usuario fue eliminado");
                    return "login";
                default:
                    loginErrors.add("Ocurrio un error inesperado");
                    return "login";
            }
        } else {
            loginErrors.add("Usuario o contraseña no valido");
            return "login";
        }
    }

    @Authorize(roles = "Administrador")
    public String update() {

        usuarioService.update(this.usuario);

        return "/usuario/index.xhtml";
    }

    public String update(String path) {

        usuarioService.update(this.usuario);

        return path;
    }

    @Authorize(roles = "Administrador")
    public String aprobarUsuario(Integer usuarioId) {

        Usuario usuario = usuarioService.get(usuarioId);
        //Si el usuario no existe se devulve un not found para que el sistema no lance una excepcion
        if (usuario != null) {
            usuarioService.cambiarEstadoUsuario(usuarioId, 2);
            return "/usuario/index";
        }
        return "notfound";
    }

    @Authorize(roles = "Administrador")
    public String rechazarUsuario(Integer usuarioId) {

        Usuario usuario = usuarioService.get(usuarioId);
        //Si el usuario no existe se devulve un not found para que el sistema no lance una excepcion
        if (usuario != null) {
            usuarioService.cambiarEstadoUsuario(usuarioId, 3);
            return "/usuario/index";
        }

        return "notfound";
    }

    @Authorize(roles = "Administrador")
    public String delete(Integer usuarioId) {
        usuarioService.delete(usuarioId);
        return "/usuario/index";
    }

    public String get(Integer usuarioId, String path) {
        this.usuario = usuarioService.get(usuarioId);

        if (usuario == null) {
            return "notfound";
        }
        return path;
    }

    public String logout() {
        SessionUtils.destroy();
        return "/login.xml";
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getLoginErrors() {
        return loginErrors;
    }

    public void setLoginErrors(List<String> loginErrors) {
        this.loginErrors = loginErrors;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String editView(Integer id) {
        usuario = usuarioService.get(id);
        return "/usuario/update";
    }
}
