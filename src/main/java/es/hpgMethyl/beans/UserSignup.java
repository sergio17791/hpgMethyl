package es.hpgMethyl.beans;

import java.io.Serializable;
import java.util.Locale;

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
import es.hpgMethyl.exceptions.DuplicatedEmail;
import es.hpgMethyl.exceptions.SignupUserException;
import es.hpgMethyl.usecases.user.SignupUser.SignupUser;
import es.hpgMethyl.usecases.user.SignupUser.SignupUserRequest;
import es.hpgMethyl.usecases.user.SignupUser.SignupUserResponse;
import es.hpgMethyl.utils.FacesContextUtils;

@ManagedBean(name="userSignup")
@RequestScoped
public class UserSignup implements Serializable {

	private static final long serialVersionUID = -5391509907036611627L;
	
	private String email;
	private String emailVerification;
	private String firstName;
	private String lastName;
    private String password;
    private String passwordRecoveryQuestion;
    private String passwordRecoveryResponse;
    private String passwordVerification;
    private String defaultLanguage;
    private UIComponent signupComponent;
    
    public UserSignup() {
    	this.email = null;
    	this.emailVerification = null;
    	this.firstName = null;
    	this.lastName = null;
    	this.password = null;
    	this.passwordRecoveryQuestion = null;
    	this.passwordRecoveryResponse = null;
    	this.passwordVerification = null;
    	this.defaultLanguage = null;
    }
    
    @PostConstruct
    public void init() {
    	LanguageSelector languageSelector = (LanguageSelector) FacesContextUtils.getBean("languageSelector");		
		Locale language = languageSelector.getLanguage();
		this.defaultLanguage = language.getLanguage();
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
	 * @return the emailVerification
	 */
	public String getEmailVerification() {
		return emailVerification;
	}

	/**
	 * @param emailVerification the emailVerification to set
	 */
	public void setEmailVerification(String emailVerification) {
		this.emailVerification = emailVerification;
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
	 * @return the passwordVerification
	 */
	public String getPasswordVerification() {
		return passwordVerification;
	}

	/**
	 * @param passwordVerification the passwordVerification to set
	 */
	public void setPasswordVerification(String passwordVerification) {
		this.passwordVerification = passwordVerification;
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
	 * @return the signupComponent
	 */
	public UIComponent getSignupComponent() {
		return signupComponent;
	}

	/**
	 * @param signupComponent the signupComponent to set
	 */
	public void setSignupComponent(UIComponent signupComponent) {
		this.signupComponent = signupComponent;
	}

	public String processUserRegistration() {
			
		try {
			SignupUserResponse signupUserResponse = new SignupUser(new UserDAOHibernate()).execute(
				new SignupUserRequest(
					this.getEmail(),
					this.getPassword(),
					this.getFirstName(),
					this.getLastName(),
					this.getPasswordRecoveryQuestion(),
					this.getPasswordRecoveryResponse().replaceAll("\\s","").toLowerCase(),
					this.getDefaultLanguage()
				)				
			);
			
			User user = signupUserResponse.getUser();
			
			LanguageSelector languageSelector = (LanguageSelector) FacesContextUtils.getBean("languageSelector");		
			languageSelector.changeLanguage(user.getDefaultLanguage());
			
			FacesContextUtils.setParameterFacesContextSession(FacesContextUtils.SESSION_USER, user);
			
			return "pretty:home";				
		} catch (DuplicatedEmail e) {
			String duplicatedEmailMessage = FacesContextUtils.geti18nMessage("signup.duplicatedEmail");
			FacesContextUtils.setMessageInComponent(this.getSignupComponent(), FacesMessage.SEVERITY_ERROR, duplicatedEmailMessage, duplicatedEmailMessage);			
		} catch (SignupUserException e) {
			String defaultErrorMessage = FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.getSignupComponent(), FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
		}
		
    	this.setPassword("");
    	this.setPasswordRecoveryResponse("");
    	this.setPasswordVerification("");
		
		return null;
	}
	
	public void cleanInputComponent(ComponentSystemEvent event) throws AbortProcessingException {
		UIInput input = (UIInput) event.getComponent();
		input.setValue("");
	}
}
