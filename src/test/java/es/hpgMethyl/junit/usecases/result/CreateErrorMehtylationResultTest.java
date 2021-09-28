package es.hpgMethyl.junit.usecases.result;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.AnalysisResultDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.AnalysisResult;
import es.hpgMethyl.exceptions.CreateMehtylationResultException;
import es.hpgMethyl.exceptions.DuplicatedAnalysisResult;
import es.hpgMethyl.exceptions.HpgMethylException;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.usecases.result.CreateErrorMethylationResult.CreateErrorMethylationResult;
import es.hpgMethyl.usecases.result.CreateErrorMethylationResult.CreateErrorMethylationResultRequest;
import es.hpgMethyl.usecases.result.CreateErrorMethylationResult.CreateErrorMethylationResultResponse;

public class CreateErrorMehtylationResultTest {

	private CreateErrorMethylationResult createErrorMehtylationResult;
	
	private AnalysisResultDAO analysisResultDAO;
	
	private AnalysisRequest analysisRequest;
	
	@Before
	public void setUp() {
		this.analysisResultDAO = Mockito.mock(AnalysisResultDAO.class);
		this.createErrorMehtylationResult = new CreateErrorMethylationResult(this.analysisResultDAO);
	}
	
	@Test(expected = DuplicatedAnalysisResult.class)
	public void test_execute_givenDuplicateAnalysisResult_expectThrowDuplicatedAnalysisResult() throws HpgMethylException {	
			
		CreateErrorMethylationResultRequest request = new CreateErrorMethylationResultRequest(
				analysisRequest, 
				"Some error"
		);
		
		Mockito.doReturn(new AnalysisResult()).when(analysisResultDAO).findByAnalysisRequest(analysisRequest);
		
		this.createErrorMehtylationResult.execute(request);
	}
	
	@Test(expected = CreateMehtylationResultException.class)
	public void test_execute_givenConstraintViolation_expectThrowCreateErrorMehtylationResultException() throws HpgMethylException {	
			
		CreateErrorMethylationResultRequest request = new CreateErrorMethylationResultRequest(
				analysisRequest, 
				"Some error"
		);
		
		Mockito.doReturn(null).when(analysisResultDAO).findByAnalysisRequest(analysisRequest);
		
		Mockito.doThrow(SaveObjectException.class).when(analysisResultDAO).save(Mockito.any(AnalysisResult.class));
		
		this.createErrorMehtylationResult.execute(request);
	}
	
	@Test
	public void test_execute_givenValidRequest_expectAnalysisResult() throws HpgMethylException {	
		
		CreateErrorMethylationResultRequest request = new CreateErrorMethylationResultRequest(
				analysisRequest, 
				"Some error"
		);
		
		Mockito.doReturn(null).when(analysisResultDAO).findByAnalysisRequest(analysisRequest);
		
		Mockito.doNothing().when(analysisResultDAO).save(Mockito.any(AnalysisResult.class));
		
		CreateErrorMethylationResultResponse useCaseResponse = this.createErrorMehtylationResult.execute(request);
		
		AnalysisResult response = useCaseResponse.getAnalysisResult();
		
		Assert.assertEquals(request.getAnalysisRequest(), response.getAnalysisRequest());
		Assert.assertEquals(request.getError(), response.getError());
	}
}
