package es.hpgMethyl.usecases.user.DeactivateUser;

import es.hpgMethyl.entities.User;

public class DeactivateUserResponse {

	private User user;

	public DeactivateUserResponse(User user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
}
