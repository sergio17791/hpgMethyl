package es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisInputFile;

import java.util.UUID;

import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.exceptions.AnalysisRequestNotFound;
import es.hpgMethyl.exceptions.AnalysisRequestProcessed;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.exceptions.UpdateMethylationAnalysisException;
import es.hpgMethyl.types.AnalysisStatus;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysisRequest;

public class UpdateMethylationAnalysisInputFile {

	private AnalysisRequestDAO analysisRequestDAO;

	public UpdateMethylationAnalysisInputFile(AnalysisRequestDAO analysisRequestDAO) {
		this.analysisRequestDAO = analysisRequestDAO;
	}
	
	public UpdateMethylationAnalysisInputFileResponse execute(UpdateMethylationAnalysisInputFileRequest request) throws AnalysisRequestNotFound, AnalysisRequestProcessed, UpdateMethylationAnalysisException {
		
		UUID id = request.getId();
		
		AnalysisRequest analysisRequest = null;
		try {
			analysisRequest = new GetMethylationAnalysis(analysisRequestDAO).execute(
					new GetMethylationAnalysisRequest(id)
			).getAnalysisRequest();
		} catch (GetObjectException e) {
			throw new UpdateMethylationAnalysisException(e);
		}
		
		if(!analysisRequest.getStatus().equals(AnalysisStatus.CREATED)) {
			throw new AnalysisRequestProcessed("Analysis Request " + id + " has already been processed");
		}
		
		analysisRequest.setInputReadFile(request.getInputReadFile());
		
		try {
			this.analysisRequestDAO.save(analysisRequest);
		} catch(SaveObjectException e) {
			throw new UpdateMethylationAnalysisException(e);
		}
		
		return new UpdateMethylationAnalysisInputFileResponse(analysisRequest);
	}
}
