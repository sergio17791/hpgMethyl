package es.hpgMethyl.junit.usecases.result;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.AnalysisResultDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.AnalysisResult;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.exceptions.AnalysisResultNotFound;
import es.hpgMethyl.exceptions.HpgMethylException;
import es.hpgMethyl.usecases.result.GetMethylationAnalysisResult.GetMethylationAnalysisResult;
import es.hpgMethyl.usecases.result.GetMethylationAnalysisResult.GetMethylationAnalysisResultRequest;
import es.hpgMethyl.usecases.result.GetMethylationAnalysisResult.GetMethylationAnalysisResultResponse;

public class GetMethylationAnalysisResultTest {

	private GetMethylationAnalysisResult getMethylationAnalysisResult;
	
	private AnalysisResultDAO analysisResultDAO;
	
	@Before
	public void setUp() {
		this.analysisResultDAO = Mockito.mock(AnalysisResultDAO.class);
		this.getMethylationAnalysisResult = new GetMethylationAnalysisResult(this.analysisResultDAO);
	}
	
	@Test(expected = AnalysisResultNotFound.class)
	public void test_execute_givenNonExistentMethylationAnalysis_expectThrowAnalysisRequestNotFound() throws HpgMethylException {
		
		AnalysisRequest analysisRequest = new AnalysisRequest();
		
		Mockito.doReturn(null).when(analysisResultDAO).findByAnalysisRequest(analysisRequest);
		
		this.getMethylationAnalysisResult.execute(new GetMethylationAnalysisResultRequest(analysisRequest));
	}
	
	@Test
	public void test_execute_givenExistentID_expectAnalysisRequest() throws HpgMethylException {
		
		AnalysisRequest analysisRequest = new AnalysisRequest();
		
		AnalysisResult analysisResult = new AnalysisResult(
				UUID.randomUUID(),
				new Date(),
				new Date(),
				analysisRequest,
				new HPGMethylFile(),
				1280229870,
				31080182,
				3999808,
				26753886,
				62521969,
				162371568,
				608524098,
				new BigDecimal("33.20"),
				new BigDecimal("2.40"),
				new BigDecimal("4.21"),
				new BigDecimal("0"),
				new BigDecimal("3024.49"),
				new BigDecimal("3024.49"),
				31391744,
				new BigDecimal("86.40"),
				27122088,
				new BigDecimal("13.60"),
				4269656,
				null
		);
		
		Mockito.doReturn(analysisResult).when(analysisResultDAO).findByAnalysisRequest(analysisRequest);
		
		GetMethylationAnalysisResultResponse response = this.getMethylationAnalysisResult.execute(new GetMethylationAnalysisResultRequest(analysisRequest));
		
		Assert.assertEquals(analysisRequest, response.getAnalysisResult().getAnalysisRequest());
	}
}
