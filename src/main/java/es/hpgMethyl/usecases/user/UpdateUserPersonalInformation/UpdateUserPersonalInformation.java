package es.hpgMethyl.usecases.user.UpdateUserPersonalInformation;

import java.util.UUID;

import es.hpgMethyl.dao.UserDAO;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.exceptions.UpdateUserException;
import es.hpgMethyl.exceptions.UserNotFound;
import es.hpgMethyl.types.UserRole;
import es.hpgMethyl.usecases.user.GetUser.GetUser;
import es.hpgMethyl.usecases.user.GetUser.GetUserRequest;

public class UpdateUserPersonalInformation {

	private UserDAO userDAO;

	public UpdateUserPersonalInformation(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public UpdateUserPersonalInformationResponse execute(UpdateUserPersonalInformationRequest request) throws UserNotFound, UpdateUserException {
		
		UUID id = request.getId();
		
		User user = null;
		
		try {
			user = new GetUser(userDAO).execute(new GetUserRequest(id)).getUser();
		} catch (GetObjectException e) {
			throw new UpdateUserException(e);
		}
		
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setDefaultLanguage(request.getDefaultLanguage());
		
		UserRole role = request.getRole();
		
		if(role != null) {
			user.setRole(role);
		}
		
		try {
			this.userDAO.save(user);
		} catch(SaveObjectException e) {
			throw new UpdateUserException(e);
		}
		
		return new UpdateUserPersonalInformationResponse(user);
	}
}
