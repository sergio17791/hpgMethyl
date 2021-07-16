package es.hpgMethyl.usecases.file.CheckFileIsPending;

import es.hpgMethyl.dao.HPGMethylFileDAO;

public class CheckFileIsPending {
	
	private HPGMethylFileDAO fileDAO;

	public CheckFileIsPending(HPGMethylFileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
	
	public CheckFileIsPendingResponse execute(CheckFileIsPendingRequest request) {
		
		Boolean pending = this.fileDAO.checkFileIsPending(request.getUser(), request.getFileName());
		
		return new CheckFileIsPendingResponse(pending);
	}
}
