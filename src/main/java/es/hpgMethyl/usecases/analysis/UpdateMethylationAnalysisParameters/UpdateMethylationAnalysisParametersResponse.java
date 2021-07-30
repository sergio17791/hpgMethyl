package es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisParameters;

import es.hpgMethyl.entities.AnalysisRequest;

public class UpdateMethylationAnalysisParametersResponse {
	
	private AnalysisRequest analysisRequest;

	public UpdateMethylationAnalysisParametersResponse(AnalysisRequest analysisRequest) {
		this.analysisRequest = analysisRequest;
	}

	/**
	 * @return the analysisRequest
	 */
	public AnalysisRequest getAnalysisRequest() {
		return analysisRequest;
	}
}
