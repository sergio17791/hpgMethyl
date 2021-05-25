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
import es.hpgMethyl.exceptions.AnalysisRequestProcessed;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.HpgMethylException;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.exceptions.UpdateMethylationAnalysisException;
import es.hpgMethyl.types.AnalysisStatus;
import es.hpgMethyl.types.PairedMode;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisPairedFile.UpdateMethylationAnalysisPairedFile;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisPairedFile.UpdateMethylationAnalysisPairedFileRequest;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisPairedFile.UpdateMethylationAnalysisPairedFileResponse;

public class UpdateMethylationAnalysisPairedFileTest {

	private UpdateMethylationAnalysisPairedFile updateMethylationAnalysisPairedFile;
	
	private AnalysisRequestDAO analysisRequestDAO;
	
	private AnalysisRequest analysisRequest;
	
	@Before
	public void setUp() {
		this.analysisRequestDAO = Mockito.mock(AnalysisRequestDAO.class);
		this.updateMethylationAnalysisPairedFile = new UpdateMethylationAnalysisPairedFile(this.analysisRequestDAO);
		this.analysisRequest = new AnalysisRequest(
				UUID.randomUUID(),
				new Date(), 
				new Date(),
				new User(),
				"Update Methylation Analysis Paired File Test", 
				AnalysisStatus.CREATED,
				new HPGMethylFile(), 
				false,
				false, 
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
		
		UpdateMethylationAnalysisPairedFileRequest request = new UpdateMethylationAnalysisPairedFileRequest(
				id,
				new HPGMethylFile()
		);
		
		Mockito.doThrow(GetObjectException.class).when(analysisRequestDAO).get(id);
		
		this.updateMethylationAnalysisPairedFile.execute(request);
	}
	
	@Test(expected = AnalysisRequestNotFound.class)
	public void test_execute_givenAnUnexistingAnalysisID_expectThrowAnalysisRequestNotFound() throws HpgMethylException {

		UUID id = UUID.randomUUID();
		
		UpdateMethylationAnalysisPairedFileRequest request = new UpdateMethylationAnalysisPairedFileRequest(
				id,
				new HPGMethylFile()
		);
		
		Mockito.doReturn(null).when(analysisRequestDAO).get(id);
		
		this.updateMethylationAnalysisPairedFile.execute(request);
	}
	
	@Test(expected = AnalysisRequestProcessed.class)
	public void test_execute_givenProcessedAnalysis_expectThrowAnalysisRequestProcessed() throws HpgMethylException {
		
		UUID id = UUID.randomUUID();
		
		AnalysisRequest processedAnalysisRequest = new AnalysisRequest(
				id,
				new Date(), 
				new Date(),
				new User(),
				"Processed Analysis Request Test", 
				AnalysisStatus.COMPLETED,
				new HPGMethylFile(), 
				false,
				false, 
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
		
		UpdateMethylationAnalysisPairedFileRequest request = new UpdateMethylationAnalysisPairedFileRequest(
				id,
				new HPGMethylFile()
		);
		
		Mockito.doReturn(processedAnalysisRequest).when(analysisRequestDAO).get(id);
		
		this.updateMethylationAnalysisPairedFile.execute(request);
	}
	
	@Test(expected = UpdateMethylationAnalysisException.class)
	public void test_execute_givenAnErrorWhenUpdateAnalysisRequestObject_expectThrowUpdateMethylationAnalysisException() throws HpgMethylException {
		
		UUID id = analysisRequest.getId();
		
		UpdateMethylationAnalysisPairedFileRequest request = new UpdateMethylationAnalysisPairedFileRequest(
				id,
				new HPGMethylFile()
		);
		
		Mockito.doReturn(analysisRequest).when(analysisRequestDAO).get(id);		
		
		Mockito.doThrow(SaveObjectException.class).when(analysisRequestDAO).save(Mockito.any(AnalysisRequest.class));
		
		this.updateMethylationAnalysisPairedFile.execute(request);
	}
	
	@Test
	public void test_execute_givenValidRequest_expectAnalysisRequest() throws HpgMethylException {
		
		UUID id = analysisRequest.getId();
		
		HPGMethylFile newFile = new HPGMethylFile(
				UUID.randomUUID(),
				new Date(), 
				new Date(),
				new User(),
				"test_4M_100nt_n3_r010.bwa.read1.fastq_convert.fastq",
				"/files/users/03711b01-5812-4a00-a039-e8eee346fb6b/test_4M_100nt_n3_r010.bwa.read1.fastq_convert.fastq",
				Long.valueOf(123),
				"text/plain",
				true
		);
		
		UpdateMethylationAnalysisPairedFileRequest request = new UpdateMethylationAnalysisPairedFileRequest(id, newFile);
		
		Mockito.doReturn(analysisRequest).when(analysisRequestDAO).get(id);			
		
		Mockito.doNothing().when(analysisRequestDAO).save(Mockito.any(AnalysisRequest.class));
		
		UpdateMethylationAnalysisPairedFileResponse useCaseResponse = this.updateMethylationAnalysisPairedFile.execute(request);
		
		AnalysisRequest response = useCaseResponse.getAnalysisRequest();
		
		Assert.assertEquals(request.getPairedFile().getFileName(), response.getPairedEndModeFile().getFileName());
	}
}
