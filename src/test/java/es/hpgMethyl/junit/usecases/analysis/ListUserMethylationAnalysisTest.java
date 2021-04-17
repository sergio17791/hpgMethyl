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
import es.hpgMethyl.entities.User;
import es.hpgMethyl.types.UserRole;
import es.hpgMethyl.usecases.analysis.ListUserMethylationAnalysis.ListUserMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.ListUserMethylationAnalysis.ListUserMethylationAnalysisRequest;
import es.hpgMethyl.usecases.analysis.ListUserMethylationAnalysis.ListUserMethylationAnalysisResponse;

public class ListUserMethylationAnalysisTest {

	private ListUserMethylationAnalysis listMethylationAnalysis;
	
	private AnalysisRequestDAO analysisRequestDAO;
	
	private User user;
	
	@Before
	public void setUp() {
		this.analysisRequestDAO = Mockito.mock(AnalysisRequestDAO.class);
		this.listMethylationAnalysis = new ListUserMethylationAnalysis(this.analysisRequestDAO);		
		
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
	
	public void test_execute_givenUserWithoutAnalysis_expectEmptyList() {
		
		ListUserMethylationAnalysisRequest request = new ListUserMethylationAnalysisRequest(user);
		
		Mockito.doReturn(new ArrayList<AnalysisRequest>()).when(analysisRequestDAO).list(user);
		
		ListUserMethylationAnalysisResponse response = this.listMethylationAnalysis.execute(request);
		
		Assert.assertTrue(response.getAnalysisRequestList().isEmpty());
	}
	
	public void test_execute_givenUserWithAnalysis_expectList() {
		
		ListUserMethylationAnalysisRequest request = new ListUserMethylationAnalysisRequest(user);
		
		List<AnalysisRequest> analysisRequestList = new ArrayList<AnalysisRequest>();
		analysisRequestList.add(new AnalysisRequest());
		analysisRequestList.add(new AnalysisRequest());
		analysisRequestList.add(new AnalysisRequest());
		
		Mockito.doReturn(analysisRequestList).when(analysisRequestDAO).list(user);
		
		ListUserMethylationAnalysisResponse response = this.listMethylationAnalysis.execute(request);
		
		Assert.assertFalse(response.getAnalysisRequestList().isEmpty());
	}
}
