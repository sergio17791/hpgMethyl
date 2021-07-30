package es.hpgMethyl.usecases.user.GetUserByEmail;

import es.hpgMethyl.dao.UserDAO;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.UserNotFound;

public class GetUserByEmail {
	
	private UserDAO userDAO;

	public GetUserByEmail(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public GetUserByEmailResponse execute(GetUserByEmailRequest request) throws UserNotFound {
		
		String email = request.getEmail();
		
		User user = this.userDAO.findByEmail(email);
		
		if(user == null) {
			throw new UserNotFound("User " + email + " not found");
		}
		
		return new GetUserByEmailResponse(user);
	}
}
