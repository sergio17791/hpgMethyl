package es.hpgMethyl.usecases.analysis.ListMethylationAnalysis;

import java.util.List;

import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.exceptions.ListObjectsException;

public class ListMethylationAnalysis {

	private AnalysisRequestDAO analysisRequestDAO;

	public ListMethylationAnalysis(AnalysisRequestDAO analysisRequestDAO) {
		this.analysisRequestDAO = analysisRequestDAO;
	}
	
	public ListMethylationAnalysisResponse execute() throws ListObjectsException {
				
		List<AnalysisRequest> analysisRequestList = this.analysisRequestDAO.listAll();
		
		return new ListMethylationAnalysisResponse(analysisRequestList);
	}
}
