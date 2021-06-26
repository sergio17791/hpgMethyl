package es.hpgMethyl.junit.usecases.analysis;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.exceptions.AnalysisRequestNotFound;
import es.hpgMethyl.exceptions.HpgMethylException;
import es.hpgMethyl.types.AnalysisStatus;
import es.hpgMethyl.usecases.analysis.GetNextPendingMethylationAnalysis.GetNextPendingMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.GetNextPendingMethylationAnalysis.GetNextPendingMethylationAnalysisResponse;

public class GetNextPendingMethylationAnalysisTest {

	private GetNextPendingMethylationAnalysis getNextPendingMethylationAnalysis;
	
	private AnalysisRequestDAO analysisRequestDAO;
	
	@Before
	public void setUp() {
		this.analysisRequestDAO = Mockito.mock(AnalysisRequestDAO.class);
		this.getNextPendingMethylationAnalysis = new GetNextPendingMethylationAnalysis(this.analysisRequestDAO);
	}
	
	@Test(expected = AnalysisRequestNotFound.class)
	public void test_execute_givenNonExistentPendingMethylationAnalysis_expectThrowAnalysisRequestNotFound() throws HpgMethylException {
		
		Mockito.doReturn(null).when(analysisRequestDAO).getNextPendingAnalysis();
		
		this.getNextPendingMethylationAnalysis.execute();
	}
	
	@Test
	public void test_execute_givenExistentPendingMethylationAnalysis_expectAnalysisRequest() throws HpgMethylException {
		
		AnalysisRequest analysisRequest = new AnalysisRequest();
		analysisRequest.setId(UUID.randomUUID());
		analysisRequest.setStatus(AnalysisStatus.CREATED);
		
		Mockito.doReturn(analysisRequest).when(analysisRequestDAO).getNextPendingAnalysis();
		
		GetNextPendingMethylationAnalysisResponse response = this.getNextPendingMethylationAnalysis.execute();
		
		Assert.assertEquals(analysisRequest.getId(), response.getAnalysisRequest().getId());
	}
}
