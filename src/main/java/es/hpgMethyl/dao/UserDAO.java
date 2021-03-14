package es.hpgMethyl.dao;

import java.util.UUID;

import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.UserNotFound;

public interface UserDAO extends BaseDAO<User, UUID> {
	User findByEmail(String email);
}
