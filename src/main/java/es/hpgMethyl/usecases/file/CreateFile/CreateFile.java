package es.hpgMethyl.usecases.file.CreateFile;

import es.hpgMethyl.dao.HPGMethylFileDAO;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.CreateFileException;
import es.hpgMethyl.exceptions.DuplicatedFile;
import es.hpgMethyl.exceptions.SaveObjectException;

public class CreateFile {

	private HPGMethylFileDAO fileDAO;

	public CreateFile(HPGMethylFileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
	
	public CreateFileResponse execute(CreateFileRequest request) throws CreateFileException, DuplicatedFile {
		
		User user = request.getUser();
		String fileName = request.getFileName();
		
		if(this.fileDAO.existsFile(user, fileName)) {
			throw new DuplicatedFile();
		}
		
		HPGMethylFile file = new HPGMethylFile();
		file.setUser(user);
		file.setFileName(fileName);
		file.setPath(request.getPath());
		file.setSize(request.getSize());
		file.setContentType(request.getContentType());
		
		try {
			this.fileDAO.save(file);
		} catch (SaveObjectException e) {
			throw new CreateFileException(e);
		}
		
		return new CreateFileResponse(file);		
	}
}
