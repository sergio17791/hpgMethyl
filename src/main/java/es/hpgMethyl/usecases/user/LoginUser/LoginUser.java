package es.hpgMethyl.usecases.user.LoginUser;

import es.hpgMethyl.dao.UserDAO;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.DisabledUser;
import es.hpgMethyl.exceptions.InvalidCredentials;
import es.hpgMethyl.utils.PasswordUtils;

public class LoginUser {
	
	private UserDAO userDAO;	
	
	public LoginUser(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public LoginUserResponse execute(LoginUserRequest request) throws DisabledUser, InvalidCredentials {
		
		String email = request.getEmail();
		String password = request.getPassword();
		
		if(email != null && password != null) {

			User user = this.userDAO.findByEmail(request.getEmail());
			
			if(user == null) {
				throw new InvalidCredentials();
			}						
			
			Boolean passwordVerified = PasswordUtils.checkPassword(
					password, 
					user.getPassword(), 
					user.getPasswordSalt()
			);
					
			if(passwordVerified) {	
				
				if(!user.getActive()) {
					throw new DisabledUser("User " + email + " is disabled");
				}
				
				return new LoginUserResponse(user);
			}	 			
		}
								
		throw new InvalidCredentials();
	}
}
 