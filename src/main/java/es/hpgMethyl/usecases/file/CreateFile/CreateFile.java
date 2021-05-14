package es.hpgMethyl.usecases.file.CreateFile;

import es.hpgMethyl.dao.FileDAO;
import es.hpgMethyl.entities.File;
import es.hpgMethyl.exceptions.CreateFileException;
import es.hpgMethyl.exceptions.DuplicatedFile;
import es.hpgMethyl.exceptions.SaveObjectException;

public class CreateFile {

	private FileDAO fileDAO;

	public CreateFile(FileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
	
	public CreateFileResponse execute(CreateFileRequest request) throws CreateFileException, DuplicatedFile {
		
		String fileName = request.getFileName();
		String folder = request.getFolder();
		
		if(this.fileDAO.existsFile(fileName, folder)) {
			throw new DuplicatedFile();
		}
		
		File file = new File();
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
