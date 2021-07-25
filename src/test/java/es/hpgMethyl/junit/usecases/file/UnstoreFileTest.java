package es.hpgMethyl.junit.usecases.file;

import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.HPGMethylFileDAO;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.FileNotFound;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.HpgMethylException;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.exceptions.UpdateFileException;
import es.hpgMethyl.usecases.file.UnstoreFile.UnstoreFile;
import es.hpgMethyl.usecases.file.UnstoreFile.UnstoreFileRequest;
import es.hpgMethyl.usecases.file.UnstoreFile.UnstoreFileResponse;

public class UnstoreFileTest {

	private UnstoreFile unstoreFile;
	
	private HPGMethylFileDAO fileDAO;
	
	private HPGMethylFile file;
	
	@Before
	public void setUp() {
		this.fileDAO = Mockito.mock(HPGMethylFileDAO.class);
		this.unstoreFile = new UnstoreFile(this.fileDAO);
		this.file = new HPGMethylFile(
				UUID.randomUUID(),
				new Date(),
				new Date(),
				new User(),
				"test_4M_200nt_n3_r010.bwa.read1.fastq_convert.fastq",
				"/data/user/eea47a62-cdd3-44a9-b5b1-72836c8afe56/test_4M_200nt_n3_r010.bwa.read1.fastq_convert.fastq",
				Long.valueOf(1234),
				"fastq",
				Boolean.TRUE
		);
	}		
	
	@Test(expected = UpdateFileException.class)
	public void test_execute_givenAnErrorWhenGetFileObject_expectThrowUpdateFileException() throws HpgMethylException {
		
		UUID id = file.getId();
		
		UnstoreFileRequest request = new UnstoreFileRequest(id);
		
		Mockito.doThrow(GetObjectException.class).when(fileDAO).get(id);
		
		this.unstoreFile.execute(request);
	}
	
	@Test(expected = FileNotFound.class)
	public void test_execute_givenAnUnexistingFileID_expectThrowFileNotFoundException() throws HpgMethylException {
		
		UUID id = file.getId();
		
		UnstoreFileRequest request = new UnstoreFileRequest(id);
		
		Mockito.doReturn(null).when(fileDAO).get(id);
		
		this.unstoreFile.execute(request);
	}
	
	@Test(expected = UpdateFileException.class)
	public void test_execute_givenAnErrorWhenUpdateFileObject_expectThrowUpdateFileException() throws HpgMethylException {
		
		UUID id = file.getId();
		
		UnstoreFileRequest request = new UnstoreFileRequest(id);
		
		Mockito.doReturn(file).when(fileDAO).get(id);
		
		Mockito.doThrow(SaveObjectException.class).when(fileDAO).save(Mockito.any(HPGMethylFile.class));
		
		this.unstoreFile.execute(request);
	}
	
	@Test
	public void test_execute_givenValidRequest_expectHPGMethylFile() throws HpgMethylException {
		
		UUID id = file.getId();
		
		UnstoreFileRequest request = new UnstoreFileRequest(id);
		
		Mockito.doReturn(file).when(fileDAO).get(id);
		
		Mockito.doNothing().when(fileDAO).save(Mockito.any(HPGMethylFile.class));
		
		UnstoreFileResponse useCaseResponse = this.unstoreFile.execute(request);
		
		HPGMethylFile response = useCaseResponse.getFile();
		
		Assert.assertEquals(Boolean.FALSE, response.getStored());
	}
}
