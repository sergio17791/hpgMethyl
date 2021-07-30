package es.hpgMethyl.usecases.result.CreateMethylationResult;

import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.HPGMethylFile;

public class CreateMehtylationResultRequest {

	private AnalysisRequest analysisRequest;
	
	private HPGMethylFile resultFile;

	public CreateMehtylationResultRequest(AnalysisRequest analysisRequest, HPGMethylFile resultFile) {
		this.analysisRequest = analysisRequest;
		this.resultFile = resultFile;
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
	 * @return the resultFile
	 */
	public HPGMethylFile getResultFile() {
		return resultFile;
	}

	/**
	 * @param resultFile the resultFile to set
	 */
	public void setResultFile(HPGMethylFile resultFile) {
		this.resultFile = resultFile;
	}		
}
