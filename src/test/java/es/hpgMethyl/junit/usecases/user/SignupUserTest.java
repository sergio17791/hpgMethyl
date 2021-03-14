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
		String password = "passwordtest";
		String firstName = "First name";
		String lastName = "Last name";
		String passwordRecoveryQuestion = "Recovery question";
		String passwordRecoveryResponse = "Recovery response";
		
		SignupUserRequest request = new SignupUserRequest(email, password, firstName, lastName, passwordRecoveryQuestion, passwordRecoveryResponse);
		
		Mockito.doReturn(new User()).when(userDao).findByEmail(email);
		
		this.signupUser.execute(request);
	}
	
	@Test(expected = SignupUserException.class)
	public void test_execute_givenConstraintViolation_expectThrowSignupUserException() throws DuplicatedEmail, SignupUserException, SaveObjectException, UserNotFound {	
		
		String email = "test@email.com";
		String password = "passwordtest";
		String firstName = "First name";
		String lastName = "Last name";
		String passwordRecoveryQuestion = "Recovery question";
		String passwordRecoveryResponse = "Recovery response";
		
		SignupUserRequest request = new SignupUserRequest(email, password, firstName, lastName, passwordRecoveryQuestion, passwordRecoveryResponse);
		
		Mockito.doReturn(null).when(userDao).findByEmail(email);
		
		Mockito.doThrow(SaveObjectException.class).when(userDao).save(Mockito.any(User.class));
		
		this.signupUser.execute(request);
	}
	
	@Test
	public void test_execute_givenValidRequest_expectUser() throws DuplicatedEmail, SignupUserException, SaveObjectException, UserNotFound {	
		
		String email = "test@email.com";
		String password = "passwordtest";
		String firstName = "First name";
		String lastName = "Last name";
		String passwordRecoveryQuestion = "Recovery question";
		String passwordRecoveryResponse = "Recovery response";
		
		SignupUserRequest request = new SignupUserRequest(email, password, firstName, lastName, passwordRecoveryQuestion, passwordRecoveryResponse);
		
		Mockito.doReturn(null).when(userDao).findByEmail(email);
		
		Mockito.doNothing().when(userDao).save(Mockito.any(User.class));
		
		SignupUserResponse response = this.signupUser.execute(request);

		Assert.assertEquals(email, response.getUser().getEmail());
		Assert.assertEquals(firstName, response.getUser().getFirstName());
		Assert.assertEquals(lastName, response.getUser().getLastName());
		Assert.assertTrue(response.getUser().isActive());
	}
}
