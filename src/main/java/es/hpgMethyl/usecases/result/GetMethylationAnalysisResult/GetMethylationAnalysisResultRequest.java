package es.hpgMethyl.usecases.result.GetMethylationAnalysisResult;

import es.hpgMethyl.entities.AnalysisRequest;

public class GetMethylationAnalysisResultRequest {

	private AnalysisRequest analysisRequest;

	public GetMethylationAnalysisResultRequest(AnalysisRequest analysisRequest) {
		this.analysisRequest = analysisRequest;
	}

	/**
	 * @return the analysisRequest
	 */
	public AnalysisRequest getAnalysisRequest() {
		return analysisRequest;
	}

	/**
	 * @param analysisRequest the analysisRequest to set
	 */
	public void setAnalysisRequest(AnalysisRequest analysisRequest) {
		this.analysisRequest = analysisRequest;
	}
}
