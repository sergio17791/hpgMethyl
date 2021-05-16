package es.hpgMethyl.usecases.file.CreateFile;

import es.hpgMethyl.dao.HPGMethylFileDAO;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.exceptions.CreateFileException;
import es.hpgMethyl.exceptions.DuplicatedFile;
import es.hpgMethyl.exceptions.SaveObjectException;

public class CreateFile {

	private HPGMethylFileDAO fileDAO;

	public CreateFile(HPGMethylFileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
	
	public CreateFileResponse execute(CreateFileRequest request) throws CreateFileException, DuplicatedFile {
		
		String fileName = request.getFileName();
		String folder = request.getFolder();
		
		if(this.fileDAO.existsFile(fileName, folder)) {
			throw new DuplicatedFile();
		}
		
		HPGMethylFile file = new HPGMethylFile();
		file.setUser(request.getUser());
		file.setFileName(fileName);
		file.setFolder(folder);
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
