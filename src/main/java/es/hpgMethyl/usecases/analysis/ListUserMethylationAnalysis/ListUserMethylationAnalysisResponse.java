package es.hpgMethyl.usecases.analysis.ListUserMethylationAnalysis;

import java.util.List;

import es.hpgMethyl.entities.AnalysisRequest;

public class ListUserMethylationAnalysisResponse {

	List<AnalysisRequest> analysisRequestList;

	public ListUserMethylationAnalysisResponse(List<AnalysisRequest> analysisRequestList) {
		this.analysisRequestList = analysisRequestList;
	}

	/**
	 * @return the analysisRequestList
	 */
	public List<AnalysisRequest> getAnalysisRequestList() {
		return analysisRequestList;
	}	
}
