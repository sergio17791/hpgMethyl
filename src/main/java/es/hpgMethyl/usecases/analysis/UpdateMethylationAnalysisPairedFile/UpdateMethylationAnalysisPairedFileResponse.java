package es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisPairedFile;

import es.hpgMethyl.entities.AnalysisRequest;

public class UpdateMethylationAnalysisPairedFileResponse {

	private AnalysisRequest analysisRequest;

	public UpdateMethylationAnalysisPairedFileResponse(AnalysisRequest analysisRequest) {
		this.analysisRequest = analysisRequest;
	}

	/**
	 * @return the analysisRequest
	 */
	public AnalysisRequest getAnalysisRequest() {
		return analysisRequest;
	}
}
