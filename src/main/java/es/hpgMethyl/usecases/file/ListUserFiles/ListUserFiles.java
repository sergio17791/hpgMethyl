package es.hpgMethyl.usecases.file.ListUserFiles;

import java.util.List;

import es.hpgMethyl.dao.HPGMethylFileDAO;
import es.hpgMethyl.entities.HPGMethylFile;

public class ListUserFiles {

	private HPGMethylFileDAO fileDAO;

	public ListUserFiles(HPGMethylFileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
	
	public ListUserFilesResponse execute(ListUserFilesRequest request) {
		
		List<HPGMethylFile> files = this.fileDAO.list(request.getUser());
		
		return new ListUserFilesResponse(files);
	}
}
