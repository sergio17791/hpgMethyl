package es.hpgMethyl.usecases.user.LoginUser;

import es.hpgMethyl.dao.UserDAO;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.InvalidCredentials;
import es.hpgMethyl.exceptions.UserNotFound;
import es.hpgMethyl.utils.PasswordUtils;

public class LoginUser {
	
	private UserDAO userDAO;	
	
	public LoginUser(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public LoginUserResponse execute(LoginUserRequest request) throws InvalidCredentials, UserNotFound {
		
		User user = this.userDAO.findByEmail(request.getEmail());

		Boolean passwordVerified = PasswordUtils.checkPassword(
				request.getPassword(), 
				user.getPassword(), 
				user.getSalt()
		);
			
		if(passwordVerified) {
			return new LoginUserResponse(user);
		}			
				
		throw new InvalidCredentials();
	}
}
 