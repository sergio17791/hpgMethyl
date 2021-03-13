package es.hpgMethyl.junit.usecases.analysis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.hibernate.AnalysisRequestDAOHibernate;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.AnalysisRequestNotFound;
import es.hpgMethyl.exceptions.CreateMethylationAnalysisException;
import es.hpgMethyl.exceptions.DuplicatedIdentifier;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.types.PairedMode;
import es.hpgMethyl.usecases.analysis.CreateMethylationAnalysis.CreateMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.CreateMethylationAnalysis.CreateMethylationAnalysisRequest;
import es.hpgMethyl.usecases.analysis.CreateMethylationAnalysis.CreateMethylationAnalysisResponse;

public class CreateMethylationAnalysisTest {

	private CreateMethylationAnalysis createMethylationAnalysis;
	
	private AnalysisRequestDAOHibernate analysisRequestDAOHibernate;
	
	private User user;
	
	@Before
	public void setUp() {
		this.analysisRequestDAOHibernate = Mockito.mock(AnalysisRequestDAOHibernate.class);
		this.createMethylationAnalysis = new CreateMethylationAnalysis(this.analysisRequestDAOHibernate);
		
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
				true
			);
	}
	
	@Test(expected = DuplicatedIdentifier.class)
	public void test_execute_givenDuplicateIdentifier_expectThrowDuplicatedIdentifier() throws AnalysisRequestNotFound, CreateMethylationAnalysisException, DuplicatedIdentifier {	
		
		String identifier = "DuplicatedIdentifier";
		
		CreateMethylationAnalysisRequest request = new CreateMethylationAnalysisRequest(
				this.user, 
				identifier,
				"inputReadFile",
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
				null
		);
		
		Mockito.doReturn(new AnalysisRequest()).when(analysisRequestDAOHibernate).findByIdentifier(user, identifier);
		
		this.createMethylationAnalysis.execute(request);
	}
	
	@Test
	public void test_execute_givenValidRequest_expectAnalysisRequest() throws AnalysisRequestNotFound, CreateMethylationAnalysisException, DuplicatedIdentifier, SaveObjectException {	
		
		String identifier = "GreatIdentifier";
		
		CreateMethylationAnalysisRequest request = new CreateMethylationAnalysisRequest(
				this.user, 
				identifier,
				"inputReadFile",
				false, 
				true, 
				false, 
				PairedMode.PAIRED_END_MODE,
				"pairedEndModeFile", 
				new BigDecimal("1"), 
				new BigDecimal("2"),
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
				new BigDecimal("30"),
				new BigDecimal("31"),
				new BigDecimal("32"),
				false, 
				true, 
				new BigDecimal("33"),
				new BigDecimal("34")
		);
		
		Mockito.doThrow(AnalysisRequestNotFound.class).when(analysisRequestDAOHibernate).findByIdentifier(user, identifier);
		
		Mockito.doNothing().when(analysisRequestDAOHibernate).save(Mockito.any(AnalysisRequest.class));
		
		CreateMethylationAnalysisResponse useCaseResponse = this.createMethylationAnalysis.execute(request);
		
		AnalysisRequest response = useCaseResponse.getAnalysisRequest();
		
		Assert.assertEquals(request.getIdentifier(), response.getIdentifier());
		Assert.assertEquals(request.getInputReadFile(), response.getInputReadFile());
		Assert.assertEquals(request.getWriteMethylationContext(), response.getWriteMethylationContext());
		Assert.assertEquals(request.getReadBatchSize(), response.getReadBatchSize());
		Assert.assertEquals(request.getWriteBatchSize(), response.getWriteBatchSize());
		Assert.assertEquals(request.getPairedMode(), response.getPairedMode());
		Assert.assertEquals(request.getPairedEndModeFile(), response.getPairedEndModeFile());
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
