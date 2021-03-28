package es.hpgMethyl.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import es.hpgMethyl.utils.FacesContextUtils;

@ManagedBean(name="userLogout")
@RequestScoped
public class UserLogout implements Serializable {

	private static final long serialVersionUID = 6431885285766595644L;

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
