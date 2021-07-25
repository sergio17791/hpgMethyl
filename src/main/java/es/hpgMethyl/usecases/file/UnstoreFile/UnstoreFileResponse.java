package es.hpgMethyl.usecases.file.UnstoreFile;

import es.hpgMethyl.entities.HPGMethylFile;

public class UnstoreFileResponse {

	private HPGMethylFile file;
	
	public UnstoreFileResponse(HPGMethylFile file) {
		this.file = file;
	}

	/**
	 * @return the file
	 */
	public HPGMethylFile getFile() {
		return file;
	}
}
