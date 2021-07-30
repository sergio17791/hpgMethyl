package es.hpgMethyl.usecases.file.CreateFile;

import es.hpgMethyl.entities.HPGMethylFile;

public class CreateFileResponse {

	private HPGMethylFile file;

	public CreateFileResponse(HPGMethylFile file) {
		this.file = file;
	}

	/**
	 * @return the file
	 */
	public HPGMethylFile getFile() {
		return file;
	}
}
