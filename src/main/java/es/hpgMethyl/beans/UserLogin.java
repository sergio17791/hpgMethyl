package es.hpgMethyl.beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;

import es.hpgMethyl.dao.hibernate.UserDAOHibernate;
import es.hpgMethyl.exceptions.InvalidCredentials;
import es.hpgMethyl.usecases.user.LoginUser.LoginUser;
import es.hpgMethyl.usecases.user.LoginUser.LoginUserRequest;
import es.hpgMethyl.usecases.user.LoginUser.LoginUserResponse;
import es.hpgMethyl.utils.FacesContextUtils;

public class UserLogin {
	
	private String email;
    private String password;
    private UIComponent loginComponent;
    
	public UserLogin() {
		this.email = "";
        this.password = "";
        this.loginComponent = null;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the loginComponent
	 */
	public UIComponent getLoginComponent() {
		return loginComponent;
	}

	/**
	 * @param loginComponent the loginComponent to set
	 */
	public void setLoginComponent(UIComponent loginComponent) {
		this.loginComponent = loginComponent;
	}
    
	public String checkUser() {
				
		try {
			LoginUserResponse loginUserResponse = new LoginUser(new UserDAOHibernate()).execute(
				new LoginUserRequest(
					this.getEmail(),
					this.getPassword()
				)
			);
			
			FacesContextUtils.setParameterFacesContextSession(FacesContextUtils.SESSION_USER, loginUserResponse.getUser());
			
			return "validLoginUser";
		} catch (InvalidCredentials e) {
			String invalidCredentialsMessage = FacesContextUtils.geti18nMessage("login.invalidCredentials");
			FacesContextUtils.setMessageInComponent(this.loginComponent, FacesMessage.SEVERITY_ERROR, invalidCredentialsMessage, invalidCredentialsMessage);
			return "invalidLoginUser";
		}			
	}
}