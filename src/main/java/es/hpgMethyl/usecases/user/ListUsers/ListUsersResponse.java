package es.hpgMethyl.usecases.user.ListUsers;

import java.util.List;

import es.hpgMethyl.entities.User;

public class ListUsersResponse {

	List<User> users;

	public ListUsersResponse(List<User> users) {
		this.users = users;
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}
}
