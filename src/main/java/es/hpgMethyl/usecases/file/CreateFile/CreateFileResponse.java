package es.hpgMethyl.usecases.file.CreateFile;

import es.hpgMethyl.entities.File;

public class CreateFileResponse {

	private File file;

	public CreateFileResponse(File file) {
		this.file = file;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}
}
