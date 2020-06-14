package es.hpgMethyl.junit.usecases.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.hibernate.UserDAOHibernate;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.InvalidCredentials;
import es.hpgMethyl.exceptions.UserNotFound;
import es.hpgMethyl.usecases.user.LoginUser.LoginUser;
import es.hpgMethyl.usecases.user.LoginUser.LoginUserRequest;
import es.hpgMethyl.usecases.user.LoginUser.LoginUserResponse;

public class LoginUserTest {
	
	private LoginUser loginUser;
	
	private User user;
	
	private UserDAOHibernate userDaoHibernate;
	
	@Before
	public void setUp() {
		this.userDaoHibernate = Mockito.mock(UserDAOHibernate.class);
		this.loginUser = new LoginUser(userDaoHibernate);
		this.user = new User(
			UUID.randomUUID(), 
			LocalDateTime.now(), 
			LocalDateTime.now(), 
			"First name", 
			"Last name", 
			"test@test.com", 
			"zjlLDS8dN6GMtm8kt1EAxyEOqb0wtMkgoYfvZccptp91LE+DTJiDMKiTmReKKEjIQU/vLRI0bH+8rMSWPFogmQ==", 
			"hWd34tw8Y9t69Gkl+LVRuguPVBt2WAzESxWv6hv4Qgh/R9IGkZ4ksUmF", 
			true
		);
	}

	@Test(expected = InvalidCredentials.class)
	public void test_execute_givenNullEmail_expectThrowUserNotFoundException() throws InvalidCredentials {
		
		String email = null;
		String password = "fakeemailpassword";
					
		LoginUserRequest request = new LoginUserRequest(email, password);
			
		this.loginUser.execute(request);
	}
	
	@Test(expected = InvalidCredentials.class)
	public void test_execute_givenInvalidEmail_expectThrowUserNotFoundException() throws InvalidCredentials, UserNotFound {
		
		String email = "fake@email.com";
		String password = "fakeemailpassword";
		
		Mockito.when(userDaoHibernate.findByEmail(email)).thenThrow(UserNotFound.class);
		
		LoginUserRequest request = new LoginUserRequest(email, password);
			
		this.loginUser.execute(request);
	}
	
	@Test(expected = InvalidCredentials.class)
	public void test_execute_givenNullPassword_expectThrowUserNotFoundException() throws InvalidCredentials {
		
		String email = "test@test.com";
		String password = null;
			
		LoginUserRequest request = new LoginUserRequest(email, password);
		
		this.loginUser.execute(request);
	}
	
	@Test(expected = InvalidCredentials.class)
	public void test_execute_givenInvalidPassword_expectThrowUserNotFoundException() throws InvalidCredentials, UserNotFound {
		
		String email = "test@test.com";
		String password = "fakeemailpassword";
		
		Mockito.when(userDaoHibernate.findByEmail(email)).thenReturn(this.user);
		
		LoginUserRequest request = new LoginUserRequest(email, password);
		
		this.loginUser.execute(request);
	}
	
	@Test
	public void test_execute_givenValidCredentials_expectUser() throws InvalidCredentials, UserNotFound {
		
		String email = "test@test.com";
		String password = "test";
		
		Mockito.when(userDaoHibernate.findByEmail(email)).thenReturn(this.user);
		
		LoginUserRequest request = new LoginUserRequest(email, password);
		
		LoginUserResponse response = this.loginUser.execute(request);
		
		Assert.assertEquals(email, response.getUser().getEmail());
	}
}
