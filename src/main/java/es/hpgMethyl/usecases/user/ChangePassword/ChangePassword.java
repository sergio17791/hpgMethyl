package es.hpgMethyl.usecases.user.ChangePassword;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import es.hpgMethyl.dao.UserDAO;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.ChangePasswordException;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.utils.PasswordUtils;

public class ChangePassword {

	private UserDAO userDAO;	
	
	public ChangePassword(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public ChangePasswordResponse execute(ChangePasswordRequest request) throws ChangePasswordException {
		
		User user = request.getUser();
		
		String salt = PasswordUtils.makeSalt();
		
		if(salt == null || salt.isEmpty()) {
			throw new ChangePasswordException("Error generating salt");
		}
		
		String hashedPassword;
		try {
			hashedPassword = PasswordUtils.getHashWithSalt(request.getNewPassword(), salt);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new ChangePasswordException(e);
		}
		
		user.setPasswordSalt(salt);
		user.setPassword(hashedPassword);
		
		try {
			this.userDAO.save(user);
		} catch(SaveObjectException e) {
			throw new ChangePasswordException(e);
		}
		
		return new ChangePasswordResponse(user);
	}
}
