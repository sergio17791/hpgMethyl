package es.hpgMethyl.usecases.analysis.ListUserMethylationAnalysis;

import java.util.List;

import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.entities.AnalysisRequest;

public class ListUserMethylationAnalysis {
	
	private AnalysisRequestDAO analysisRequestDAO;

	public ListUserMethylationAnalysis(AnalysisRequestDAO analysisRequestDAO) {
		this.analysisRequestDAO = analysisRequestDAO;
	}
	
	public ListUserMethylationAnalysisResponse execute(ListUserMethylationAnalysisRequest request) {
	
		List<AnalysisRequest> analysisRequestList = this.analysisRequestDAO.list(request.getUser());
		
		return new ListUserMethylationAnalysisResponse(analysisRequestList);
	}
}
