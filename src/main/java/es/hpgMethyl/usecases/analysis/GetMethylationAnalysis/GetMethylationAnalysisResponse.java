package es.hpgMethyl.usecases.analysis.GetMethylationAnalysis;

import es.hpgMethyl.entities.AnalysisRequest;

public class GetMethylationAnalysisResponse {

	private AnalysisRequest analysisRequest;

	public GetMethylationAnalysisResponse(AnalysisRequest analysisRequest) {
		this.analysisRequest = analysisRequest;
	}

	/**
	 * @return the analysisRequest
	 */
	public AnalysisRequest getAnalysisRequest() {
		return analysisRequest;
	}
}
