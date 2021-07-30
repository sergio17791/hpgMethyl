package es.hpgMethyl.usecases.user.LoginUser;

import es.hpgMethyl.entities.User;

public class LoginUserResponse {

	private User user;
	
	public LoginUserResponse(User user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
}
