package es.hpgMethyl.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import es.hpgMethyl.dao.hibernate.UserDAOHibernate;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.UpdateUserException;
import es.hpgMethyl.exceptions.UserNotFound;
import es.hpgMethyl.usecases.user.UpdateUserPersonalInformation.UpdateUserPersonalInformation;
import es.hpgMethyl.usecases.user.UpdateUserPersonalInformation.UpdateUserPersonalInformationRequest;
import es.hpgMethyl.usecases.user.UpdateUserPersonalInformation.UpdateUserPersonalInformationResponse;
import es.hpgMethyl.utils.FacesContextUtils;

@ManagedBean(name="userConfiguration")
@RequestScoped
public class UserConfiguration implements Serializable {

private static final long serialVersionUID = 4323418201301711899L;
    
    private String email;
    
    private String defaultLanguage;
    
    private String firstName;
    
    private String lastName;    
    
    private String oldPassword;
    
    private String newPassword;
    
    private String newPasswordVerification;
    
    private String password;
    
    private String passwordRecoveryQuestion;
    
    private String newPasswordRecoveryResponse;
	
	private UIComponent updatePersonalInformationComponent;
		
	public UserConfiguration() {
		this.email = null;
        this.defaultLanguage = null;
        this.firstName = null;
        this.lastName = null;
        this.oldPassword = null;
        this.newPassword = null;
        this.newPasswordVerification = null;
        this.password = null;
        this.passwordRecoveryQuestion = null;
        this.newPasswordRecoveryResponse = null;
	}
	
	@PostConstruct
	public void init() {
		
		User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
	        
	    if(user != null) {
	    	this.email = user.getEmail();
		    this.firstName = user.getFirstName();
		    this.lastName = user.getLastName();
		    this.passwordRecoveryQuestion = user.getPasswordRecoveryQuestion();
		    this.defaultLanguage = user.getDefaultLanguage();
	    }	     					
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
     * @return the defaultLanguage
     */
    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    /**
     * @param defaultLanguage the defaultLanguage to set
     */
    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the oldPassword
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * @param oldPassword the oldPassword to set
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
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
     * @return the passwordRecoveryQuestion
     */
    public String getPasswordRecoveryQuestion() {
        return passwordRecoveryQuestion;
    }

    /**
     * @param passwordRecoveryQuestion the passwordRecoveryQuestion to set
     */
    public void setPasswordRecoveryQuestion(String passwordRecoveryQuestion) {
        this.passwordRecoveryQuestion = passwordRecoveryQuestion;
    }

    /**
     * @return the newPasswordRecoveryResponse
     */
    public String getNewPasswordRecoveryResponse() {
        return newPasswordRecoveryResponse;
    }

    /**
     * @param newPasswordRecoveryResponse the newPasswordRecoveryResponse to set
     */
    public void setNewPasswordRecoveryResponse(String newPasswordRecoveryResponse) {
        this.newPasswordRecoveryResponse = newPasswordRecoveryResponse;
    }

	/**
	 * @return the updatePersonalInformationComponent
	 */
	public UIComponent getUpdatePersonalInformationComponent() {
		return updatePersonalInformationComponent;
	}

	/**
	 * @param updatePersonalInformationComponent the updatePersonalInformationComponent to set
	 */
	public void setUpdatePersonalInformationComponent(UIComponent updatePersonalInformationComponent) {
		this.updatePersonalInformationComponent = updatePersonalInformationComponent;
	}	
	
	public String updatePersonalInformation() {
		
		User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
        
        if(user == null) {
            return "pretty:home";   
        }
        
        try {
            UpdateUserPersonalInformationResponse response = new UpdateUserPersonalInformation(new UserDAOHibernate()).execute(
                    new UpdateUserPersonalInformationRequest(
                        user.getId(),
                        firstName,
                        lastName,
                        defaultLanguage
                    )
            );
            
            user = response.getUser();
            
            FacesContextUtils.setParameterFacesContextSession(FacesContextUtils.SESSION_USER, user);
            
            String successMessage = FacesContextUtils.geti18nMessage("general.updateSuccessfully");
            FacesContextUtils.setMessageInComponent(this.updatePersonalInformationComponent, FacesMessage.SEVERITY_INFO, successMessage, successMessage);
            
        } catch (UserNotFound | UpdateUserException e) {
            String defaultErrorMessage = FacesContextUtils.geti18nMessage("error.default");
            FacesContextUtils.setMessageInComponent(this.updatePersonalInformationComponent, FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
        }   
        
        return null;
	}
}
