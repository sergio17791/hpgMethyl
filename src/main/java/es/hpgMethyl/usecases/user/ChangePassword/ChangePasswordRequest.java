package es.hpgMethyl.usecases.user.ChangePassword;

import es.hpgMethyl.entities.User;

public class ChangePasswordRequest {
	
	private User user;
	
	private String newPassword;

	public ChangePasswordRequest(User user, String newPassword) {
		this.user = user;
		this.newPassword = newPassword;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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
