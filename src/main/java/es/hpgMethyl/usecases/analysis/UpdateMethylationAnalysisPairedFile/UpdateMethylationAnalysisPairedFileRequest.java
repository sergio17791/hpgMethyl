package es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisPairedFile;

import java.util.UUID;

import es.hpgMethyl.entities.HPGMethylFile;

public class UpdateMethylationAnalysisPairedFileRequest {

	private UUID id;
	
	private HPGMethylFile pairedFile;

	public UpdateMethylationAnalysisPairedFileRequest(UUID id, HPGMethylFile pairedFile) {
		this.id = id;
		this.pairedFile = pairedFile;
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
	 * @return the pairedFile
	 */
	public HPGMethylFile getPairedFile() {
		return pairedFile;
	}

	/**
	 * @param pairedFile the pairedFile to set
	 */
	public void setPairedFile(HPGMethylFile pairedFile) {
		this.pairedFile = pairedFile;
	}
}
