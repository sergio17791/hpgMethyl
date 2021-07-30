package es.hpgMethyl.usecases.user.SignupUser;

public class SignupUserRequest {

	private String email;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String passwordRecoveryQuestion;
	
	private String passwordRecoveryResponse;
	
	private String defaultLanguage;

	public SignupUserRequest(
			String email, 
			String password, 
			String firstName, 
			String lastName,
			String passwordRecoveryQuestion,
			String passwordRecoveryResponse,
			String defaultLanguage
	) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passwordRecoveryQuestion = passwordRecoveryQuestion;
		this.passwordRecoveryResponse = passwordRecoveryResponse;
		this.defaultLanguage = defaultLanguage;
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
}
