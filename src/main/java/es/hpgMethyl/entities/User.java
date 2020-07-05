package es.hpgMethyl.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "\"user\"")
public class User extends BaseEntity {

	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "password_salt", nullable = false)
	private String passwordSalt;
	
	@Column(name = "password_recovery_question", nullable = false)
	private String passwordRecoveryQuestion;
	
	@Column(name = "password_recovery_response", nullable = false)
	private String passwordRecoveryResponse;
	
	@Column(name = "password_recovery_response_salt", nullable = false)
	private String passwordRecoveryResponseSalt;
	
	@Column(name = "active", nullable = false)
	private Boolean active;
	
	public User() {
		super();
		this.active = true;
	}
	
	public User(
			UUID id, 
			Date createdAt, 
			Date updatedAt, 
			String firstName, 
			String lastName, 
			String email,
			String password,
			String passwordSalt,
			String passwordRecoveryQuestion,
			String passwordRecoveryResponse,
			String passwordRecoveryResponseSalt,
			Boolean active
	) {
		super(id, createdAt, updatedAt);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.passwordSalt = passwordSalt;
		this.passwordRecoveryQuestion = passwordRecoveryQuestion;
		this.passwordRecoveryResponse = passwordRecoveryResponse;
		this.passwordRecoveryResponseSalt = passwordRecoveryResponseSalt;
		this.active = active;
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
	 * @return the passwordSalt
	 */
	public String getPasswordSalt() {
		return passwordSalt;
	}

	/**
	 * @param passwordSalt the passwordSalt to set
	 */
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
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
	 * @return the passwordRecoveryResponseSalt
	 */
	public String getPasswordRecoveryResponseSalt() {
		return passwordRecoveryResponseSalt;
	}

	/**
	 * @param passwordRecoveryResponseSalt the passwordRecoveryResponseSalt to set
	 */
	public void setPasswordRecoveryResponseSalt(String passwordRecoveryResponseSalt) {
		this.passwordRecoveryResponseSalt = passwordRecoveryResponseSalt;
	}

	/**
	 * @return the active
	 */
	public Boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}	
}
