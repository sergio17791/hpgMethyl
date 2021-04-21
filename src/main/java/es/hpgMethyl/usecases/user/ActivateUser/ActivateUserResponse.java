package es.hpgMethyl.usecases.user.ActivateUser;

import es.hpgMethyl.entities.User;

public class ActivateUserResponse {

	private User user;

	public ActivateUserResponse(User user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
}
