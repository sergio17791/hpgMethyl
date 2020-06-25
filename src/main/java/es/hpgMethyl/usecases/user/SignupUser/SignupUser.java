package es.hpgMethyl.usecases.user.SignupUser;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import es.hpgMethyl.dao.UserDAO;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.exceptions.SignupUserException;
import es.hpgMethyl.utils.PasswordUtils;

public class SignupUser {

	private UserDAO userDAO;	
	
	public SignupUser(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public SignupUserResponse execute(SignupUserRequest request) throws SignupUserException {
				
		Optional<String> salt = PasswordUtils.makeSalt(32);
		
		if(salt.isEmpty()) {
			throw new SignupUserException("Error creating salt");
		}
		
		String password = null;
		try {
			password = PasswordUtils.getHashWithSalt(request.getPassword(), salt.get());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new SignupUserException(e);
		}
		
		User user = new User();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setSalt(salt.get());
		user.setPassword(password);
		
		try {
			this.userDAO.save(user);
		} catch(SaveObjectException e) {
			throw new SignupUserException(e);
		}
		
		return new SignupUserResponse(user);
	}
}
