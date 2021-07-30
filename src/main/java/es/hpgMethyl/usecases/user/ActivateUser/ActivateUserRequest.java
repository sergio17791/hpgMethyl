package es.hpgMethyl.usecases.user.ActivateUser;

import java.util.UUID;

public class ActivateUserRequest {

	private UUID id;
	
	public ActivateUserRequest(UUID id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}
}
