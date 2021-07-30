package es.hpgMethyl.usecases.analysis.ListMethylationAnalysis;

import java.util.List;

import es.hpgMethyl.entities.AnalysisRequest;

public class ListMethylationAnalysisResponse {

	List<AnalysisRequest> analysisRequestList;

	public ListMethylationAnalysisResponse(List<AnalysisRequest> analysisRequestList) {
		this.analysisRequestList = analysisRequestList;
	}

	/**
	 * @return the analysisRequestList
	 */
	public List<AnalysisRequest> getAnalysisRequestList() {
		return analysisRequestList;
	}	
}
