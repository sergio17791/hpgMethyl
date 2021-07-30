package es.hpgMethyl.usecases.user.ListUsers;

import java.util.List;

import es.hpgMethyl.dao.UserDAO;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.ListObjectsException;

public class ListUsers {

	private UserDAO userDAO;

	public ListUsers(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public ListUsersResponse execute() throws ListObjectsException {
				
		List<User> users = this.userDAO.listAll();	
			
		return new ListUsersResponse(users);	
	}
}
