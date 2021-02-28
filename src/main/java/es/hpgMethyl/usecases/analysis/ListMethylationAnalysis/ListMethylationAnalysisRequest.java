package es.hpgMethyl.usecases.analysis.ListMethylationAnalysis;

import es.hpgMethyl.entities.User;

public class ListMethylationAnalysisRequest {

	User user;

	public ListMethylationAnalysisRequest(User user) {
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
