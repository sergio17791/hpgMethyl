package es.hpgMethyl.usecases.analysis.ListPendingMethylationAnalysisWithFile;

import java.util.List;

import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.entities.AnalysisRequest;

public class ListPendingMethylationAnalysisWithFile {

	private AnalysisRequestDAO analysisRequestDAO;

	public ListPendingMethylationAnalysisWithFile(AnalysisRequestDAO analysisRequestDAO) {
		this.analysisRequestDAO = analysisRequestDAO;
	}
	
	public ListPendingMethylationAnalysisWithFileResponse execute(ListPendingMethylationAnalysisWithFileRequest request) {
		
		List<AnalysisRequest> analysisRequestList = this.analysisRequestDAO.listPendingAnalysisWithFile(
				request.getUser(),
				request.getFile()
		);
		
		return new ListPendingMethylationAnalysisWithFileResponse(analysisRequestList);
	}
}
