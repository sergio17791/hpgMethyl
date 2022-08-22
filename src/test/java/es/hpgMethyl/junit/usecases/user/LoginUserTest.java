package es.hpgMethyl.junit.usecases.user;

import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.UserDAO;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.DisabledUser;
import es.hpgMethyl.exceptions.HpgMethylException;
import es.hpgMethyl.exceptions.InvalidCredentials;
import es.hpgMethyl.types.UserRole;
import es.hpgMethyl.usecases.user.LoginUser.LoginUser;
import es.hpgMethyl.usecases.user.LoginUser.LoginUserRequest;
import es.hpgMethyl.usecases.user.LoginUser.LoginUserResponse;

public class LoginUserTest {
	
	private LoginUser loginUser;
	
	private User user;
	
	private UserDAO userDao;
	
	@Before
	public void setUp() {
		this.userDao = Mockito.mock(UserDAO.class);
		this.loginUser = new LoginUser(this.userDao);
		this.user = new User(
			UUID.randomUUID(), 
			new Date(), 
			new Date(), 
			"First name", 
			"Last name", 
			"test@test.com", 
			"aIDiK4Pd/yy+gASC2bgMppRlSEWAlBCUm5JtcHddRa1Po3busHyrlhvMfKeau0urUqAzBkwCc4IeckYfw3wda8Ost2cWdDfjKH6gbcmBJX8sqnEdVTBDUwPUuyA36+R7QGmPFcBIxsDhHI0CKG+OphyfMBeVXzf35dPwucqwVWk=", 
			"Lm7jSnhSQYVOh3mQHToscnO_CV0",
			"Recovery question",
			"uKjTIA2jD1XUhcL2IcYC52FMS5ahYHcpwrt2qyDefsNOTx68reYWiu57ExBIq0KUJHmmeWh6n/bxfpnJ7PouZdaanp3NthhFN6pFMuvvBPWRcfdcpkuL54fntJskAIfMXrsOyEbVLbOuegPi3q0zmQxM3BTB27lKal6k2ZjVcu8=",
			"HYq3kHHP0mcFia25J5qp8YYN8TE",
			true,
			"es",
			UserRole.USER
		);
	}

	@Test(expected = InvalidCredentials.class)
	public void test_execute_givenNullEmail_expectThrowUserNotFoundException() throws HpgMethylException {
		
		String email = null;
		String password = "fakeemailpassword";
					
		LoginUserRequest request = new LoginUserRequest(email, password);
			
		this.loginUser.execute(request);
	}
	
	@Test(expected = InvalidCredentials.class)
	public void test_execute_givenInvalidEmail_expectThrowUserNotFoundException() throws HpgMethylException {
		
		String email = "fake@email.com";
		String password = "fakeemailpassword";
		
		Mockito.doReturn(null).when(userDao).findByEmail(email);
		
		LoginUserRequest request = new LoginUserRequest(email, password);
			
		this.loginUser.execute(request);
	}
	
	@Test(expected = InvalidCredentials.class)
	public void test_execute_givenNullPassword_expectThrowUserNotFoundException() throws HpgMethylException {
		
		String email = "test@test.com";
		String password = null;
			
		LoginUserRequest request = new LoginUserRequest(email, password);
		
		this.loginUser.execute(request);
	}
	
	@Test(expected = InvalidCredentials.class)
	public void test_execute_givenInvalidPassword_expectThrowUserNotFoundException() throws HpgMethylException {
		
		String email = "test@test.com";
		String password = "fakeemailpassword";
		
		Mockito.doReturn(this.user).when(userDao).findByEmail(email);
		
		LoginUserRequest request = new LoginUserRequest(email, password);
		
		this.loginUser.execute(request);
	}
	
	@Test
	public void test_execute_givenValidCredentials_expectUser() throws HpgMethylException {
		
		String email = "test@test.com";
		String password = "passwordtest";
		
		Mockito.doReturn(this.user).when(userDao).findByEmail(email);
		
		LoginUserRequest request = new LoginUserRequest(email, password);
		
		LoginUserResponse response = this.loginUser.execute(request);
		
		Assert.assertEquals(email, response.getUser().getEmail());
	}
	
	@Test(expected = DisabledUser.class)
	public void test_execute_givenDisabledUser_expectThrowDisabledUserException() throws HpgMethylException {
		
		String email = "test@test.com";
		String password = "passwordtest";
		
		this.user.setActive(false);
		
		Mockito.doReturn(this.user).when(userDao).findByEmail(email);
		
		LoginUserRequest request = new LoginUserRequest(email, password);
		
		this.loginUser.execute(request);
	}
}
