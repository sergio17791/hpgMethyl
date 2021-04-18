package es.hpgMethyl.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import es.hpgMethyl.dao.hibernate.UserDAOHibernate;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.ListObjectsException;
import es.hpgMethyl.usecases.user.ListUsers.ListUsers;

@ManagedBean(name="listUsersBean")
@RequestScoped
public class ListUsersBean implements Serializable {

	private static final long serialVersionUID = 6574209270626227917L;
	
	private List<User> users;

	public ListUsersBean() {
		this.users = new ArrayList<User>();
	}
	
	@PostConstruct
	public void init() {
		
		try {
			this.users = new ListUsers(new UserDAOHibernate()).execute().getUsers();
		} catch (ListObjectsException e) {
			this.users = new ArrayList<User>();
		}
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
