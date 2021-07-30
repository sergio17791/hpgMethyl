package es.hpgMethyl.usecases.analysis.ListUserMethylationAnalysis;

import es.hpgMethyl.entities.User;

public class ListUserMethylationAnalysisRequest {

	private User user;

	public ListUserMethylationAnalysisRequest(User user) {
		this.user = user;
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
}
