package es.hpgMethyl.usecases.file.GetFile;

import java.util.UUID;

import es.hpgMethyl.dao.HPGMethylFileDAO;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.exceptions.FileNotFound;
import es.hpgMethyl.exceptions.GetObjectException;

public class GetFile {

	private HPGMethylFileDAO fileDAO;
	
	public GetFile(HPGMethylFileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
	
	public GetFileResponse execute(GetFileRequest request) throws GetObjectException, FileNotFound {
		
		UUID id = request.getId();
		
		HPGMethylFile file = this.fileDAO.get(id);
			
		if(file == null) {
			throw new FileNotFound("File " + id + " not found");
		}
			
		return new GetFileResponse(file);	
	}
}
