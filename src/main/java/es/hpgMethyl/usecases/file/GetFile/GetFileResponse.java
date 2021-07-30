package es.hpgMethyl.usecases.file.GetFile;

import es.hpgMethyl.entities.HPGMethylFile;

public class GetFileResponse {

	private HPGMethylFile file;

	public GetFileResponse(HPGMethylFile file) {
		this.file = file;
	}

	/**
	 * @return the file
	 */
	public HPGMethylFile getFile() {
		return file;
	}	
}
