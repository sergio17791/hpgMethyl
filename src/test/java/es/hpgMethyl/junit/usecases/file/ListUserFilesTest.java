package es.hpgMethyl.junit.usecases.file;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;

import es.hpgMethyl.dao.HPGMethylFileDAO;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.types.UserRole;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFiles;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFilesRequest;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFilesResponse;

public class ListUserFilesTest {

	private HPGMethylFileDAO fileDAO;
	
	private ListUserFiles listUserFiles;
	
	private User user;
	
	@Before
	public void setUp() {
		this.fileDAO = Mockito.mock(HPGMethylFileDAO.class);
		this.listUserFiles = new ListUserFiles(this.fileDAO);		
		
		this.user = new User(
				UUID.randomUUID(), 
				new Date(), 
				new Date(), 
				"First name", 
				"Last name", 
				"test@test.com", 
				"yk7wtMR4rBr/UPCThvmoiSiw5Lf+hD+VwbQVeb21zXPOi5XJqRoGgxGml1JQj5DdxahbDV+QysvAEt44ZVvA6qrUifWJStKKlL4LGz6rOwjVtg9AblQBFWW2CHxZ85CTN+exdOlKU/cTVjC3uyCaQs6cS/ckzk1DVZPgTZT5lBY=", 
				"Lm7jSnhSQYVOh3mQHToscnO_CV0",
				"Recovery question",
				"uKjTIA2jD1XUhcL2IcYC52FMS5ahYHcpwrt2qyDefsNOTx68reYWiu57ExBIq0KUJHmmeWh6n/bxfpnJ7PouZdaanp3NthhFN6pFMuvvBPWRcfdcpkuL54fntJskAIfMXrsOyEbVLbOuegPi3q0zmQxM3BTB27lKal6k2ZjVcu8=",
				"HYq3kHHP0mcFia25J5qp8YYN8TE",
				true,
				"es",
				UserRole.USER
			);
	}
	
	public void test_execute_givenUserWithoutFiles_expectEmptyList() {
		
		ListUserFilesRequest request = new ListUserFilesRequest(user);
		
		Mockito.doReturn(new ArrayList<HPGMethylFile>()).when(fileDAO).list(user);
		
		ListUserFilesResponse response = this.listUserFiles.execute(request);
		
		Assert.assertTrue(response.getFiles().isEmpty());
	}
	
	public void test_execute_givenUserWithFiles_expectList() {
		
		ListUserFilesRequest request = new ListUserFilesRequest(user);
		
		List<HPGMethylFile> filesList = new ArrayList<HPGMethylFile>();
		filesList.add(new HPGMethylFile());
		filesList.add(new HPGMethylFile());
		filesList.add(new HPGMethylFile());
		
		Mockito.doReturn(filesList).when(fileDAO).list(user);
		
		ListUserFilesResponse response = this.listUserFiles.execute(request);
		
		Assert.assertFalse(response.getFiles().isEmpty());
	}
}
