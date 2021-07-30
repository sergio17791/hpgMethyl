package es.hpgMethyl.usecases.user.UpdateUserPersonalInformation;

import java.util.UUID;

import es.hpgMethyl.types.UserRole;

public class UpdateUserPersonalInformationRequest {

	private UUID id;
	
	private String firstName;
	
	private String lastName;
	
	private String defaultLanguage;
	
	private UserRole role;

	public UpdateUserPersonalInformationRequest(UUID id, String firstName, String lastName, String defaultLanguage, UserRole role) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.defaultLanguage = defaultLanguage;
		this.role = role;
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
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
	 * @return the role
	 */
	public UserRole getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}
}
