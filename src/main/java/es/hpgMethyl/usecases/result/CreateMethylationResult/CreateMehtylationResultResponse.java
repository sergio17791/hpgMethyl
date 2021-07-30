package es.hpgMethyl.usecases.result.CreateMethylationResult;

import es.hpgMethyl.entities.AnalysisResult;

public class CreateMehtylationResultResponse {

	private AnalysisResult analysisResult;

	public CreateMehtylationResultResponse(AnalysisResult analysisResult) {
		this.analysisResult = analysisResult;
	}

	/**
	 * @return the analysisResult
	 */
	public AnalysisResult getAnalysisResult() {
		return analysisResult;
	}
}
