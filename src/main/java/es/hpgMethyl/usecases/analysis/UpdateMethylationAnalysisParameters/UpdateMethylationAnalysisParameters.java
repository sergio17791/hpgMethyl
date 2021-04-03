package es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisParameters;

import java.util.UUID;

import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.AnalysisRequestNotFound;
import es.hpgMethyl.exceptions.DuplicatedIdentifier;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.exceptions.UpdateMethylationAnalysisException;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysisRequest;

public class UpdateMethylationAnalysisParameters {

	private AnalysisRequestDAO analysisRequestDAO;

	public UpdateMethylationAnalysisParameters(AnalysisRequestDAO analysisRequestDAO) {
		this.analysisRequestDAO = analysisRequestDAO;
	}
	
	public UpdateMethylationAnalysisParametersResponse execute(UpdateMethylationAnalysisParametersRequest request) throws AnalysisRequestNotFound, DuplicatedIdentifier, UpdateMethylationAnalysisException {
		
		UUID id = request.getId();
		
		AnalysisRequest analysisRequest = null;
		try {
			analysisRequest = new GetMethylationAnalysis(analysisRequestDAO).execute(
					new GetMethylationAnalysisRequest(id)
			).getAnalysisRequest();
		} catch (GetObjectException e) {
			throw new UpdateMethylationAnalysisException(e);
		}
		
		String newIdentifier = request.getIdentifier();
		
		if(!analysisRequest.getIdentifier().equals(newIdentifier)) {
			if(checkDuplicatedUserIdentifier(analysisRequest.getUser(), newIdentifier)) {
				throw new DuplicatedIdentifier();
			}
		}
				
		analysisRequest.setIdentifier(newIdentifier);
		analysisRequest.setWriteMethylationContext(request.getWriteMethylationContext());
		analysisRequest.setReadBatchSize(request.getReadBatchSize());
		analysisRequest.setWriteBatchSize(request.getWriteBatchSize());
		analysisRequest.setPairedMode(request.getPairedMode());
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
			throw new UpdateMethylationAnalysisException(e);
		}
		
		return new UpdateMethylationAnalysisParametersResponse(analysisRequest);
	}
	
	private Boolean checkDuplicatedUserIdentifier(User user, String identifier) {
		
		AnalysisRequest analysisRequest = this.analysisRequestDAO.findByIdentifier(user, identifier);
			
		return analysisRequest != null;
	}
}
