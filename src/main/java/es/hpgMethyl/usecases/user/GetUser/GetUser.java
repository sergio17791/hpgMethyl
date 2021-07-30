package es.hpgMethyl.usecases.user.GetUser;

import java.util.UUID;

import es.hpgMethyl.dao.UserDAO;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.UserNotFound;

public class GetUser {

	private UserDAO userDAO;

	public GetUser(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public GetUserResponse execute(GetUserRequest request) throws GetObjectException, UserNotFound {
		
		UUID id = request.getId();
		
		User user = this.userDAO.get(id);
			
		if(user == null) {
			throw new UserNotFound("User " + id + " not found");
		}
			
		return new GetUserResponse(user);	
	}
}
