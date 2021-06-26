package es.hpgMethyl.junit.usecases.analysis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.AnalysisRequestNotFound;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.HpgMethylException;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.exceptions.UpdateMethylationAnalysisException;
import es.hpgMethyl.types.AnalysisStatus;
import es.hpgMethyl.types.PairedMode;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisStatus.UpdateMethylationAnalysisStatus;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisStatus.UpdateMethylationAnalysisStatusRequest;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisStatus.UpdateMethylationAnalysisStatusResponse;

public class UpdateMethylationAnalysisStatusTest {

	private UpdateMethylationAnalysisStatus updateMethylationAnalysisStatus;
	
	private AnalysisRequestDAO analysisRequestDAO;
	
	private AnalysisRequest analysisRequest;
	
	@Before
	public void setUp() {
		this.analysisRequestDAO = Mockito.mock(AnalysisRequestDAO.class);
		this.updateMethylationAnalysisStatus = new UpdateMethylationAnalysisStatus(this.analysisRequestDAO);
		this.analysisRequest = new AnalysisRequest(
				UUID.randomUUID(),
				new Date(), 
				new Date(),
				new User(),
				"Update Methylation Analysis Status Test", 
				AnalysisStatus.CREATED,
				new HPGMethylFile(), 
				false, 
				PairedMode.SINGLE_END_MODE, 
				null,
				null, 
				null, 
				new BigDecimal("0.8"),
				new BigDecimal("5.0"),
				new BigDecimal("-4.0"),
				new BigDecimal("10.0"),
				new BigDecimal("0.5"),
				new BigDecimal("5"),
				new BigDecimal("30"),
				new BigDecimal("4"),
				new BigDecimal("100"),
				new BigDecimal("23"),
				new BigDecimal("16"),
				new BigDecimal("10"),
				new BigDecimal("100"),
				new BigDecimal("-1"),
				null, 
				null,
				null, 
				false, 
				true, 
				null,
				null,
				1
		);
	}
	
	@Test(expected = UpdateMethylationAnalysisException.class)
	public void test_execute_givenAnErrorWhenGetAnalysisRequestObject_expectThrowUpdateMethylationAnalysisException() throws HpgMethylException {
		
		UUID id = analysisRequest.getId();
		
		UpdateMethylationAnalysisStatusRequest request = new UpdateMethylationAnalysisStatusRequest(
				id,
				AnalysisStatus.PROCESSING
		);
		
		Mockito.doThrow(GetObjectException.class).when(analysisRequestDAO).get(id);
		
		this.updateMethylationAnalysisStatus.execute(request);
	}
	
	@Test(expected = AnalysisRequestNotFound.class)
	public void test_execute_givenAnUnexistingAnalysisID_expectThrowAnalysisRequestNotFound() throws HpgMethylException {

		UUID id = UUID.randomUUID();
		
		UpdateMethylationAnalysisStatusRequest request = new UpdateMethylationAnalysisStatusRequest(
				id,
				AnalysisStatus.PROCESSING
		);
		
		Mockito.doReturn(null).when(analysisRequestDAO).get(id);
		
		this.updateMethylationAnalysisStatus.execute(request);
	}
	
	
	@Test(expected = UpdateMethylationAnalysisException.class)
	public void test_execute_givenAnErrorWhenUpdateAnalysisRequestObject_expectThrowUpdateMethylationAnalysisException() throws HpgMethylException {
		
		UUID id = analysisRequest.getId();
		
		UpdateMethylationAnalysisStatusRequest request = new UpdateMethylationAnalysisStatusRequest(
				id,
				AnalysisStatus.PROCESSING
		);
		
		Mockito.doReturn(analysisRequest).when(analysisRequestDAO).get(id);		
		
		Mockito.doThrow(SaveObjectException.class).when(analysisRequestDAO).save(Mockito.any(AnalysisRequest.class));
		
		this.updateMethylationAnalysisStatus.execute(request);
	}
	
	@Test
	public void test_execute_givenValidRequest_expectAnalysisRequest() throws HpgMethylException {
		
		UUID id = analysisRequest.getId();
		
		UpdateMethylationAnalysisStatusRequest request = new UpdateMethylationAnalysisStatusRequest(
				id,
				AnalysisStatus.PROCESSING
		);
		
		Mockito.doReturn(analysisRequest).when(analysisRequestDAO).get(id);		
		
		Mockito.doNothing().when(analysisRequestDAO).save(Mockito.any(AnalysisRequest.class));
		
		UpdateMethylationAnalysisStatusResponse useCaseResponse = this.updateMethylationAnalysisStatus.execute(request);
		
		AnalysisRequest response = useCaseResponse.getAnalysisRequest();
		
		Assert.assertEquals(request.getStatus(), response.getStatus());
	}
}
