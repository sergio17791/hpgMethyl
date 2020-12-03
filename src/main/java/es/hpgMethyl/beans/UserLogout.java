package es.hpgMethyl.beans;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import es.hpgMethyl.utils.FacesContextUtils;

public class UserLogout {
	
	public void closeSession() {
		
		FacesContextUtils.invalidateSession();
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect(((HttpServletRequest) externalContext.getRequest()).getRequestURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
