package es.hpgMethyl.usecases.analysis.GetNextPendingMethylationAnalysis;

import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.exceptions.AnalysisRequestNotFound;

public class GetNextPendingMethylationAnalysis {
	
	private AnalysisRequestDAO analysisRequestDAO;

	public GetNextPendingMethylationAnalysis(AnalysisRequestDAO analysisRequestDAO) {
		this.analysisRequestDAO = analysisRequestDAO;
	}
	
	public GetNextPendingMethylationAnalysisResponse execute() throws AnalysisRequestNotFound {
		
		AnalysisRequest analysisRequest = this.analysisRequestDAO.getNextPendingAnalysis();
		
		if(analysisRequest == null) {
			throw new AnalysisRequestNotFound("No pending analysis has been found");
		}
			
		return new GetNextPendingMethylationAnalysisResponse(analysisRequest);	
	}
}
