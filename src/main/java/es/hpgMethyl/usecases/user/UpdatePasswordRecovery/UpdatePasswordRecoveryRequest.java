package es.hpgMethyl.usecases.user.UpdatePasswordRecovery;

import java.util.UUID;

public class UpdatePasswordRecoveryRequest {

	private UUID id;
	
	private String passwordRecoveryQuestion;
	
    private String passwordRecoveryResponse;

	public UpdatePasswordRecoveryRequest(UUID id, String passwordRecoveryQuestion, String passwordRecoveryResponse) {
		this.id = id;
		this.passwordRecoveryQuestion = passwordRecoveryQuestion;
		this.passwordRecoveryResponse = passwordRecoveryResponse;
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
}
