package es.hpgMethyl.beans;

import es.hpgMethyl.utils.FacesContextUtils;

public class UserLogout {
	
	public void closeSession() {
		FacesContextUtils.invalidateSession();
	}
}
