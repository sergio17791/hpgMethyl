package es.hpgMethyl.usecases.user.GetUser;

import es.hpgMethyl.entities.User;

public class GetUserResponse {

	private User user;

	public GetUserResponse(User user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
}
