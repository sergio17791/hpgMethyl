package es.hpgMethyl.usecases.result.CreateErrorMethylationResult;

import es.hpgMethyl.entities.AnalysisRequest;

public class CreateErrorMethylationResultRequest {

	private AnalysisRequest analysisRequest;
	
	private String error;

	public CreateErrorMethylationResultRequest(
			AnalysisRequest analysisRequest, 
			String error
		) {
		this.analysisRequest = analysisRequest;
		this.error = error;
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

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
}
