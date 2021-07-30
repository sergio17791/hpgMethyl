package es.hpgMethyl.usecases.user.SignupUser;

import es.hpgMethyl.entities.User;

public class SignupUserResponse {

	private User user;

	public SignupUserResponse(User user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
}
