package es.hpgMethyl.junit.usecases.user;

import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.hibernate.UserDAOHibernate;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.UserNotFound;
import es.hpgMethyl.usecases.user.GetUserByEmail.GetUserByEmail;
import es.hpgMethyl.usecases.user.GetUserByEmail.GetUserByEmailRequest;
import es.hpgMethyl.usecases.user.GetUserByEmail.GetUserByEmailResponse;

public class GetUserByEmailTest {

	private GetUserByEmail getUserByEmail;
	
	private UserDAOHibernate userDaoHibernate;
	
	@Before
	public void setUp() {
		this.userDaoHibernate = Mockito.mock(UserDAOHibernate.class);
		this.getUserByEmail = new GetUserByEmail(userDaoHibernate);
	}
	
	@Test(expected = UserNotFound.class)
	public void test_execute_givenNonExistentEmail_expectThrowUserNotFound() throws UserNotFound {
		
		String email = "test@email.com";
		
		GetUserByEmailRequest request = new GetUserByEmailRequest(email);
		
		Mockito.doThrow(UserNotFound.class).when(userDaoHibernate).findByEmail(email);
		
		this.getUserByEmail.execute(request);
	}
	
	@Test
	public void test_execute_givenExistentEmail_expectUser() throws UserNotFound {
		
		String email = "test@email.com";
		
		User user = new User(
				UUID.randomUUID(), 
				new Date(), 
				new Date(), 
				"First name", 
				"Last name", 
				email, 
				"yk7wtMR4rBr/UPCThvmoiSiw5Lf+hD+VwbQVeb21zXPOi5XJqRoGgxGml1JQj5DdxahbDV+QysvAEt44ZVvA6qrUifWJStKKlL4LGz6rOwjVtg9AblQBFWW2CHxZ85CTN+exdOlKU/cTVjC3uyCaQs6cS/ckzk1DVZPgTZT5lBY=", 
				"Lm7jSnhSQYVOh3mQHToscnO_CV0",
				"Recovery question",
				"uKjTIA2jD1XUhcL2IcYC52FMS5ahYHcpwrt2qyDefsNOTx68reYWiu57ExBIq0KUJHmmeWh6n/bxfpnJ7PouZdaanp3NthhFN6pFMuvvBPWRcfdcpkuL54fntJskAIfMXrsOyEbVLbOuegPi3q0zmQxM3BTB27lKal6k2ZjVcu8=",
				"HYq3kHHP0mcFia25J5qp8YYN8TE",
				true
			);
		
		GetUserByEmailRequest request = new GetUserByEmailRequest(email);
		
		Mockito.when(userDaoHibernate.findByEmail(email)).thenReturn(user);
		
		GetUserByEmailResponse response = this.getUserByEmail.execute(request);
		
		Assert.assertEquals(user.getId(), response.getUser().getId());
		Assert.assertEquals(email, response.getUser().getEmail());
		Assert.assertEquals(user.getPassword(), response.getUser().getPassword());
	}
}
