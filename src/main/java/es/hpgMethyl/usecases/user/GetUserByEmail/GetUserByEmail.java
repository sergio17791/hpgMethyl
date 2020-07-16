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
		
		User user = this.userDAO.findByEmail(request.getEmail());
		
		return new GetUserByEmailResponse(user);
	}
}
