package es.hpgMethyl.usecases.user.GetUserByEmail;

import es.hpgMethyl.entities.User;

public class GetUserByEmailResponse {

	private User user;
	
	public GetUserByEmailResponse(User user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
}
