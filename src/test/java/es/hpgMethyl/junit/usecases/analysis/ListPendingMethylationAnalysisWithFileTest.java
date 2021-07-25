package es.hpgMethyl.junit.usecases.analysis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;

import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.types.UserRole;
import es.hpgMethyl.usecases.analysis.ListPendingMethylationAnalysisWithFile.ListPendingMethylationAnalysisWithFile;
import es.hpgMethyl.usecases.analysis.ListPendingMethylationAnalysisWithFile.ListPendingMethylationAnalysisWithFileRequest;
import es.hpgMethyl.usecases.analysis.ListPendingMethylationAnalysisWithFile.ListPendingMethylationAnalysisWithFileResponse;

public class ListPendingMethylationAnalysisWithFileTest {

	private ListPendingMethylationAnalysisWithFile listPendingMethylationAnalysisWithFile;
	
	private AnalysisRequestDAO analysisRequestDAO;
	
	private User user;
	
	private HPGMethylFile file;
	
	@Before
	public void setUp() {
		this.analysisRequestDAO = Mockito.mock(AnalysisRequestDAO.class);
		this.listPendingMethylationAnalysisWithFile = new ListPendingMethylationAnalysisWithFile(this.analysisRequestDAO);		
		
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
		
		this.file = new HPGMethylFile(
				UUID.randomUUID(),
				new Date(),
				new Date(),
				user,
				"test_4M_200nt_n3_r010.bwa.read1.fastq_convert.fastq",
				"/data/user/eea47a62-cdd3-44a9-b5b1-72836c8afe56/test_4M_200nt_n3_r010.bwa.read1.fastq_convert.fastq",
				Long.valueOf(1234),
				"fastq",
				Boolean.TRUE
		);
	}
	
	public void test_execute_givenNonExistingPendingAnalysisWithFile_expectEmptyList() {
		
		ListPendingMethylationAnalysisWithFileRequest request = new ListPendingMethylationAnalysisWithFileRequest(user, file);
		
		Mockito.doReturn(new ArrayList<AnalysisRequest>()).when(analysisRequestDAO).list(user);
		
		ListPendingMethylationAnalysisWithFileResponse response = this.listPendingMethylationAnalysisWithFile.execute(request);
		
		Assert.assertTrue(response.getAnalysisRequestList().isEmpty());
	}
	
	public void test_execute_givenExistingPendingAnalysisWithFile_expectList() {
		
		ListPendingMethylationAnalysisWithFileRequest request = new ListPendingMethylationAnalysisWithFileRequest(user, file);
		
		List<AnalysisRequest> analysisRequestList = new ArrayList<AnalysisRequest>();
		analysisRequestList.add(new AnalysisRequest());
		analysisRequestList.add(new AnalysisRequest());
		analysisRequestList.add(new AnalysisRequest());
		
		Mockito.doReturn(analysisRequestList).when(analysisRequestDAO).list(user);
		
		ListPendingMethylationAnalysisWithFileResponse response = this.listPendingMethylationAnalysisWithFile.execute(request);
		
		Assert.assertFalse(response.getAnalysisRequestList().isEmpty());
	}
}
