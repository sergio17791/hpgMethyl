package es.hpgMethyl.junit.usecases.user;

import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.UserDAO;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.HpgMethylException;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.exceptions.UpdateUserException;
import es.hpgMethyl.exceptions.UserNotFound;
import es.hpgMethyl.types.UserRole;
import es.hpgMethyl.usecases.user.DeactivateUser.DeactivateUser;
import es.hpgMethyl.usecases.user.DeactivateUser.DeactivateUserRequest;
import es.hpgMethyl.usecases.user.DeactivateUser.DeactivateUserResponse;

public class DeactivateUserTest {

	private DeactivateUser deactivateUser;
	
	private UserDAO userDAO;
	
	private User user;
	
	@Before
	public void setUp() {
		this.userDAO = Mockito.mock(UserDAO.class);
		this.deactivateUser = new DeactivateUser(this.userDAO);
		this.user = new User(
				UUID.randomUUID(), 
				new Date(), 
				new Date(), 
				"First name", 
				"Last name", 
				"updateUserPersonalInformationTest@user.com", 
				"yk7wtMR4rBr/UPCThvmoiSiw5Lf+hD+VwbQVeb21zXPOi5XJqRoGgxGml1JQj5DdxahbDV+QysvAEt44ZVvA6qrUifWJStKKlL4LGz6rOwjVtg9AblQBFWW2CHxZ85CTN+exdOlKU/cTVjC3uyCaQs6cS/ckzk1DVZPgTZT5lBY=", 
				"Lm7jSnhSQYVOh3mQHToscnO_CV0",
				"Recovery question",
				"uKjTIA2jD1XUhcL2IcYC52FMS5ahYHcpwrt2qyDefsNOTx68reYWiu57ExBIq0KUJHmmeWh6n/bxfpnJ7PouZdaanp3NthhFN6pFMuvvBPWRcfdcpkuL54fntJskAIfMXrsOyEbVLbOuegPi3q0zmQxM3BTB27lKal6k2ZjVcu8=",
				"HYq3kHHP0mcFia25J5qp8YYN8TE",
				true,
				"es",
				UserRole.USER
		);
	}
	
	@Test(expected = UpdateUserException.class)
	public void test_execute_givenAnErrorWhenGetUserObject_expectThrowUpdateUserException() throws HpgMethylException {
		
		UUID id = user.getId();
		
		DeactivateUserRequest request = new DeactivateUserRequest(id);
		
		Mockito.doThrow(GetObjectException.class).when(userDAO).get(id);
		
		this.deactivateUser.execute(request);
	}
	
	@Test(expected = UserNotFound.class)
	public void test_execute_givenAnUnexistingUserID_expectThrowUserNotFound() throws HpgMethylException {

		UUID id = UUID.randomUUID();
		
		DeactivateUserRequest request = new DeactivateUserRequest(id);
		
		Mockito.doReturn(null).when(userDAO).get(id);
		
		this.deactivateUser.execute(request);
	}
	
	@Test(expected = UpdateUserException.class)
	public void test_execute_givenAnErrorWhenUpdateUserObject_expectThrowUpdateUserException() throws HpgMethylException {
		
		UUID id = user.getId();
		
		DeactivateUserRequest request = new DeactivateUserRequest(id);
		
		Mockito.doReturn(user).when(userDAO).get(id);		
		
		Mockito.doThrow(SaveObjectException.class).when(userDAO).save(Mockito.any(User.class));
		
		this.deactivateUser.execute(request);
	}
	
	@Test
	public void test_execute_givenValidRequest_expectUser() throws HpgMethylException {
		
		UUID id = user.getId();
		
		DeactivateUserRequest request = new DeactivateUserRequest(id);
		
		Mockito.doReturn(user).when(userDAO).get(id);
				
		Mockito.doNothing().when(userDAO).save(Mockito.any(User.class));
		
		DeactivateUserResponse useCaseResponse = this.deactivateUser.execute(request);
		
		User response = useCaseResponse.getUser();
		
		Assert.assertFalse(response.getActive());
	}
}
