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
		result.setTotalNumberCAnalysed(request.getTotalNumberCAnalysed());
		result.setTotalMethylatedCCPGContext(request.getTotalMethylatedCCPGContext());
		result.setTotalMethylatedCCHGContext(request.getTotalMethylatedCCHGContext());
		result.setTotalMethylatedCCHHContext(request.getTotalMethylatedCCHHContext());
		result.setTotalCToTConversionsCPGContext(request.getTotalCToTConversionsCPGContext());
		result.setTotalCToTConversionsCHGContext(request.getTotalCToTConversionsCHGContext());
		result.setTotalCToTConversionsCHHContex(request.getTotalCToTConversionsCHHContex());
		result.setcMethylatedCPGContext(request.getcMethylatedCPGContext());
		result.setcMethylatedCHGContext(request.getcMethylatedCHGContext());
		result.setcMethylatedCHHContext(request.getcMethylatedCHHContext());
		result.setLoadingTime(request.getLoadingTime());
		result.setAligmentTime(request.getAligmentTime());
		result.setTotalTime(request.getTotalTime());
		result.setTotalReadsProcessed(request.getTotalReadsProcessed());
		result.setReadsMapped(request.getReadsMapped());
		result.setTotalReadsMapped(request.getTotalReadsMapped());
		result.setReadsUnmapped(request.getReadsUnmapped());
		result.setTotalReadsUnmapped(request.getTotalReadsUnmapped());
		
		try {
			this.analysisResultDAO.save(result);
		} catch (SaveObjectException e) {
			throw new CreateMehtylationResultException(e);
		}
		
		return new CreateMehtylationResultResponse(result);		
	}
}
