package es.hpgMethyl.usecases.result.CreateErrorMethylationResult;

import es.hpgMethyl.dao.AnalysisResultDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.AnalysisResult;
import es.hpgMethyl.exceptions.CreateMehtylationResultException;
import es.hpgMethyl.exceptions.DuplicatedAnalysisResult;
import es.hpgMethyl.exceptions.SaveObjectException;

public class CreateErrorMethylationResult {

	private AnalysisResultDAO analysisResultDAO;

	public CreateErrorMethylationResult(AnalysisResultDAO analysisResultDAO) {
		this.analysisResultDAO = analysisResultDAO;
	}
	
	public CreateErrorMethylationResultResponse execute(CreateErrorMethylationResultRequest request) throws CreateMehtylationResultException, DuplicatedAnalysisResult {
		
		AnalysisRequest analysisRequest = request.getAnalysisRequest();
		
		if(this.analysisResultDAO.findByAnalysisRequest(analysisRequest) != null) {
			throw new DuplicatedAnalysisResult();
		}
		
		AnalysisResult result = new AnalysisResult();
		result.setAnalysisRequest(analysisRequest);
		result.setError(request.getError());
		
		try {
			this.analysisResultDAO.save(result);
		} catch (SaveObjectException e) {
			throw new CreateMehtylationResultException(e);
		}
		
		return new CreateErrorMethylationResultResponse(result);		
	}
}
