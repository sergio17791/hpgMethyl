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
import es.hpgMethyl.exceptions.CreateMehtylationResultException;
import es.hpgMethyl.exceptions.DuplicatedAnalysisResult;
import es.hpgMethyl.exceptions.HpgMethylException;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.usecases.result.CreateMethylationResult.CreateMehtylationResult;
import es.hpgMethyl.usecases.result.CreateMethylationResult.CreateMehtylationResultRequest;
import es.hpgMethyl.usecases.result.CreateMethylationResult.CreateMehtylationResultResponse;

public class CreateMehtylationResultTest {

	private CreateMehtylationResult createMehtylationResult;
	
	private AnalysisResultDAO analysisResultDAO;
	
	private AnalysisRequest analysisRequest;
	
	@Before
	public void setUp() {
		this.analysisResultDAO = Mockito.mock(AnalysisResultDAO.class);
		this.createMehtylationResult = new CreateMehtylationResult(this.analysisResultDAO);
	}
	
	@Test(expected = DuplicatedAnalysisResult.class)
	public void test_execute_givenDuplicateAnalysisResult_expectThrowDuplicatedAnalysisResult() throws HpgMethylException {	
			
		CreateMehtylationResultRequest request = new CreateMehtylationResultRequest(
				analysisRequest, 
				new HPGMethylFile()
		);
		
		Mockito.doReturn(new AnalysisResult()).when(analysisResultDAO).findByAnalysisRequest(analysisRequest);
		
		this.createMehtylationResult.execute(request);
	}
	
	@Test(expected = CreateMehtylationResultException.class)
	public void test_execute_givenConstraintViolation_expectThrowCreateMehtylationResultException() throws HpgMethylException {	
			
		CreateMehtylationResultRequest request = new CreateMehtylationResultRequest(
				analysisRequest, 
				new HPGMethylFile()
		);
		
		Mockito.doReturn(null).when(analysisResultDAO).findByAnalysisRequest(analysisRequest);
		
		Mockito.doThrow(SaveObjectException.class).when(analysisResultDAO).save(Mockito.any(AnalysisResult.class));
		
		this.createMehtylationResult.execute(request);
	}
	
	@Test
	public void test_execute_givenValidRequest_expectAnalysisResult() throws HpgMethylException {	
		
		CreateMehtylationResultRequest request = new CreateMehtylationResultRequest(
				analysisRequest, 
				new HPGMethylFile()
		);
		
		Mockito.doReturn(null).when(analysisResultDAO).findByAnalysisRequest(analysisRequest);
		
		Mockito.doNothing().when(analysisResultDAO).save(Mockito.any(AnalysisResult.class));
		
		CreateMehtylationResultResponse useCaseResponse = this.createMehtylationResult.execute(request);
		
		AnalysisResult response = useCaseResponse.getAnalysisResult();
		
		Assert.assertEquals(request.getAnalysisRequest(), response.getAnalysisRequest());
		Assert.assertEquals(request.getResultFile(), response.getResultFile());
	}
}
