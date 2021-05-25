package es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisInputFile;

import es.hpgMethyl.entities.AnalysisRequest;

public class UpdateMethylationAnalysisInputFileResponse {

	private AnalysisRequest analysisRequest;

	public UpdateMethylationAnalysisInputFileResponse(AnalysisRequest analysisRequest) {
		this.analysisRequest = analysisRequest;
	}

	/**
	 * @return the analysisRequest
	 */
	public AnalysisRequest getAnalysisRequest() {
		return analysisRequest;
	}
}
