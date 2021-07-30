package es.hpgMethyl.usecases.analysis.CreateMethylationAnalysis;

import es.hpgMethyl.entities.AnalysisRequest;

public class CreateMethylationAnalysisResponse {

	private AnalysisRequest analysisRequest;

	public CreateMethylationAnalysisResponse(AnalysisRequest analysisRequest) {
		this.analysisRequest = analysisRequest;
	}

	/**
	 * @return the analysisRequest
	 */
	public AnalysisRequest getAnalysisRequest() {
		return analysisRequest;
	}
}
