package es.hpgMethyl.usecases.analysis.ListPendingMethylationAnalysisWithFile;

import java.util.List;

import es.hpgMethyl.entities.AnalysisRequest;

public class ListPendingMethylationAnalysisWithFileResponse {

	List<AnalysisRequest> analysisRequestList;

	public ListPendingMethylationAnalysisWithFileResponse(List<AnalysisRequest> analysisRequestList) {
		this.analysisRequestList = analysisRequestList;
	}

	/**
	 * @return the analysisRequestList
	 */
	public List<AnalysisRequest> getAnalysisRequestList() {
		return analysisRequestList;
	}	
}
