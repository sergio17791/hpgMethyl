package es.hpgMethyl.usecases.analysis.ListPendingMethylationAnalysisWithFile;

import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.entities.User;

public class ListPendingMethylationAnalysisWithFileRequest {

	private User user;
	
	private HPGMethylFile file;

	public ListPendingMethylationAnalysisWithFileRequest(User user, HPGMethylFile file) {
		this.user = user;
		this.file = file;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the file
	 */
	public HPGMethylFile getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(HPGMethylFile file) {
		this.file = file;
	}
}
