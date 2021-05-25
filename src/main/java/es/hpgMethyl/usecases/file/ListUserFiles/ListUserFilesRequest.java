package es.hpgMethyl.usecases.file.ListUserFiles;

import es.hpgMethyl.entities.User;

public class ListUserFilesRequest {
	
	private User user;
	
	private Boolean stored;

	public ListUserFilesRequest(User user, Boolean stored) {
		this.user = user;
		this.stored = stored;
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
	 * @return the stored
	 */
	public Boolean getStored() {
		return stored;
	}

	/**
	 * @param stored the stored to set
	 */
	public void setStored(Boolean stored) {
		this.stored = stored;
	}
}
