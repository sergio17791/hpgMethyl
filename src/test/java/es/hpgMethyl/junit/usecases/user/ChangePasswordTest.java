package es.hpgMethyl.junit.usecases.user;

import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.hibernate.UserDAOHibernate;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.ChangePasswordException;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.usecases.user.ChangePassword.ChangePassword;
import es.hpgMethyl.usecases.user.ChangePassword.ChangePasswordRequest;
import es.hpgMethyl.usecases.user.ChangePassword.ChangePasswordResponse;

public class ChangePasswordTest {

	private User user;
	
	private ChangePassword changePassword;
	
	private UserDAOHibernate userDaoHibernate;
	
	@Before
	public void setUp() {
		this.userDaoHibernate = Mockito.mock(UserDAOHibernate.class);
		this.changePassword = new ChangePassword(userDaoHibernate);
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
	
	@Test(expected = ChangePasswordException.class)
	public void test_execute_givenConstraintViolation_expectThrowChangePasswordException() throws ChangePasswordException, SaveObjectException {
		
		String newPassword = "newpasswordtest";
		
		ChangePasswordRequest request = new ChangePasswordRequest(this.user, newPassword);
		
		Mockito.doThrow(SaveObjectException.class).when(userDaoHibernate).save(Mockito.any(User.class));
		
		this.changePassword.execute(request);
	}
	
	@Test
	public void test_execute_givenValidRequest_expectUser() throws ChangePasswordException, SaveObjectException {
		
		String newPassword = "changedpasswordtest";
		
		String email = this.user.getEmail();
		String oldHashedPassword = this.user.getPassword();
		
		ChangePasswordRequest request = new ChangePasswordRequest(this.user, newPassword);
		
		Mockito.doNothing().when(userDaoHibernate).save(Mockito.any(User.class));
		
		ChangePasswordResponse response = this.changePassword.execute(request);
		
		Assert.assertEquals(email, response.getUser().getEmail());
		Assert.assertNotEquals(oldHashedPassword, response.getUser().getPassword());
	}
}
