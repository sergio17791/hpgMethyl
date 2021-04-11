package es.hpgMethyl.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;

import es.hpgMethyl.dao.hibernate.UserDAOHibernate;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.ChangePasswordException;
import es.hpgMethyl.exceptions.InvalidCredentials;
import es.hpgMethyl.exceptions.UpdateUserException;
import es.hpgMethyl.exceptions.UserNotFound;
import es.hpgMethyl.usecases.user.ChangePassword.ChangePassword;
import es.hpgMethyl.usecases.user.ChangePassword.ChangePasswordRequest;
import es.hpgMethyl.usecases.user.ChangePassword.ChangePasswordResponse;
import es.hpgMethyl.usecases.user.LoginUser.LoginUser;
import es.hpgMethyl.usecases.user.LoginUser.LoginUserRequest;
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
    private String passwordRecoveryResponse;
	private UIComponent updatePersonalInformationComponent;
	private UIComponent changePasswordComponent;
	private UIComponent updatePasswordRecoveryComponent;
		
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
        this.passwordRecoveryResponse = null;
	}
	
	@PostConstruct
	public void init() {
		
		User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
	        
	    if(user != null) {
	    	this.email = user.getEmail();
		    this.firstName = user.getFirstName();
		    this.lastName = user.getLastName();
		    this.defaultLanguage = user.getDefaultLanguage();
		    this.passwordRecoveryQuestion = user.getPasswordRecoveryQuestion();
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
     * @return the passwordRecoveryResponse
     */
    public String getPasswordRecoveryResponse() {
        return passwordRecoveryResponse;
    }

    /**
     * @param passwordRecoveryResponse the passwordRecoveryResponse to set
     */
    public void setPasswordRecoveryResponse(String passwordRecoveryResponse) {
        this.passwordRecoveryResponse = passwordRecoveryResponse;
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

	/**
	 * @return the updatePasswordRecoveryComponent
	 */
	public UIComponent getUpdatePasswordRecoveryComponent() {
		return updatePasswordRecoveryComponent;
	}

	/**
	 * @param updatePasswordRecoveryComponent the updatePasswordRecoveryComponent to set
	 */
	public void setUpdatePasswordRecoveryComponent(UIComponent updatePasswordRecoveryComponent) {
		this.updatePasswordRecoveryComponent = updatePasswordRecoveryComponent;
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
	
	public String changePassword() {
		
		User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
        
        if(user == null) {
            return "pretty:home";   
        }
        
        try {
			new LoginUser(new UserDAOHibernate()).execute(
					new LoginUserRequest(user.getEmail(), oldPassword)
			);
		} catch (InvalidCredentials e1) {
			String defaultErrorMessage = FacesContextUtils.geti18nMessage("error.invalidCredentials");
			FacesContextUtils.setMessageInComponent(this.changePasswordComponent, FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
			return null;
		}
        
		try {
			ChangePasswordResponse response = new ChangePassword(new UserDAOHibernate()).execute(
					new ChangePasswordRequest(user.getId(), newPassword)
			);
			
			FacesContextUtils.setParameterFacesContextSession(FacesContextUtils.SESSION_USER, response.getUser());
			
			String successMessage = FacesContextUtils.geti18nMessage("general.updateSuccessfully");
            FacesContextUtils.setMessageInComponent(this.changePasswordComponent, FacesMessage.SEVERITY_INFO, successMessage, successMessage);
						
		} catch (ChangePasswordException e) {
			String defaultErrorMessage = FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.changePasswordComponent, FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);						
		} catch (UserNotFound e) {
			return "pretty:home";  
		}
		
		return null;
	}
	
	public String updatePasswordRecovery() {
		
		String successMessage = FacesContextUtils.geti18nMessage("general.updateSuccessfully");
        FacesContextUtils.setMessageInComponent(this.updatePasswordRecoveryComponent, FacesMessage.SEVERITY_INFO, successMessage, successMessage);
        
        return null;
	}
	
	public void cleanInputComponent(ComponentSystemEvent event) throws AbortProcessingException {
		UIInput input = (UIInput) event.getComponent();
		input.setValue("");
	}
}
