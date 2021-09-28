package es.hpgMethyl.usecases.result.CreateErrorMethylationResult;

import es.hpgMethyl.entities.AnalysisResult;

public class CreateErrorMethylationResultResponse {

	private AnalysisResult analysisResult;

	public CreateErrorMethylationResultResponse(AnalysisResult analysisResult) {
		this.analysisResult = analysisResult;
	}

	/**
	 * @return the analysisResult
	 */
	public AnalysisResult getAnalysisResult() {
		return analysisResult;
	}
}
