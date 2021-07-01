package es.hpgMethyl.beans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;

import es.hpgMethyl.dao.hibernate.UserDAOHibernate;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.ChangePasswordException;
import es.hpgMethyl.exceptions.UserNotFound;
import es.hpgMethyl.usecases.user.ChangePassword.ChangePassword;
import es.hpgMethyl.usecases.user.ChangePassword.ChangePasswordRequest;
import es.hpgMethyl.usecases.user.ChangePassword.ChangePasswordResponse;
import es.hpgMethyl.usecases.user.GetUserByEmail.GetUserByEmail;
import es.hpgMethyl.usecases.user.GetUserByEmail.GetUserByEmailRequest;
import es.hpgMethyl.usecases.user.GetUserByEmail.GetUserByEmailResponse;
import es.hpgMethyl.utils.FacesContextUtils;
import es.hpgMethyl.utils.PasswordUtils;

@ManagedBean(name="passwordRecovery")
@ViewScoped
public class PasswordRecovery implements Serializable {

	private static final long serialVersionUID = 7364661395117841439L;

	private User user;
	private String email;
	private String question;
	private String response;
	private Boolean correctResponse;
	private String newPassword;
	private String newPasswordVerification;
	private UIComponent passwordRecoveryComponent;
	private UIComponent responseQuestionComponent;
	private UIComponent changePasswordComponent;

	public PasswordRecovery() {
		this.user = null;
		this.question = null;
		this.response = null;
		this.correctResponse = false;
		this.newPassword = null;
		this.newPasswordVerification = null;
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
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
	}
	

	/**
	 * @return the correctResponse
	 */
	public Boolean getCorrectResponse() {
		return correctResponse;
	}

	/**
	 * @param correctResponse the correctResponse to set
	 */
	public void setCorrectResponse(Boolean correctResponse) {
		this.correctResponse = correctResponse;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the newPasswordVerification
	 */
	public String getNewPasswordVerification() {
		return newPasswordVerification;
	}

	/**
	 * @param newPasswordVerification the newPasswordVerification to set
	 */
	public void setNewPasswordVerification(String newPasswordVerification) {
		this.newPasswordVerification = newPasswordVerification;
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
	
	public UIComponent getResponseQuestionComponent() {
		return responseQuestionComponent;
	}

	public void setResponseQuestionComponent(UIComponent responseQuestionComponent) {
		this.responseQuestionComponent = responseQuestionComponent;
	}

	/**
	 * @return the changePasswordComponent
	 */
	public UIComponent getChangePasswordComponent() {
		return changePasswordComponent;
	}

	/**
	 * @param changePasswordComponent the changePasswordComponent to set
	 */
	public void setChangePasswordComponent(UIComponent changePasswordComponent) {
		this.changePasswordComponent = changePasswordComponent;
	}

	public void checkEmail() {
		
		try {
			GetUserByEmailResponse response = new GetUserByEmail(new UserDAOHibernate()).execute(
					new GetUserByEmailRequest(this.getEmail())
			);
			
			User user = response.getUser();
			
			if(user.getActive()) {
				this.user = user;
				this.setQuestion(this.user.getPasswordRecoveryQuestion());
			} else {
				String userNotFoundMessage = FacesContextUtils.geti18nMessage("error.disabledUser");
				FacesContextUtils.setMessageInComponent(this.getPasswordRecoveryComponent(), FacesMessage.SEVERITY_ERROR, userNotFoundMessage, userNotFoundMessage);
			}
			
		} catch(UserNotFound e) {
			String userNotFoundMessage = FacesContextUtils.geti18nMessage("passwordRecovery.userNotFound");
			FacesContextUtils.setMessageInComponent(this.getPasswordRecoveryComponent(), FacesMessage.SEVERITY_ERROR, userNotFoundMessage, userNotFoundMessage);
		}
	}
	
	public void sendResponse() {
		
		try {
			String hashedResponse = PasswordUtils.getHashWithSalt(this.getResponse().replaceAll("\\s","").toLowerCase(), this.user.getPasswordRecoveryResponseSalt());
			
			if(hashedResponse.equals(this.user.getPasswordRecoveryResponse())) {
				this.setCorrectResponse(true);
			} else {
				String wrongResponseMessage = FacesContextUtils.geti18nMessage("passwordRecovery.wrongResponse");
				FacesContextUtils.setMessageInComponent(this.getResponseQuestionComponent(), FacesMessage.SEVERITY_ERROR, wrongResponseMessage, wrongResponseMessage);
			}
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			String defaultErrorMessage = FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.getResponseQuestionComponent(), FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
		}
	}
	
	public String changePassword() {
		
		try {
			ChangePasswordResponse response = new ChangePassword(new UserDAOHibernate()).execute(
					new ChangePasswordRequest(this.user.getId(), this.getNewPassword())
			);
			
			FacesContextUtils.setParameterFacesContextSession(FacesContextUtils.SESSION_USER, response.getUser());
			
			return "/index";				
		} catch (ChangePasswordException | UserNotFound e) {
			String defaultErrorMessage = FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.getChangePasswordComponent(), FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
			
			return null;
		}
	}
	
	public void cleanInputComponent(ComponentSystemEvent event) throws AbortProcessingException {
		UIInput input = (UIInput) event.getComponent();
		input.setValue("");
	}
}
