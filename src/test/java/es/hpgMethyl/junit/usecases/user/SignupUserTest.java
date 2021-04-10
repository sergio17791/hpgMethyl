package es.hpgMethyl.junit.usecases.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.UserDAO;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.DuplicatedEmail;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.exceptions.SignupUserException;
import es.hpgMethyl.exceptions.UserNotFound;
import es.hpgMethyl.usecases.user.SignupUser.SignupUser;
import es.hpgMethyl.usecases.user.SignupUser.SignupUserRequest;
import es.hpgMethyl.usecases.user.SignupUser.SignupUserResponse;

public class SignupUserTest {
	
	private SignupUser signupUser;
	
	private UserDAO userDao;
	
	@Before
	public void setUp() {
		this.userDao = Mockito.mock(UserDAO.class);
		this.signupUser = new SignupUser(userDao);
	}
	
	@Test(expected = DuplicatedEmail.class)
	public void test_execute_givenDuplicateEmail_expectThrowDuplicatedEmail() throws DuplicatedEmail, SignupUserException, SaveObjectException, UserNotFound {	
		
		String email = "test@email.com";
		
		SignupUserRequest request = new SignupUserRequest(
				email, 
				"passwordtest", 
				"First name", 
				"Last name", 
				"Recovery question", 
				"Recovery response", 
				"en"
		);
		
		Mockito.doReturn(new User()).when(userDao).findByEmail(email);
		
		this.signupUser.execute(request);
	}
	
	@Test(expected = SignupUserException.class)
	public void test_execute_givenConstraintViolation_expectThrowSignupUserException() throws DuplicatedEmail, SignupUserException, SaveObjectException, UserNotFound {	
		
		String email = "test@email.com";
		
		SignupUserRequest request = new SignupUserRequest(
				email, 
				"passwordtest", 
				"First name", 
				"Last name", 
				"Recovery question", 
				"Recovery response", 
				"en"
		);
		
		Mockito.doReturn(null).when(userDao).findByEmail(email);
		
		Mockito.doThrow(SaveObjectException.class).when(userDao).save(Mockito.any(User.class));
		
		this.signupUser.execute(request);
	}
	
	@Test
	public void test_execute_givenValidRequest_expectUser() throws DuplicatedEmail, SignupUserException, SaveObjectException, UserNotFound {	
		
		String email = "test@email.com";
		
		SignupUserRequest request = new SignupUserRequest(
				email, 
				"passwordtest", 
				"First name", 
				"Last name", 
				"Recovery question", 
				"Recovery response", 
				"en"
		);
		
		Mockito.doReturn(null).when(userDao).findByEmail(email);
		
		Mockito.doNothing().when(userDao).save(Mockito.any(User.class));
		
		SignupUserResponse response = this.signupUser.execute(request);

		Assert.assertEquals(request.getEmail(), response.getUser().getEmail());
		Assert.assertEquals(request.getFirstName(), response.getUser().getFirstName());
		Assert.assertEquals(request.getLastName(), response.getUser().getLastName());
		Assert.assertEquals(request.getPasswordRecoveryQuestion(), response.getUser().getPasswordRecoveryQuestion());
		Assert.assertEquals(request.getDefaultLanguage(), response.getUser().getDefaultLanguage());
		Assert.assertTrue(response.getUser().isActive());
	}
}
