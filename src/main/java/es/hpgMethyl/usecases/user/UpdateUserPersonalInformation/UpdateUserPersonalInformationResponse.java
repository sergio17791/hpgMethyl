package es.hpgMethyl.usecases.user.UpdateUserPersonalInformation;

import es.hpgMethyl.entities.User;

public class UpdateUserPersonalInformationResponse {

	private User user;

	public UpdateUserPersonalInformationResponse(User user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
}
