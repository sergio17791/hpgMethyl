package es.hpgMethyl.usecases.file.ListUserFiles;

import java.util.List;

import es.hpgMethyl.entities.HPGMethylFile;

public class ListUserFilesResponse {

	private List<HPGMethylFile> files;

	public ListUserFilesResponse(List<HPGMethylFile> files) {
		this.files = files;
	}

	public List<HPGMethylFile> getFiles() {
		return files;
	}
}
