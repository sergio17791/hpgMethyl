package es.hpgMethyl.usecases.user.DeactivateUser;

import java.util.UUID;

public class DeactivateUserRequest {

	private UUID id;
	
	public DeactivateUserRequest(UUID id) {
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
