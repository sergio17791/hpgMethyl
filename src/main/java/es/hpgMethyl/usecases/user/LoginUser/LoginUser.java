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
	
	public LoginUserResponse execute(LoginUserRequest request) throws InvalidCredentials {
		
		String email = request.getEmail();
		String password = request.getPassword();
		
		if(email != null && password != null) {

			User user;
			try {
				user = this.userDAO.findByEmail(request.getEmail());
			} catch (UserNotFound e) {
				throw new InvalidCredentials();
			}
			
			Boolean passwordVerified = PasswordUtils.checkPassword(
					password, 
					user.getPassword(), 
					user.getSalt()
			);
					
			if(passwordVerified) {
				return new LoginUserResponse(user);
			}	 
			
		}
								
		throw new InvalidCredentials();
	}
}
 