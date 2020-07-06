package es.hpgMethyl.junit.usecases.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.hibernate.UserDAOHibernate;
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
	
	private UserDAOHibernate userDaoHibernate;
	
	@Before
	public void setUp() {
		this.userDaoHibernate = Mockito.mock(UserDAOHibernate.class);
		this.signupUser = new SignupUser(userDaoHibernate);
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
		
		Mockito.doReturn(new User()).when(userDaoHibernate).findByEmail(email);
		
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
		
		Mockito.doThrow(UserNotFound.class).when(userDaoHibernate).findByEmail(email);
		
		Mockito.doThrow(SaveObjectException.class).when(userDaoHibernate).save(Mockito.any(User.class));
		
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
		
		Mockito.doThrow(UserNotFound.class).when(userDaoHibernate).findByEmail(email);
		
		Mockito.doNothing().when(userDaoHibernate).save(Mockito.any(User.class));
		
		SignupUserResponse response = this.signupUser.execute(request);

		Assert.assertEquals(email, response.getUser().getEmail());
		Assert.assertEquals(firstName, response.getUser().getFirstName());
		Assert.assertEquals(lastName, response.getUser().getLastName());
		Assert.assertTrue(response.getUser().isActive());
	}
}
