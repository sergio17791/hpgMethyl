package es.hpgMethyl.usecases.analysis.GetNextPendingMethylationAnalysis;

import es.hpgMethyl.entities.AnalysisRequest;

public class GetNextPendingMethylationAnalysisResponse {
	
	private AnalysisRequest analysisRequest;

	public GetNextPendingMethylationAnalysisResponse(AnalysisRequest analysisRequest) {
		this.analysisRequest = analysisRequest;
	}

	/**
	 * @return the analysisRequest
	 */
	public AnalysisRequest getAnalysisRequest() {
		return analysisRequest;
	}
}
