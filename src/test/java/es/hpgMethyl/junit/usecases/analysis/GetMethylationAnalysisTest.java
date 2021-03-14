package es.hpgMethyl.junit.usecases.analysis;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.exceptions.AnalysisRequestNotFound;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.HpgMethylException;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysisRequest;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysisResponse;

public class GetMethylationAnalysisTest {

	private GetMethylationAnalysis getMethylationAnalysis;
	
	private AnalysisRequestDAO analysisRequestDAO;
	
	@Before
	public void setUp() {
		this.analysisRequestDAO = Mockito.mock(AnalysisRequestDAO.class);
		this.getMethylationAnalysis = new GetMethylationAnalysis(this.analysisRequestDAO);
	}
	
	@Test(expected = HpgMethylException.class)
	public void test_execute_givenAnErrorWhenGetAnalysisRequestObject_expectThrowHpgMethylException() throws HpgMethylException {
		
		UUID id = UUID.randomUUID();
		
		Mockito.doThrow(GetObjectException.class).when(analysisRequestDAO).get(id);
		
		this.getMethylationAnalysis.execute(new GetMethylationAnalysisRequest(id));
	}
	
	@Test(expected = AnalysisRequestNotFound.class)
	public void test_execute_givenNonExistentMethylationAnalysis_expectThrowAnalysisRequestNotFound() throws HpgMethylException {
		
		UUID id = UUID.randomUUID();
		
		Mockito.doReturn(null).when(analysisRequestDAO).get(id);
		
		this.getMethylationAnalysis.execute(new GetMethylationAnalysisRequest(id));
	}
	
	@Test
	public void test_execute_givenExistentID_expectAnalysisRequest() throws HpgMethylException {
		
		UUID id = UUID.randomUUID();
		
		AnalysisRequest analysisRequest = new AnalysisRequest();
		analysisRequest.setId(id);
		
		Mockito.doReturn(analysisRequest).when(analysisRequestDAO).get(id);
		
		GetMethylationAnalysisResponse response = this.getMethylationAnalysis.execute(new GetMethylationAnalysisRequest(id));
		
		Assert.assertEquals(id, response.getAnalysisRequest().getId());
	}
}
