package es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisStatus;

import java.util.UUID;

import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.exceptions.AnalysisRequestNotFound;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.exceptions.UpdateMethylationAnalysisException;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysisRequest;

public class UpdateMethylationAnalysisStatus {

	private AnalysisRequestDAO analysisRequestDAO;

	public UpdateMethylationAnalysisStatus(AnalysisRequestDAO analysisRequestDAO) {
		this.analysisRequestDAO = analysisRequestDAO;
	}
	
	public UpdateMethylationAnalysisStatusResponse execute(UpdateMethylationAnalysisStatusRequest request) throws AnalysisRequestNotFound, UpdateMethylationAnalysisException {
		
		UUID id = request.getId();
		
		AnalysisRequest analysisRequest = null;
		try {
			analysisRequest = new GetMethylationAnalysis(analysisRequestDAO).execute(
					new GetMethylationAnalysisRequest(id)
			).getAnalysisRequest();
		} catch (GetObjectException e) {
			throw new UpdateMethylationAnalysisException(e);
		}
		
		analysisRequest.setStatus(request.getStatus());
		
		try {
			this.analysisRequestDAO.save(analysisRequest);
		} catch(SaveObjectException e) {
			throw new UpdateMethylationAnalysisException(e);
		}
		
		return new UpdateMethylationAnalysisStatusResponse(analysisRequest);
	}
}
