package es.hpgMethyl.usecases.analysis.CreateMethylationAnalysis;

import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.CreateMethylationAnalysisException;
import es.hpgMethyl.exceptions.DuplicatedIdentifier;
import es.hpgMethyl.exceptions.SaveObjectException;

public class CreateMethylationAnalysis {
	
	private AnalysisRequestDAO analysisRequestDAO;

	public CreateMethylationAnalysis(AnalysisRequestDAO analysisRequestDAO) {
		this.analysisRequestDAO = analysisRequestDAO;
	}
	
	public CreateMethylationAnalysisResponse execute(CreateMethylationAnalysisRequest request) throws CreateMethylationAnalysisException, DuplicatedIdentifier {
		
		User user = request.getUser();
		String identifier = request.getIdentifier();
		
		if(checkDuplicatedUserIdentifier(user, identifier)) {
			throw new DuplicatedIdentifier();
		}
		
		AnalysisRequest analysisRequest = new AnalysisRequest();
		analysisRequest.setUser(user);
		analysisRequest.setIdentifier(identifier);
		analysisRequest.setInputReadFile(request.getInputReadFile());
		analysisRequest.setWriteMethylationContext(request.getWriteMethylationContext());
		analysisRequest.setPairedMode(request.getPairedMode());
		analysisRequest.setPairedEndModeFile(request.getPairedEndModeFile());
		analysisRequest.setPairedMaxDistance(request.getPairedMaxDistance());
		analysisRequest.setPairedMinDistance(request.getPairedMinDistance());
		analysisRequest.setSwaMinimunScore(request.getSwaMinimunScore());
		analysisRequest.setSwaMatchScore(request.getSwaMatchScore());
		analysisRequest.setSwaMismatchScore(request.getSwaMismatchScore());
		analysisRequest.setSwaGapOpen(request.getSwaGapOpen());
		analysisRequest.setSwaGapExtend(request.getSwaGapExtend());
		analysisRequest.setCalFlankSize(request.getCalFlankSize());
		analysisRequest.setMinimumCalSize(request.getMinimumCalSize());
		analysisRequest.setCalUmbralLengthFactor(request.getCalUmbralLengthFactor());
		analysisRequest.setMaximumBetweenSeeds(request.getMaximumBetweenSeeds());
		analysisRequest.setMaximumSeedSize(request.getMaximumSeedSize());
		analysisRequest.setMinimumSeedSize(request.getMinimumSeedSize());
		analysisRequest.setNumberSeedsPerRead(request.getNumberSeedsPerRead());
		analysisRequest.setReadMinimumDiscardLength(request.getReadMinimumDiscardLength());
		analysisRequest.setReadMaximumInnerGap(request.getReadMaximumInnerGap());
		analysisRequest.setMinimumNumberSeeds(request.getMinimumNumberSeeds());
		analysisRequest.setFilterReadMappings(request.getFilterReadMappings());
		analysisRequest.setFilterSeedMappings(request.getFilterSeedMappings());
		analysisRequest.setReportAll(request.getReportAll());
		analysisRequest.setReportBest(request.getReportBest());
		analysisRequest.setReportNBest(request.getReportNBest());
		analysisRequest.setReportNHits(request.getReportNHits());
		
		try {
			this.analysisRequestDAO.save(analysisRequest);
		} catch(SaveObjectException e) {
			throw new CreateMethylationAnalysisException(e);
		}
		
		return new CreateMethylationAnalysisResponse(analysisRequest);		
	}
	
	private Boolean checkDuplicatedUserIdentifier(User user, String identifier) {
		
		AnalysisRequest analysisRequest = this.analysisRequestDAO.findByIdentifier(user, identifier);
			
		return analysisRequest != null;
	}
}
