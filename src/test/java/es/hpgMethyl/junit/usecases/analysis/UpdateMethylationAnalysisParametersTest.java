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
import es.hpgMethyl.exceptions.DuplicatedIdentifier;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.HpgMethylException;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.exceptions.UpdateMethylationAnalysisException;
import es.hpgMethyl.types.AnalysisStatus;
import es.hpgMethyl.types.PairedMode;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisParameters.UpdateMethylationAnalysisParameters;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisParameters.UpdateMethylationAnalysisParametersRequest;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisParameters.UpdateMethylationAnalysisParametersResponse;

public class UpdateMethylationAnalysisParametersTest {

	private UpdateMethylationAnalysisParameters updateMethylationAnalysisParameters;
	
	private AnalysisRequestDAO analysisRequestDAO;
	
	private AnalysisRequest analysisRequest;
	
	@Before
	public void setUp() {
		this.analysisRequestDAO = Mockito.mock(AnalysisRequestDAO.class);
		this.updateMethylationAnalysisParameters = new UpdateMethylationAnalysisParameters(this.analysisRequestDAO);
		this.analysisRequest = new AnalysisRequest(
				UUID.randomUUID(),
				new Date(), 
				new Date(),
				new User(),
				"Update Methylation Analysis Parameters Test", 
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
		
		UpdateMethylationAnalysisParametersRequest request = new UpdateMethylationAnalysisParametersRequest(
				id,
				analysisRequest.getIdentifier(),
				true,
				analysisRequest.getPairedMode(),
				analysisRequest.getPairedMaxDistance(),
				analysisRequest.getPairedMinDistance(),
				analysisRequest.getSwaMinimunScore(),
				analysisRequest.getSwaMatchScore(),
				analysisRequest.getSwaMismatchScore(),
				analysisRequest.getSwaGapOpen(),
				analysisRequest.getSwaGapExtend(),
				analysisRequest.getCalFlankSize(),
				analysisRequest.getMinimumCalSize(),
				analysisRequest.getCalUmbralLengthFactor(),
				analysisRequest.getMaximumBetweenSeeds(),
				analysisRequest.getMaximumSeedSize(),
				analysisRequest.getMinimumSeedSize(),
				analysisRequest.getNumberSeedsPerRead(),
				analysisRequest.getReadMinimumDiscardLength(),
				analysisRequest.getReadMaximumInnerGap(),
				analysisRequest.getMinimumNumberSeeds(),
				analysisRequest.getFilterReadMappings(),
				analysisRequest.getFilterSeedMappings(),
				analysisRequest.getReportAll(),
				analysisRequest.getReportBest(),
				analysisRequest.getReportNBest(),
				analysisRequest.getReportNHits()
		);
		
		Mockito.doThrow(GetObjectException.class).when(analysisRequestDAO).get(id);
		
		this.updateMethylationAnalysisParameters.execute(request);
	}
	
	@Test(expected = AnalysisRequestNotFound.class)
	public void test_execute_givenAnUnexistingAnalysisID_expectThrowAnalysisRequestNotFound() throws HpgMethylException {

		UUID id = UUID.randomUUID();
		
		UpdateMethylationAnalysisParametersRequest request = new UpdateMethylationAnalysisParametersRequest(
				id,
				analysisRequest.getIdentifier(),
				true,
				analysisRequest.getPairedMode(),
				analysisRequest.getPairedMaxDistance(),
				analysisRequest.getPairedMinDistance(),
				analysisRequest.getSwaMinimunScore(),
				analysisRequest.getSwaMatchScore(),
				analysisRequest.getSwaMismatchScore(),
				analysisRequest.getSwaGapOpen(),
				analysisRequest.getSwaGapExtend(),
				analysisRequest.getCalFlankSize(),
				analysisRequest.getMinimumCalSize(),
				analysisRequest.getCalUmbralLengthFactor(),
				analysisRequest.getMaximumBetweenSeeds(),
				analysisRequest.getMaximumSeedSize(),
				analysisRequest.getMinimumSeedSize(),
				analysisRequest.getNumberSeedsPerRead(),
				analysisRequest.getReadMinimumDiscardLength(),
				analysisRequest.getReadMaximumInnerGap(),
				analysisRequest.getMinimumNumberSeeds(),
				analysisRequest.getFilterReadMappings(),
				analysisRequest.getFilterSeedMappings(),
				analysisRequest.getReportAll(),
				analysisRequest.getReportBest(),
				analysisRequest.getReportNBest(),
				analysisRequest.getReportNHits()
		);
		
		Mockito.doReturn(null).when(analysisRequestDAO).get(id);
		
		this.updateMethylationAnalysisParameters.execute(request);
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
		
		UpdateMethylationAnalysisParametersRequest request = new UpdateMethylationAnalysisParametersRequest(
				id,
				processedAnalysisRequest.getIdentifier(),
				true,
				processedAnalysisRequest.getPairedMode(),
				processedAnalysisRequest.getPairedMaxDistance(),
				processedAnalysisRequest.getPairedMinDistance(),
				processedAnalysisRequest.getSwaMinimunScore(),
				processedAnalysisRequest.getSwaMatchScore(),
				processedAnalysisRequest.getSwaMismatchScore(),
				processedAnalysisRequest.getSwaGapOpen(),
				processedAnalysisRequest.getSwaGapExtend(),
				processedAnalysisRequest.getCalFlankSize(),
				processedAnalysisRequest.getMinimumCalSize(),
				processedAnalysisRequest.getCalUmbralLengthFactor(),
				processedAnalysisRequest.getMaximumBetweenSeeds(),
				processedAnalysisRequest.getMaximumSeedSize(),
				processedAnalysisRequest.getMinimumSeedSize(),
				processedAnalysisRequest.getNumberSeedsPerRead(),
				processedAnalysisRequest.getReadMinimumDiscardLength(),
				processedAnalysisRequest.getReadMaximumInnerGap(),
				processedAnalysisRequest.getMinimumNumberSeeds(),
				processedAnalysisRequest.getFilterReadMappings(),
				processedAnalysisRequest.getFilterSeedMappings(),
				processedAnalysisRequest.getReportAll(),
				processedAnalysisRequest.getReportBest(),
				processedAnalysisRequest.getReportNBest(),
				processedAnalysisRequest.getReportNHits()
		);
		
		Mockito.doReturn(processedAnalysisRequest).when(analysisRequestDAO).get(id);
		
		this.updateMethylationAnalysisParameters.execute(request);
	}
	
	@Test(expected = DuplicatedIdentifier.class)
	public void test_execute_givenDuplicateIdentifier_expectThrowDuplicatedIdentifier() throws HpgMethylException {
		
		UUID id = analysisRequest.getId();
		String identifier = "Duplicated identifier";
		
		UpdateMethylationAnalysisParametersRequest request = new UpdateMethylationAnalysisParametersRequest(
				id,
				identifier,
				true,
				analysisRequest.getPairedMode(),
				analysisRequest.getPairedMaxDistance(),
				analysisRequest.getPairedMinDistance(),
				analysisRequest.getSwaMinimunScore(),
				analysisRequest.getSwaMatchScore(),
				analysisRequest.getSwaMismatchScore(),
				analysisRequest.getSwaGapOpen(),
				analysisRequest.getSwaGapExtend(),
				analysisRequest.getCalFlankSize(),
				analysisRequest.getMinimumCalSize(),
				analysisRequest.getCalUmbralLengthFactor(),
				analysisRequest.getMaximumBetweenSeeds(),
				analysisRequest.getMaximumSeedSize(),
				analysisRequest.getMinimumSeedSize(),
				analysisRequest.getNumberSeedsPerRead(),
				analysisRequest.getReadMinimumDiscardLength(),
				analysisRequest.getReadMaximumInnerGap(),
				analysisRequest.getMinimumNumberSeeds(),
				analysisRequest.getFilterReadMappings(),
				analysisRequest.getFilterSeedMappings(),
				analysisRequest.getReportAll(),
				analysisRequest.getReportBest(),
				analysisRequest.getReportNBest(),
				analysisRequest.getReportNHits()
		);
		
		Mockito.doReturn(analysisRequest).when(analysisRequestDAO).get(id);
		
		Mockito.doReturn(new AnalysisRequest()).when(analysisRequestDAO).findByIdentifier(analysisRequest.getUser(), identifier);
		
		this.updateMethylationAnalysisParameters.execute(request);
	}
	
	@Test(expected = UpdateMethylationAnalysisException.class)
	public void test_execute_givenAnErrorWhenUpdateAnalysisRequestObject_expectThrowUpdateMethylationAnalysisException() throws HpgMethylException {
		
		UUID id = analysisRequest.getId();
		String identifier = analysisRequest.getIdentifier();
		
		UpdateMethylationAnalysisParametersRequest request = new UpdateMethylationAnalysisParametersRequest(
				id,
				identifier,
				true,
				analysisRequest.getPairedMode(),
				analysisRequest.getPairedMaxDistance(),
				analysisRequest.getPairedMinDistance(),
				analysisRequest.getSwaMinimunScore(),
				analysisRequest.getSwaMatchScore(),
				analysisRequest.getSwaMismatchScore(),
				analysisRequest.getSwaGapOpen(),
				analysisRequest.getSwaGapExtend(),
				analysisRequest.getCalFlankSize(),
				analysisRequest.getMinimumCalSize(),
				analysisRequest.getCalUmbralLengthFactor(),
				analysisRequest.getMaximumBetweenSeeds(),
				analysisRequest.getMaximumSeedSize(),
				analysisRequest.getMinimumSeedSize(),
				analysisRequest.getNumberSeedsPerRead(),
				analysisRequest.getReadMinimumDiscardLength(),
				analysisRequest.getReadMaximumInnerGap(),
				analysisRequest.getMinimumNumberSeeds(),
				analysisRequest.getFilterReadMappings(),
				analysisRequest.getFilterSeedMappings(),
				analysisRequest.getReportAll(),
				analysisRequest.getReportBest(),
				analysisRequest.getReportNBest(),
				analysisRequest.getReportNHits()
		);
		
		Mockito.doReturn(analysisRequest).when(analysisRequestDAO).get(id);
		
		Mockito.doReturn(null).when(analysisRequestDAO).findByIdentifier(analysisRequest.getUser(), identifier);			
		
		Mockito.doThrow(SaveObjectException.class).when(analysisRequestDAO).save(Mockito.any(AnalysisRequest.class));
		
		this.updateMethylationAnalysisParameters.execute(request);
	}
	
	@Test
	public void test_execute_givenValidRequest_expectAnalysisRequest() throws HpgMethylException {
		
		UUID id = analysisRequest.getId();
		String identifier = analysisRequest.getIdentifier();
		
		UpdateMethylationAnalysisParametersRequest request = new UpdateMethylationAnalysisParametersRequest(
				id,
				identifier,
				true,
				analysisRequest.getPairedMode(),
				analysisRequest.getPairedMaxDistance(),
				analysisRequest.getPairedMinDistance(),
				analysisRequest.getSwaMinimunScore(),
				analysisRequest.getSwaMatchScore(),
				analysisRequest.getSwaMismatchScore(),
				analysisRequest.getSwaGapOpen(),
				analysisRequest.getSwaGapExtend(),
				analysisRequest.getCalFlankSize(),
				analysisRequest.getMinimumCalSize(),
				analysisRequest.getCalUmbralLengthFactor(),
				analysisRequest.getMaximumBetweenSeeds(),
				analysisRequest.getMaximumSeedSize(),
				analysisRequest.getMinimumSeedSize(),
				analysisRequest.getNumberSeedsPerRead(),
				analysisRequest.getReadMinimumDiscardLength(),
				analysisRequest.getReadMaximumInnerGap(),
				analysisRequest.getMinimumNumberSeeds(),
				analysisRequest.getFilterReadMappings(),
				analysisRequest.getFilterSeedMappings(),
				analysisRequest.getReportAll(),
				analysisRequest.getReportBest(),
				analysisRequest.getReportNBest(),
				analysisRequest.getReportNHits()
		);
		
		Mockito.doReturn(analysisRequest).when(analysisRequestDAO).get(id);
		
		Mockito.doReturn(null).when(analysisRequestDAO).findByIdentifier(analysisRequest.getUser(), identifier);			
		
		Mockito.doNothing().when(analysisRequestDAO).save(Mockito.any(AnalysisRequest.class));
		
		UpdateMethylationAnalysisParametersResponse useCaseResponse = this.updateMethylationAnalysisParameters.execute(request);
		
		AnalysisRequest response = useCaseResponse.getAnalysisRequest();
		
		Assert.assertEquals(request.getIdentifier(), response.getIdentifier());
		Assert.assertEquals(request.getWriteMethylationContext(), response.getWriteMethylationContext());
		Assert.assertEquals(request.getPairedMode(), response.getPairedMode());
		Assert.assertEquals(request.getPairedMaxDistance(), response.getPairedMaxDistance());
		Assert.assertEquals(request.getPairedMinDistance(), response.getPairedMinDistance());
		Assert.assertEquals(request.getSwaMinimunScore(), response.getSwaMinimunScore());
		Assert.assertEquals(request.getSwaMatchScore(), response.getSwaMatchScore());
		Assert.assertEquals(request.getSwaMismatchScore(), response.getSwaMismatchScore());
		Assert.assertEquals(request.getSwaGapOpen(), response.getSwaGapOpen());
		Assert.assertEquals(request.getSwaGapExtend(), response.getSwaGapExtend());
		Assert.assertEquals(request.getCalFlankSize(), response.getCalFlankSize());
		Assert.assertEquals(request.getMinimumCalSize(), response.getMinimumCalSize());
		Assert.assertEquals(request.getCalUmbralLengthFactor(), response.getCalUmbralLengthFactor());
		Assert.assertEquals(request.getMaximumBetweenSeeds(), response.getMaximumBetweenSeeds());
		Assert.assertEquals(request.getMaximumSeedSize(), response.getMaximumSeedSize());
		Assert.assertEquals(request.getMinimumSeedSize(), response.getMinimumSeedSize());
		Assert.assertEquals(request.getNumberSeedsPerRead(), response.getNumberSeedsPerRead());
		Assert.assertEquals(request.getReadMinimumDiscardLength(), response.getReadMinimumDiscardLength());
		Assert.assertEquals(request.getReadMaximumInnerGap(), response.getReadMaximumInnerGap());
		Assert.assertEquals(request.getMinimumNumberSeeds(), response.getMinimumNumberSeeds());
		Assert.assertEquals(request.getFilterReadMappings(), response.getFilterReadMappings());
		Assert.assertEquals(request.getReportAll(), response.getReportAll());
		Assert.assertEquals(request.getReportBest(), response.getReportBest());
		Assert.assertEquals(request.getReportNBest(), response.getReportNBest());
		Assert.assertEquals(request.getReportNHits(), response.getReportNHits());
	}
}
