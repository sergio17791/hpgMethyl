package es.hpgMethyl.junit.usecases.file;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.HPGMethylFileDAO;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.exceptions.FileNotFound;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.HpgMethylException;
import es.hpgMethyl.usecases.file.GetFile.GetFile;
import es.hpgMethyl.usecases.file.GetFile.GetFileRequest;
import es.hpgMethyl.usecases.file.GetFile.GetFileResponse;

public class GetFileTest {

	private GetFile getFile;
	
	private HPGMethylFileDAO fileDAO;
	
	@Before
	public void setUp() {
		this.fileDAO = Mockito.mock(HPGMethylFileDAO.class);
		this.getFile = new GetFile(this.fileDAO);
	}
	
	@Test(expected = GetObjectException.class)
	public void test_execute_givenAnErrorWhenGetFileObject_expectThrowGetObjectException() throws HpgMethylException {
		
		UUID id = UUID.randomUUID();
		
		Mockito.doThrow(GetObjectException.class).when(fileDAO).get(id);
		
		this.getFile.execute(new GetFileRequest(id));
	}
	
	@Test(expected = FileNotFound.class)
	public void test_execute_givenNonExistentFile_expectThrowFileNotFound() throws HpgMethylException {
		
		UUID id = UUID.randomUUID();
		
		Mockito.doReturn(null).when(fileDAO).get(id);
		
		this.getFile.execute(new GetFileRequest(id));
	}
	
	@Test
	public void test_execute_givenExistentID_expectFile() throws HpgMethylException {
		
		UUID id = UUID.randomUUID();
		
		HPGMethylFile file = new HPGMethylFile();
		file.setId(id);
		
		Mockito.doReturn(file).when(fileDAO).get(id);
		
		GetFileResponse response = this.getFile.execute(new GetFileRequest(id));
		
		Assert.assertEquals(id, response.getFile().getId());
	}	
}
