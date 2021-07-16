package es.hpgMethyl.usecases.file.UnstoreFile;

import java.util.UUID;

import es.hpgMethyl.dao.HPGMethylFileDAO;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.exceptions.FileNotFound;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.exceptions.UpdateFileException;

public class UnstoreFile {

	private HPGMethylFileDAO fileDAO;
	
	public UnstoreFile(HPGMethylFileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
	
	public UnstoreFileResponse execute(UnstoreFileRequest request) throws FileNotFound, UpdateFileException {
		
		UUID id = request.getId();
		
		HPGMethylFile file;
		try {
			file = this.fileDAO.get(id);
		} catch (GetObjectException e) {
			throw new UpdateFileException(e);
		}
			
		if(file == null) {
			throw new FileNotFound("File " + id + " not found");
		}
		
		file.setStored(false);
		
		try {
			this.fileDAO.save(file);
		} catch(SaveObjectException e) {
			throw new UpdateFileException(e);
		}
		
		return new UnstoreFileResponse(file);
	}
}
