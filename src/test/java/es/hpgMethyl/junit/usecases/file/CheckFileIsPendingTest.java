package es.hpgMethyl.junit.usecases.file;

import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;

import es.hpgMethyl.dao.HPGMethylFileDAO;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.types.UserRole;
import es.hpgMethyl.usecases.file.CheckFileIsPending.CheckFileIsPending;
import es.hpgMethyl.usecases.file.CheckFileIsPending.CheckFileIsPendingRequest;
import es.hpgMethyl.usecases.file.CheckFileIsPending.CheckFileIsPendingResponse;

public class CheckFileIsPendingTest {

	private HPGMethylFileDAO fileDAO;
	
	private CheckFileIsPending checkFileIsPending;
	
	private User user;
	
	@Before
	public void setUp() {
		this.fileDAO = Mockito.mock(HPGMethylFileDAO.class);
		this.checkFileIsPending = new CheckFileIsPending(this.fileDAO);		
		
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
	
	public void test_execute_givenNonPendingFile_expectFalse() {
		
		String fileName = "test_4M_200nt_n3_r010.bwa.read1.fastq_convert.fastq";
		
		CheckFileIsPendingRequest request = new CheckFileIsPendingRequest(user, fileName);
		
		Mockito.doReturn(false).when(fileDAO).checkFileIsPending(user, fileName);
		
		CheckFileIsPendingResponse response = this.checkFileIsPending.execute(request);
		
		Assert.assertFalse(response.getPending());
	}
	
	public void test_execute_givenPendingtFile_expectTrue() {
		
		String fileName = "test_4M_100nt_n3_r010.bwa.read1.fastq_convert.fastq";
		
		CheckFileIsPendingRequest request = new CheckFileIsPendingRequest(user, fileName);
		
		Mockito.doReturn(true).when(fileDAO).checkFileIsPending(user, fileName);
		
		CheckFileIsPendingResponse response = this.checkFileIsPending.execute(request);
		
		Assert.assertTrue(response.getPending());
	}
}
