package es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisStatus;

import es.hpgMethyl.entities.AnalysisRequest;

public class UpdateMethylationAnalysisStatusResponse {

	private AnalysisRequest analysisRequest;

	public UpdateMethylationAnalysisStatusResponse(AnalysisRequest analysisRequest) {
		this.analysisRequest = analysisRequest;
	}

	/**
	 * @return the analysisRequest
	 */
	public AnalysisRequest getAnalysisRequest() {
		return analysisRequest;
	}	
}
