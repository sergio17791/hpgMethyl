package es.hpgMethyl.dao;

import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.UserNotFound;

public interface UserDAO extends BaseDAO<User, String> {
	User findByEmail(String email) throws UserNotFound;
}
