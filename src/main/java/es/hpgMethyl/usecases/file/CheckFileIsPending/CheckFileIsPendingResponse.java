package es.hpgMethyl.usecases.file.CheckFileIsPending;

public class CheckFileIsPendingResponse {

	private Boolean pending;

	public CheckFileIsPendingResponse(Boolean pending) {
		this.pending = pending;
	}

	/**
	 * @return the pending
	 */
	public Boolean getPending() {
		return pending;
	}	
}
