package es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisPairedFile;

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

public class UpdateMethylationAnalysisPairedFile {

	private AnalysisRequestDAO analysisRequestDAO;

	public UpdateMethylationAnalysisPairedFile(AnalysisRequestDAO analysisRequestDAO) {
		this.analysisRequestDAO = analysisRequestDAO;
	}
	
	public UpdateMethylationAnalysisPairedFileResponse execute(UpdateMethylationAnalysisPairedFileRequest request) throws AnalysisRequestNotFound, AnalysisRequestProcessed, UpdateMethylationAnalysisException {
		
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
		
		analysisRequest.setPairedEndModeFile(request.getPairedFile());
		
		try {
			this.analysisRequestDAO.save(analysisRequest);
		} catch(SaveObjectException e) {
			throw new UpdateMethylationAnalysisException(e);
		}
		
		return new UpdateMethylationAnalysisPairedFileResponse(analysisRequest);
	}
}
