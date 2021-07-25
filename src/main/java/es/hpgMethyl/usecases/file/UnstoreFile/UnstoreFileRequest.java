package es.hpgMethyl.usecases.file.UnstoreFile;

import java.util.UUID;

public class UnstoreFileRequest {

	private UUID id;
	
	public UnstoreFileRequest(UUID id) {
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
