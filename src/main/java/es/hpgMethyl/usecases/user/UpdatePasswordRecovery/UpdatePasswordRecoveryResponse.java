package es.hpgMethyl.usecases.user.UpdatePasswordRecovery;

import es.hpgMethyl.entities.User;

public class UpdatePasswordRecoveryResponse {

	private User user;

	public UpdatePasswordRecoveryResponse(User user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
}
