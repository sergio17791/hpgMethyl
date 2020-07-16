package es.hpgMethyl.beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;

import es.hpgMethyl.dao.hibernate.UserDAOHibernate;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.UserNotFound;
import es.hpgMethyl.usecases.user.GetUserByEmail.GetUserByEmail;
import es.hpgMethyl.usecases.user.GetUserByEmail.GetUserByEmailRequest;
import es.hpgMethyl.usecases.user.GetUserByEmail.GetUserByEmailResponse;
import es.hpgMethyl.utils.FacesContextUtils;

public class PasswordRecovery {

	private User user;
	
	private String email;
	
	private UIComponent passwordRecoveryComponent;

	public PasswordRecovery() {
		this.user = null;
		this.passwordRecoveryComponent = null;
	}
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the passwordRecoveryComponent
	 */
	public UIComponent getPasswordRecoveryComponent() {
		return passwordRecoveryComponent;
	}

	/**
	 * @param passwordRecoveryComponent the passwordRecoveryComponent to set
	 */
	public void setPasswordRecoveryComponent(UIComponent passwordRecoveryComponent) {
		this.passwordRecoveryComponent = passwordRecoveryComponent;
	}
	
	public void checkEmail() {
		
		try {
			GetUserByEmailResponse response = new GetUserByEmail(new UserDAOHibernate()).execute(
					new GetUserByEmailRequest(this.getEmail())
			);
			
			this.user = response.getUser();
			
		} catch(UserNotFound e) {
			String userNotFoundMessage = FacesContextUtils.geti18nMessage("passwordRecovery.userNotFound");
			FacesContextUtils.setMessageInComponent(this.getPasswordRecoveryComponent(), FacesMessage.SEVERITY_ERROR, userNotFoundMessage, userNotFoundMessage);
		}
	}
}
