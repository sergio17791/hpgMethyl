package es.hpgMethyl.usecases.analysis.ListPendingMethylationAnalysis;

import java.util.List;

import es.hpgMethyl.entities.AnalysisRequest;

public class ListPendingMethylationAnalysisResponse {

	List<AnalysisRequest> analysisRequestList;

	public ListPendingMethylationAnalysisResponse(List<AnalysisRequest> analysisRequestList) {
		this.analysisRequestList = analysisRequestList;
	}

	/**
	 * @return the analysisRequestList
	 */
	public List<AnalysisRequest> getAnalysisRequestList() {
		return analysisRequestList;
	}	
}
