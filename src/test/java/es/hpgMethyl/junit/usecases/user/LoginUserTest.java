package es.hpgMethyl.junit.usecases.user;

import java.util.Date;
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
		this.loginUser = new LoginUser(this.userDaoHibernate);
		this.user = new User(
			UUID.randomUUID(), 
			new Date(), 
			new Date(), 
			"First name", 
			"Last name", 
			"test@test.com", 
			"yk7wtMR4rBr/UPCThvmoiSiw5Lf+hD+VwbQVeb21zXPOi5XJqRoGgxGml1JQj5DdxahbDV+QysvAEt44ZVvA6qrUifWJStKKlL4LGz6rOwjVtg9AblQBFWW2CHxZ85CTN+exdOlKU/cTVjC3uyCaQs6cS/ckzk1DVZPgTZT5lBY=", 
			"Lm7jSnhSQYVOh3mQHToscnO_CV0",
			"Recovery question",
			"uKjTIA2jD1XUhcL2IcYC52FMS5ahYHcpwrt2qyDefsNOTx68reYWiu57ExBIq0KUJHmmeWh6n/bxfpnJ7PouZdaanp3NthhFN6pFMuvvBPWRcfdcpkuL54fntJskAIfMXrsOyEbVLbOuegPi3q0zmQxM3BTB27lKal6k2ZjVcu8=",
			"HYq3kHHP0mcFia25J5qp8YYN8TE",
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
		String password = "passwordtest";
		
		Mockito.when(userDaoHibernate.findByEmail(email)).thenReturn(this.user);
		
		LoginUserRequest request = new LoginUserRequest(email, password);
		
		LoginUserResponse response = this.loginUser.execute(request);
		
		Assert.assertEquals(email, response.getUser().getEmail());
	}
}
