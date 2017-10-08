package ar.edu.unlam.diit.scaw.utls;

import com.sun.faces.application.ActionListenerImpl;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.component.ActionSource;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import java.lang.reflect.Method;


@SuppressWarnings("deprecation")
public class RoleListener extends ActionListenerImpl implements ActionListener {


    @Override
    public void processAction(ActionEvent event) {
        final FacesContext context = FacesContext.getCurrentInstance();


        final UIComponent source = event.getComponent();
        final ActionSource actionSource = (ActionSource) source;

        MethodBinding binding = actionSource.getAction();
        final String expr = binding.getExpressionString();


        if (!expr.startsWith("#")) {
            super.processAction(event);
            return;
        }

        //Se extrae el nombre del metodo
        final int idx = expr.indexOf('.');
        final String target = expr.substring(0, idx).substring(2);
        final String t = expr.substring(idx + 1);
        final String methodName = t.substring(0, t.indexOf('('));



        final ELContext elContext = context.getELContext();
        final ExpressionFactory factory = context.getApplication().getExpressionFactory();

        final ValueExpression ve = factory.createValueExpression(elContext, "#{" + target + '}', Object.class);
        final Object result = ve.getValue(elContext);


        final Method[] methods = result.getClass().getMethods();
        for (final Method meth : methods) {
            if (meth.getName().equals(methodName)) {
                if (meth.isAnnotationPresent(Authorize.class)) {
                    final Authorize securityAnnotation = meth.getAnnotation(Authorize.class);
                    AuthorizeHandler.checkAuth(securityAnnotation.roles());
                } else if(meth.isAnnotationPresent(AllowAnonymous.class)){
                    super.processAction(event);
                }else {
                    super.processAction(event);
                }
                break;
            }
        }
    }
}
