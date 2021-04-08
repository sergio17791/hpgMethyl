package es.hpgMethyl.junit.usecases.user;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.UserDAO;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.HpgMethylException;
import es.hpgMethyl.exceptions.UserNotFound;
import es.hpgMethyl.usecases.user.GetUser.GetUser;
import es.hpgMethyl.usecases.user.GetUser.GetUserRequest;
import es.hpgMethyl.usecases.user.GetUser.GetUserResponse;

public class GetUserTest {

	private GetUser getUser;
	
	private UserDAO userDAO;
	
	@Before
	public void setUp() {
		this.userDAO = Mockito.mock(UserDAO.class);
		this.getUser = new GetUser(this.userDAO);
	}
	
	@Test(expected = GetObjectException.class)
	public void test_execute_givenAnErrorWhenGetUserObject_expectThrowGetObjectException() throws HpgMethylException {
		
		UUID id = UUID.randomUUID();
		
		Mockito.doThrow(GetObjectException.class).when(userDAO).get(id);
		
		this.getUser.execute(new GetUserRequest(id));
	}
	
	@Test(expected = UserNotFound.class)
	public void test_execute_givenNonExistentUser_expectThrowUsertNotFound() throws HpgMethylException {
		
		UUID id = UUID.randomUUID();
		
		Mockito.doReturn(null).when(userDAO).get(id);
		
		this.getUser.execute(new GetUserRequest(id));
	}
	
	@Test
	public void test_execute_givenExistentID_expectUser() throws HpgMethylException {
		
		UUID id = UUID.randomUUID();
		
		User user = new User();
		user.setId(id);
		
		Mockito.doReturn(user).when(userDAO).get(id);
		
		GetUserResponse response = this.getUser.execute(new GetUserRequest(id));
		
		Assert.assertEquals(id, response.getUser().getId());
	}
}
