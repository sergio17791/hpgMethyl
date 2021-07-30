package es.hpgMethyl.usecases.file.ExistsFile;

public class ExistsFileResponse {

	private Boolean exists;

	public ExistsFileResponse(Boolean exists) {
		this.exists = exists;
	}

	public Boolean getExists() {
		return exists;
	}
}
