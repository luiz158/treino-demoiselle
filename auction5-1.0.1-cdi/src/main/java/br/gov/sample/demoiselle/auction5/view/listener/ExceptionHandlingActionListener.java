package br.gov.sample.demoiselle.auction5.view.listener;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import com.sun.faces.application.ActionListenerImpl;

/**
 * @author CETEC/CTJEE
 * @see ActionListener
 */
public class ExceptionHandlingActionListener extends ActionListenerImpl implements ActionListener {

	public void processAction(ActionEvent event) {
		try {
			super.processAction(event);
		} catch (Exception e) {
			System.err.println("ExceptionHandlingActionListener.processAction()");
			FacesContext facesContext = FacesContext.getCurrentInstance();
			Application application = facesContext.getApplication();
			NavigationHandler navigationHandler = application.getNavigationHandler();
			navigationHandler.handleNavigation(facesContext, null, "exceptionNavigation");
			facesContext.renderResponse();
		}
	}

}
