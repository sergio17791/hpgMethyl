package es.hpgMethyl.junit.usecases.file;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.HPGMethylFileDAO;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.CreateFileException;
import es.hpgMethyl.exceptions.DuplicatedFile;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.usecases.file.CreateFile.CreateFile;
import es.hpgMethyl.usecases.file.CreateFile.CreateFileRequest;
import es.hpgMethyl.usecases.file.CreateFile.CreateFileResponse;

public class CreateFileTest {
	
	private CreateFile createFile;
	
	private HPGMethylFileDAO fileDAO;
	
	@Before
	public void setUp() {
		this.fileDAO = Mockito.mock(HPGMethylFileDAO.class);
		this.createFile = new CreateFile(this.fileDAO);
	}
	
	@Test(expected = DuplicatedFile.class)
	public void test_execute_givenAnExistingFile_expectThrowDuplicatedFile() throws CreateFileException, DuplicatedFile {
		
		String fileName = "test_4M_100nt_n3_r010.bwa.read1.fastq_convert.fastq";
		String path = "/files/users/03711b01-5812-4a00-a039-e8eee346fb6b/test_4M_100nt_n3_r010.bwa.read1.fastq_convert.fastq";
		
		Mockito.doReturn(true).when(fileDAO).existsFile(path);
		
		CreateFileRequest request = new CreateFileRequest(
				new User(),
				fileName,
				path,
				Long.valueOf(123),
				"text/plain"
		);
		
		this.createFile.execute(request);
	}
	
	@Test(expected = CreateFileException.class)
	public void test_execute_givenConstraintViolation_expectThrowCreateFileException() throws CreateFileException, DuplicatedFile, SaveObjectException {
		
		String fileName = "test_4M_100nt_n3_r010.bwa.read1.fastq_convert.fastq";
		String path = "/files/users/03711b01-5812-4a00-a039-e8eee346fb6b/test_4M_100nt_n3_r010.bwa.read1.fastq_convert.fastq";
		
		Mockito.doReturn(false).when(fileDAO).existsFile(path);
		
		Mockito.doThrow(SaveObjectException.class).when(fileDAO).save(Mockito.any(HPGMethylFile.class));
		
		CreateFileRequest request = new CreateFileRequest(
				new User(),
				fileName,
				path,
				Long.valueOf(123),
				"text/plain"
		);
		
		this.createFile.execute(request);
	}
	
	public void test_execute_givenAnUnexistingFile_expectFile() throws CreateFileException, DuplicatedFile, SaveObjectException {
		
		User user = new User();
		String fileName = "test_4M_100nt_n3_r010.bwa.read1.fastq_convert.fastq";
		String path = "/files/users/03711b01-5812-4a00-a039-e8eee346fb6b/test_4M_100nt_n3_r010.bwa.read1.fastq_convert.fastq";
		Long size = Long.valueOf(123);
		String contentType = "text/plain";
		
		Mockito.doReturn(false).when(fileDAO).existsFile(path);
		
		Mockito.doNothing().when(fileDAO).save(Mockito.any(HPGMethylFile.class));
		
		CreateFileRequest request = new CreateFileRequest(
				user,
				fileName,
				path,
				size,
				contentType				
		);
		
		CreateFileResponse useCaseResponse = this.createFile.execute(request);
		
		HPGMethylFile response = useCaseResponse.getFile();
		
		Assert.assertEquals(request.getFileName(), response.getFileName());
		Assert.assertEquals(request.getPath(), response.getPath());
		Assert.assertEquals(request.getSize(), response.getSize());
		Assert.assertEquals(request.getContentType(), response.getContentType());
	}
}
