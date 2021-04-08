package es.hpgMethyl.usecases.user.GetUser;

import java.util.UUID;

public class GetUserRequest {

	private UUID id;

	public GetUserRequest(UUID id) {
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
