package es.hpgMethyl.usecases.user.UpdatePasswordRecovery;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

import es.hpgMethyl.dao.UserDAO;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.exceptions.UpdateUserException;
import es.hpgMethyl.exceptions.UserNotFound;
import es.hpgMethyl.usecases.user.GetUser.GetUser;
import es.hpgMethyl.usecases.user.GetUser.GetUserRequest;
import es.hpgMethyl.utils.PasswordUtils;

public class UpdatePasswordRecovery {

	private UserDAO userDAO;

	public UpdatePasswordRecovery(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public UpdatePasswordRecoveryResponse execute(UpdatePasswordRecoveryRequest request) throws UpdateUserException, UserNotFound {
		
		UUID id = request.getId();
		
		User user = null;
		
		try {
			user = new GetUser(userDAO).execute(new GetUserRequest(id)).getUser();
		} catch (GetObjectException e) {
			throw new UpdateUserException(e);
		}
		
		String salt = PasswordUtils.makeSalt();
		
		if(salt == null || salt.isEmpty()) {
			throw new UpdateUserException("Error generating salt");
		}
		
		String hashedPasswordRecoveryResponse;
		try {
			hashedPasswordRecoveryResponse = PasswordUtils.getHashWithSalt(request.getPasswordRecoveryResponse(), salt);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new UpdateUserException(e);
		}
		
		user.setPasswordRecoveryQuestion(request.getPasswordRecoveryQuestion());
		user.setPasswordRecoveryResponse(hashedPasswordRecoveryResponse);	
		user.setPasswordRecoveryResponseSalt(salt);
		
		try {
			this.userDAO.save(user);
		} catch(SaveObjectException e) {
			throw new UpdateUserException(e);
		}
		
		return new UpdatePasswordRecoveryResponse(user);
	}
}
