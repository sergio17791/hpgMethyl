package es.hpgMethyl.utils;

import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

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
		 
		 Object parameter = null;
		 
		 FacesContext facesContext = FacesContext.getCurrentInstance();
		 
		 if(facesContext != null) {
			 ExternalContext externalContext = facesContext.getExternalContext();
		     Map<String, Object> sessionMap = externalContext.getSessionMap();
		     return sessionMap.get(key);
		 }
	     
	     return parameter;
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
	 
	 public static String geti18nMessage(String key) {
		 
		 FacesContext facesContext = FacesContext.getCurrentInstance();

	     Locale locale;

	     if (facesContext == null) {
	         locale = Locale.getDefault();
	     } else {
	    	 if (facesContext.getViewRoot() != null) {
	             locale = facesContext.getViewRoot().getLocale();
	         } else {
	             locale = Locale.getDefault();
	         }
	     }

	     String message;

	     try {
	         ResourceBundle bundle = ResourceBundle.getBundle("i18n.messages", locale);
	         message = bundle.getString(key);
	     } catch (MissingResourceException e) {
	         return e.getMessage();
	     }

	     return message;
	 }
	 
	 public static void invalidateSession() {
	     FacesContext facesContext = FacesContext.getCurrentInstance();
	     ExternalContext externalContext = facesContext.getExternalContext();
	     externalContext.invalidateSession();
	 }
}