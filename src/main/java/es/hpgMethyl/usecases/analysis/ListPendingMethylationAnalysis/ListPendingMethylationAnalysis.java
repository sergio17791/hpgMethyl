package es.hpgMethyl.usecases.analysis.ListPendingMethylationAnalysis;

import java.util.List;

import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.entities.AnalysisRequest;

public class ListPendingMethylationAnalysis {

	private AnalysisRequestDAO analysisRequestDAO;

	public ListPendingMethylationAnalysis(AnalysisRequestDAO analysisRequestDAO) {
		this.analysisRequestDAO = analysisRequestDAO;
	}
	
	public ListPendingMethylationAnalysisResponse execute(ListPendingMethylationAnalysisRequest request) {
		
		List<AnalysisRequest> analysisRequestList = this.analysisRequestDAO.listPendingAnalysis(
				request.getUser(),
				request.getFile()
		);
		
		return new ListPendingMethylationAnalysisResponse(analysisRequestList);
	}
}
