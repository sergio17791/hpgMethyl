package es.hpgMethyl.usecases.user.ChangePassword;

import java.util.UUID;

public class ChangePasswordRequest {
	
	private UUID id;
	
	private String newPassword;

	public ChangePasswordRequest(UUID id, String newPassword) {
		this.id = id;
		this.newPassword = newPassword;
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
}
