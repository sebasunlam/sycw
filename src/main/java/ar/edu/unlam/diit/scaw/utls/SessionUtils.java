package ar.edu.unlam.diit.scaw.utls;

import ar.edu.unlam.diit.scaw.entities.Usuario;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static HttpSession getSession(){
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest(){
        return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static Usuario getUser(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return (Usuario)session.getAttribute("userData");
    }

    public static void setUser(Usuario usuario){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);

        session.setAttribute("userData",usuario);
    }
    public static void destroy(){
        getSession().invalidate();
    }
}
