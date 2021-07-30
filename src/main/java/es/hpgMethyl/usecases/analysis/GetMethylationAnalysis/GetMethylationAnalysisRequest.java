package es.hpgMethyl.usecases.analysis.GetMethylationAnalysis;

import java.util.UUID;

public class GetMethylationAnalysisRequest {

	private UUID id;

	public GetMethylationAnalysisRequest(UUID id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}
}
