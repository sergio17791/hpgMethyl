package es.hpgMethyl.usecases.user.SignupUser;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import es.hpgMethyl.dao.UserDAO;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.DuplicatedEmail;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.exceptions.SignupUserException;
import es.hpgMethyl.exceptions.UserNotFound;
import es.hpgMethyl.utils.PasswordUtils;

public class SignupUser {

	private UserDAO userDAO;	
	
	public SignupUser(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public SignupUserResponse execute(SignupUserRequest request) throws DuplicatedEmail, SignupUserException {
		
		if(checkDuplicatedEmail(request.getEmail())) {
			throw new DuplicatedEmail();
		}
				
		String passwordSalt = generateSalt();
		
		String passwordRecoveryResponseSalt = generateSalt();
		
		String passwordRecoveryResponseFormatted = request.getPasswordRecoveryResponse().replaceAll("\\s","").toLowerCase();

		String password = generateHashWithSalt(request.getPassword(), passwordSalt);
		String passwordRecoveryResponse = generateHashWithSalt(passwordRecoveryResponseFormatted, passwordRecoveryResponseSalt);
		
		User user = new User();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPasswordSalt(passwordSalt);
		user.setPassword(password);
		user.setPasswordRecoveryQuestion(request.getPasswordRecoveryQuestion());
		user.setPasswordRecoveryResponse(passwordRecoveryResponse);
		user.setPasswordRecoveryResponseSalt(passwordRecoveryResponseSalt);
		
		try {
			this.userDAO.save(user);
		} catch(SaveObjectException e) {
			throw new SignupUserException(e);
		}
		
		return new SignupUserResponse(user);
	}
	
	private Boolean checkDuplicatedEmail(String email) {
		try {
			this.userDAO.findByEmail(email);
			return true;
		} catch (UserNotFound e) {
			return false;
		}
	}
	
	private String generateSalt() throws SignupUserException {
		
		String salt = PasswordUtils.makeSalt();
		
		if(salt == null || salt.isEmpty()) {
			throw new SignupUserException("Error generating salt");
		}
		
		return salt;
	}
	
	private String generateHashWithSalt(String string, String salt) throws SignupUserException {
		
		try {
			return PasswordUtils.getHashWithSalt(string, salt);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new SignupUserException(e);
		}
	}
}
