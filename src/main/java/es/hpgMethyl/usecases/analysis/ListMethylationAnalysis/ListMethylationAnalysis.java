package es.hpgMethyl.usecases.analysis.ListMethylationAnalysis;

import java.util.List;

import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.entities.AnalysisRequest;

public class ListMethylationAnalysis {
	
	private AnalysisRequestDAO analysisRequestDAO;

	public ListMethylationAnalysis(AnalysisRequestDAO analysisRequestDAO) {
		this.analysisRequestDAO = analysisRequestDAO;
	}
	
	public ListMethylationAnalysisResponse execute(ListMethylationAnalysisRequest request) {
	
		List<AnalysisRequest> analysisRequestList = this.analysisRequestDAO.list(request.getUser());
		
		return new ListMethylationAnalysisResponse(analysisRequestList);
	}
}
