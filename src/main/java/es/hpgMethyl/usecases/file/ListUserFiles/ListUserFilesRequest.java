package es.hpgMethyl.usecases.file.ListUserFiles;

import es.hpgMethyl.entities.User;

public class ListUserFilesRequest {
	
	private User user;

	public ListUserFilesRequest(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
