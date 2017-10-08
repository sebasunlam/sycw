package ar.edu.unlam.diit.scaw.utls;

import com.sun.faces.application.ActionListenerImpl;
import com.sun.faces.application.MethodExpressionMethodBindingAdapter;
import org.apache.commons.lang.time.StopWatch;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.component.ActionSource;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import java.lang.reflect.Method;
import java.util.Set;


public class RoleListener extends ActionListenerImpl implements ActionListener {


    private Set<Class<?>> findAllAuthorizeAnnotations() {
        final StopWatch sw = new StopWatch();
        sw.start();
        final Reflections reflections = new Reflections("org.projectx", new TypeAnnotationsScanner());
        Set<Class<?>> allMessageDrivens = reflections.getTypesAnnotatedWith(Authorize.class); // NOTE HERE
        sw.stop();
        return allMessageDrivens;
    }

    @Override
    public void processAction(ActionEvent event) {
        final FacesContext context = FacesContext.getCurrentInstance();
        final Application application = context.getApplication();
        final ConfigurableNavigationHandler navHandler = (ConfigurableNavigationHandler) application.getNavigationHandler();

//        // Action stuff
        final UIComponent source = event.getComponent();
        final ActionSource actionSource = (ActionSource) source;
        MethodBinding binding = actionSource.getAction();
        final String expr = binding.getExpressionString();


        if (!expr.startsWith("#")) {
            super.processAction(event);
            return;
        }

        final int idx = expr.indexOf('.');
        final String target = expr.substring(0, idx).substring(2);
        final String t = expr.substring(idx + 1);
        final String method = t.substring(0, (t.length() - 1));
        final String methodName = method.substring(0, method.indexOf('('));


        final MethodExpression expression = new MethodExpressionMethodBindingAdapter(binding);
        final ELContext elContext = context.getELContext();
        final ExpressionFactory factory = context.getApplication().getExpressionFactory();

        final ValueExpression ve = factory.createValueExpression(elContext, "#{" + target + '}', Object.class);
        final Object result = ve.getValue(elContext);

        // Check if the target method is a secured method
        // and check security accordingly
        final Method[] methods = result.getClass().getMethods();
        for (final Method meth : methods) {
            if (meth.getName().equals(methodName)) {
                if (meth.isAnnotationPresent(Authorize.class)) {
                    final Authorize securityAnnotation = meth.getAnnotation(Authorize.class);
                    System.out.println("Function to check security on: " + securityAnnotation.roles()); // TODO TO LOG
                    AuthorizeHandler.checkAuth(securityAnnotation.roles());
                } else {
                    super.processAction(event);
                }
                break;
            }
        }
    }
}
