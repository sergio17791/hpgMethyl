package es.hpgMethyl.usecases.user.ChangePassword;

import es.hpgMethyl.entities.User;

public class ChangePasswordResponse {

	private User user;

	public ChangePasswordResponse(User user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
}
