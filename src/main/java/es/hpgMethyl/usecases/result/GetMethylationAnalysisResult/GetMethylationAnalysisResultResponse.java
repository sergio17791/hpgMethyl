package es.hpgMethyl.usecases.result.GetMethylationAnalysisResult;

import es.hpgMethyl.entities.AnalysisResult;

public class GetMethylationAnalysisResultResponse {

	private AnalysisResult analysisResult;

	public GetMethylationAnalysisResultResponse(AnalysisResult analysisResult) {
		this.analysisResult = analysisResult;
	}

	/**
	 * @return the analysisResult
	 */
	public AnalysisResult getAnalysisResult() {
		return analysisResult;
	}
}
