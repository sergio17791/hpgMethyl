package es.hpgMethyl.usecases.analysis.GetMethylationAnalysis;

import java.util.UUID;

import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.exceptions.AnalysisRequestNotFound;
import es.hpgMethyl.exceptions.GetObjectException;

public class GetMethylationAnalysis {

	private AnalysisRequestDAO analysisRequestDAO;

	public GetMethylationAnalysis(AnalysisRequestDAO analysisRequestDAO) {
		this.analysisRequestDAO = analysisRequestDAO;
	}
	
	public GetMethylationAnalysisResponse execute(GetMethylationAnalysisRequest request) throws AnalysisRequestNotFound, GetObjectException {
		
		UUID id = request.getId();
		
		AnalysisRequest analysisRequest = this.analysisRequestDAO.get(id);
			
		if(analysisRequest == null) {
			throw new AnalysisRequestNotFound("Analysis Request " + id + " not found");
		}
			
		return new GetMethylationAnalysisResponse(analysisRequest);	
	}
}
