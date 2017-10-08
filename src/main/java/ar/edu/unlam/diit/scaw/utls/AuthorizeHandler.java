package ar.edu.unlam.diit.scaw.utls;

import ar.edu.unlam.diit.scaw.entities.Usuario;

import javax.faces.context.FacesContext;

public class AuthorizeHandler {

    public static void checkAuth(final String roles) {
        String[] roleArray = roles.split(",");
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        Usuario user = SessionUtils.getUser();
        try {
            if (user == null)
                facesContext.getExternalContext().redirect("/login.xhtml");

            Boolean authorized = false;

            for (String role : roleArray) {
                if (user.hasRole(role)) {
                    authorized = true;
                }
            }

            if (!authorized){
                facesContext.getExternalContext().redirect("/notAuthorized.xhtml");
            }

        } catch (Exception e) {
            facesContext.getExternalContext().setResponseStatus(403);
            facesContext.responseComplete();
        }

    }
}
