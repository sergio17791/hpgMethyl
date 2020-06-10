package es.hpgMethyl.utils;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public final class FacesContextUtils {

	 final public static String SESSION_USER = "sessionUser";
	 
	 private FacesContextUtils() {};
	 
	 public static void setParameterFacesContextSession(String key, Object value) {
		 FacesContext facesContext = FacesContext.getCurrentInstance();
	     ExternalContext externalContext = facesContext.getExternalContext();
	     Map<String, Object> sessionMap = externalContext.getSessionMap();
	     sessionMap.put(key, value);
	 }
	 
	 public static Object getParameterFacesContextSession(String key) {
		 FacesContext facesContext = FacesContext.getCurrentInstance();
	     ExternalContext externalContext = facesContext.getExternalContext();
	     Map<String, Object> sessionMap = externalContext.getSessionMap();
	     return sessionMap.get(key);
	 }
	 
	 public static void setMessageInComponent(UIComponent component, Severity severity, String message, String details) {
		 FacesContext facesContext = FacesContext.getCurrentInstance();
	     FacesMessage facesMessage = new FacesMessage(severity, message, details);
	     facesContext.addMessage(component.getClientId(), facesMessage);
	 }
	 
	 public static void setMessageInComponent(UIComponent component, Severity severity, String message) {
		 FacesContext facesContext = FacesContext.getCurrentInstance();
	     FacesMessage facesMessage = new FacesMessage(message);
	     facesMessage.setSeverity(severity);
	     facesContext.addMessage(component.getClientId(), facesMessage);
	 }
	 
	 public static void setMessageInComponent(UIComponent component, String message) {
		 FacesContext facesContext = FacesContext.getCurrentInstance();
	     FacesMessage facesMessage = new FacesMessage(message);
	     facesContext.addMessage(component.getClientId(), facesMessage);
	 }
}