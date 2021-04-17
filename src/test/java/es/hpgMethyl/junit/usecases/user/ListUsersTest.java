package es.hpgMethyl.junit.usecases.user;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;

import es.hpgMethyl.dao.UserDAO;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.HpgMethylException;
import es.hpgMethyl.exceptions.ListObjectsException;
import es.hpgMethyl.usecases.user.ListUsers.ListUsers;
import es.hpgMethyl.usecases.user.ListUsers.ListUsersResponse;

public class ListUsersTest {
	
	private UserDAO userDAO;
	
	private ListUsers listUsers;
	
	@Before
	public void setUp() {
		this.userDAO = Mockito.mock(UserDAO.class);
		this.listUsers = new ListUsers(this.userDAO);		
	}
	
	public void test_execute_givenAnErrorWhenListUsersObjects_expectListObjectsException() throws HpgMethylException {
				
		Mockito.doThrow(ListObjectsException.class).when(userDAO).listAll();
		
		this.listUsers.execute();
	}
	
	public void test_execute_givenNonExistentUsers_expectEmptyList() throws HpgMethylException {
		
		Mockito.doReturn(new ArrayList<User>()).when(userDAO).listAll();
		
		ListUsersResponse response = this.listUsers.execute();
		
		Assert.assertTrue(response.getUsers().isEmpty());
	}
	
	public void test_execute_givenUserWithAnalysis_expectList() throws HpgMethylException {
		
		List<User> users = new ArrayList<User>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		
		Mockito.doReturn(users).when(userDAO).listAll();
		
		ListUsersResponse response = this.listUsers.execute();
		
		Assert.assertFalse(response.getUsers().isEmpty());
	}
}
