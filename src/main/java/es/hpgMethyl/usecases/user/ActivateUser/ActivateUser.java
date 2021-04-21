package es.hpgMethyl.usecases.user.ActivateUser;

import java.util.UUID;

import es.hpgMethyl.dao.UserDAO;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.exceptions.UpdateUserException;
import es.hpgMethyl.exceptions.UserNotFound;
import es.hpgMethyl.usecases.user.GetUser.GetUser;
import es.hpgMethyl.usecases.user.GetUser.GetUserRequest;

public class ActivateUser {

	private UserDAO userDAO;

	public ActivateUser(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public ActivateUserResponse execute(ActivateUserRequest request) throws UpdateUserException, UserNotFound {
		
		UUID id = request.getId();
		
		User user = null;
		
		try {
			user = new GetUser(userDAO).execute(new GetUserRequest(id)).getUser();
		} catch (GetObjectException e) {
			throw new UpdateUserException(e);
		}
		
		user.setActive(true);
		
		try {
			this.userDAO.save(user);
		} catch(SaveObjectException e) {
			throw new UpdateUserException(e);
		}
		
		return new ActivateUserResponse(user);
	}
}
