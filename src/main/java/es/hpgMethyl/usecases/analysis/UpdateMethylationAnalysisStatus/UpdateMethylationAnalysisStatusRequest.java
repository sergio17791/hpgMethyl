package es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisStatus;

import java.util.UUID;

import es.hpgMethyl.types.AnalysisStatus;

public class UpdateMethylationAnalysisStatusRequest {

	private UUID id;
	
	private AnalysisStatus status;

	public UpdateMethylationAnalysisStatusRequest(UUID id, AnalysisStatus status) {
		this.id = id;
		this.status = status;
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

	/**
	 * @return the status
	 */
	public AnalysisStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(AnalysisStatus status) {
		this.status = status;
	}
}
