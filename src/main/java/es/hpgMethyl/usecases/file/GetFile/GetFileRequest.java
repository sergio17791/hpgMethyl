package es.hpgMethyl.usecases.file.GetFile;

import java.util.UUID;

public class GetFileRequest {

	private UUID id;

	public GetFileRequest(UUID id) {
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
