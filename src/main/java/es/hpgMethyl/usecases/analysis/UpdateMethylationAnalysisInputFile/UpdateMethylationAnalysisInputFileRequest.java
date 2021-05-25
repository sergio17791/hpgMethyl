package es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisInputFile;

import java.util.UUID;

import es.hpgMethyl.entities.HPGMethylFile;

public class UpdateMethylationAnalysisInputFileRequest {

	private UUID id;
	
	private HPGMethylFile inputReadFile;

	public UpdateMethylationAnalysisInputFileRequest(UUID id, HPGMethylFile inputReadFile) {
		this.id = id;
		this.inputReadFile = inputReadFile;
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
	 * @return the inputReadFile
	 */
	public HPGMethylFile getInputReadFile() {
		return inputReadFile;
	}

	/**
	 * @param inputReadFile the inputReadFile to set
	 */
	public void setInputReadFile(HPGMethylFile inputReadFile) {
		this.inputReadFile = inputReadFile;
	} 
}
