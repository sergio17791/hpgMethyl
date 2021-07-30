package es.hpgMethyl.usecases.result.CreateMethylationResult;

import es.hpgMethyl.dao.AnalysisResultDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.AnalysisResult;
import es.hpgMethyl.exceptions.CreateMehtylationResultException;
import es.hpgMethyl.exceptions.DuplicatedAnalysisResult;
import es.hpgMethyl.exceptions.SaveObjectException;

public class CreateMehtylationResult {

	private AnalysisResultDAO analysisResultDAO;

	public CreateMehtylationResult(AnalysisResultDAO analysisResultDAO) {
		this.analysisResultDAO = analysisResultDAO;
	}
	
	public CreateMehtylationResultResponse execute(CreateMehtylationResultRequest request) throws CreateMehtylationResultException, DuplicatedAnalysisResult {
		
		AnalysisRequest analysisRequest = request.getAnalysisRequest();
		
		if(this.analysisResultDAO.findByAnalysisRequest(analysisRequest) != null) {
			throw new DuplicatedAnalysisResult();
		}
		
		AnalysisResult result = new AnalysisResult();
		result.setAnalysisRequest(analysisRequest);
		result.setResultFile(request.getResultFile());
		
		try {
			this.analysisResultDAO.save(result);
		} catch (SaveObjectException e) {
			throw new CreateMehtylationResultException(e);
		}
		
		return new CreateMehtylationResultResponse(result);		
	}
}
