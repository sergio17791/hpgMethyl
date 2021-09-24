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
				4269656
		);
		
		Mockito.doReturn(new AnalysisResult()).when(analysisResultDAO).findByAnalysisRequest(analysisRequest);
		
		this.createMehtylationResult.execute(request);
	}
	
	@Test(expected = CreateMehtylationResultException.class)
	public void test_execute_givenConstraintViolation_expectThrowCreateMehtylationResultException() throws HpgMethylException {	
			
		CreateMehtylationResultRequest request = new CreateMehtylationResultRequest(
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
				4269656
		);
		
		Mockito.doReturn(null).when(analysisResultDAO).findByAnalysisRequest(analysisRequest);
		
		Mockito.doThrow(SaveObjectException.class).when(analysisResultDAO).save(Mockito.any(AnalysisResult.class));
		
		this.createMehtylationResult.execute(request);
	}
	
	@Test
	public void test_execute_givenValidRequest_expectAnalysisResult() throws HpgMethylException {	
		
		CreateMehtylationResultRequest request = new CreateMehtylationResultRequest(
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
				4269656
		);
		
		Mockito.doReturn(null).when(analysisResultDAO).findByAnalysisRequest(analysisRequest);
		
		Mockito.doNothing().when(analysisResultDAO).save(Mockito.any(AnalysisResult.class));
		
		CreateMehtylationResultResponse useCaseResponse = this.createMehtylationResult.execute(request);
		
		AnalysisResult response = useCaseResponse.getAnalysisResult();
		
		Assert.assertEquals(request.getAnalysisRequest(), response.getAnalysisRequest());
		Assert.assertEquals(request.getResultFile(), response.getResultFile());
		Assert.assertEquals(request.getTotalNumberCAnalysed(), response.getTotalNumberCAnalysed()); 
		Assert.assertEquals(request.getTotalMethylatedCCPGContext(), response.getTotalMethylatedCCPGContext()); 
		Assert.assertEquals(request.getTotalMethylatedCCHGContext(), response.getTotalMethylatedCCHGContext()); 
		Assert.assertEquals(request.getTotalMethylatedCCHHContext(), response.getTotalMethylatedCCHHContext()); 
		Assert.assertEquals(request.getTotalCToTConversionsCPGContext(), response.getTotalCToTConversionsCPGContext()); 
		Assert.assertEquals(request.getTotalCToTConversionsCHGContext(), response.getTotalCToTConversionsCHGContext()); 
		Assert.assertEquals(request.getTotalCToTConversionsCHHContex(), response.getTotalCToTConversionsCHHContex()); 
		Assert.assertEquals(request.getcMethylatedCPGContext(), response.getcMethylatedCPGContext()); 
		Assert.assertEquals(request.getcMethylatedCHGContext(), response.getcMethylatedCHGContext()); 
		Assert.assertEquals(request.getcMethylatedCHHContext(), response.getcMethylatedCHHContext()); 
		Assert.assertEquals(request.getLoadingTime(), response.getLoadingTime()); 
		Assert.assertEquals(request.getAligmentTime(), response.getAligmentTime()); 
		Assert.assertEquals(request.getTotalTime(), response.getTotalTime()); 
		Assert.assertEquals(request.getTotalReadsProcessed(), response.getTotalReadsProcessed()); 
		Assert.assertEquals(request.getReadsMapped(), response.getReadsMapped()); 
		Assert.assertEquals(request.getTotalReadsMapped(), response.getTotalReadsMapped()); 
		Assert.assertEquals(request.getReadsUnmapped(), response.getReadsUnmapped()); 
		Assert.assertEquals(request.getTotalReadsUnmapped(), response.getTotalReadsUnmapped());
	}
}
