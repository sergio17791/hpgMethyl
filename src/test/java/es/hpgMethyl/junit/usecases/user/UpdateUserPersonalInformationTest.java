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
import es.hpgMethyl.usecases.user.UpdateUserPersonalInformation.UpdateUserPersonalInformation;
import es.hpgMethyl.usecases.user.UpdateUserPersonalInformation.UpdateUserPersonalInformationRequest;
import es.hpgMethyl.usecases.user.UpdateUserPersonalInformation.UpdateUserPersonalInformationResponse;

public class UpdateUserPersonalInformationTest {

	private UpdateUserPersonalInformation updateUserPersonalInformation;
	
	private UserDAO userDAO;
	
	private User user;
	
	@Before
	public void setUp() {
		this.userDAO = Mockito.mock(UserDAO.class);
		this.updateUserPersonalInformation = new UpdateUserPersonalInformation(this.userDAO);
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
				"es"
		);
	}
	
	@Test(expected = UpdateUserException.class)
	public void test_execute_givenAnErrorWhenGetUserObject_expectThrowUpdateUserException() throws HpgMethylException {
		
		UUID id = user.getId();
		
		UpdateUserPersonalInformationRequest request = new UpdateUserPersonalInformationRequest(
				id,
				user.getFirstName(),
				user.getLastName(),
				user.getDefaultLanguage()
		);
		
		Mockito.doThrow(GetObjectException.class).when(userDAO).get(id);
		
		this.updateUserPersonalInformation.execute(request);
	}
	
	@Test(expected = UserNotFound.class)
	public void test_execute_givenAnUnexistingUserID_expectThrowUserNotFound() throws HpgMethylException {

		UUID id = UUID.randomUUID();
		
		UpdateUserPersonalInformationRequest request = new UpdateUserPersonalInformationRequest(
				id,
				user.getFirstName(),
				user.getLastName(),
				user.getDefaultLanguage()
		);
		
		Mockito.doReturn(null).when(userDAO).get(id);
		
		this.updateUserPersonalInformation.execute(request);
	}
	
	@Test(expected = UpdateUserException.class)
	public void test_execute_givenAnErrorWhenUpdateUserObject_expectThrowUpdateUserException() throws HpgMethylException {
		
		UUID id = user.getId();
		
		UpdateUserPersonalInformationRequest request = new UpdateUserPersonalInformationRequest(
				id,
				user.getFirstName(),
				user.getLastName(),
				user.getDefaultLanguage()
		);
		
		Mockito.doReturn(user).when(userDAO).get(id);		
		
		Mockito.doThrow(SaveObjectException.class).when(userDAO).save(Mockito.any(User.class));
		
		this.updateUserPersonalInformation.execute(request);
	}
	
	@Test
	public void test_execute_givenValidRequest_expectUser() throws HpgMethylException {
		
		UUID id = user.getId();
		
		UpdateUserPersonalInformationRequest request = new UpdateUserPersonalInformationRequest(
				id,
				"Updated First Name",
				user.getLastName(),
				user.getDefaultLanguage()
		);
		
		Mockito.doReturn(user).when(userDAO).get(id);
				
		Mockito.doNothing().when(userDAO).save(Mockito.any(User.class));
		
		UpdateUserPersonalInformationResponse useCaseResponse = this.updateUserPersonalInformation.execute(request);
		
		User response = useCaseResponse.getUser();
		
		Assert.assertEquals(request.getFirstName(), response.getFirstName());
		Assert.assertEquals(request.getLastName(), response.getLastName());
		Assert.assertEquals(request.getDefaultLanguage(), response.getDefaultLanguage());
	}
}
