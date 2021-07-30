package es.hpgMethyl.usecases.result.GetMethylationAnalysisResult;

import es.hpgMethyl.dao.AnalysisResultDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.AnalysisResult;
import es.hpgMethyl.exceptions.AnalysisResultNotFound;

public class GetMethylationAnalysisResult {

	private AnalysisResultDAO analysisResultDAO;

	public GetMethylationAnalysisResult(AnalysisResultDAO analysisResultDAO) {
		this.analysisResultDAO = analysisResultDAO;
	}
	
	public GetMethylationAnalysisResultResponse execute(GetMethylationAnalysisResultRequest request) throws AnalysisResultNotFound {
		
		AnalysisRequest analysisRequest = request.getAnalysisRequest();
		
		AnalysisResult analysisResult = this.analysisResultDAO.findByAnalysisRequest(analysisRequest);
			
		if(analysisResult == null) {
			throw new AnalysisResultNotFound("Analysis Result from request " + analysisRequest.getIdentifier() + " not found");
		}
			
		return new GetMethylationAnalysisResultResponse(analysisResult);	
	}
}
