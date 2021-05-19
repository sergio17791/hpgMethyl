package es.hpgMethyl.usecases.file.ExistsFile;

import es.hpgMethyl.dao.HPGMethylFileDAO;

public class ExistsFile {

	private HPGMethylFileDAO fileDAO;

	public ExistsFile(HPGMethylFileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
	
	public ExistsFileResponse execute(ExistsFileRequest request) {
		
		Boolean exists = this.fileDAO.existsFile(request.getUser(), request.getFileName());
		
		return new ExistsFileResponse(exists);
	}
}
