package es.hpgMethyl.dao.hibernate;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import es.hpgMethyl.dao.UserDAO;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.UserNotFound;
import es.hpgMethyl.utils.HibernateUtils;

public class UserDAOHibernate extends BaseDAOHibernate<User,String> implements UserDAO {

	@Override
	public User findByEmail(String email) throws UserNotFound {
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		
		try {		
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
			
			Root<User> root = criteriaQuery.from(User.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("email"), email));
			
			Query<User> query = session.createQuery(criteriaQuery);	
			
			return query.getSingleResult();
		} catch(NoResultException exception) {
			throw new UserNotFound("User " + email + " not found", exception);
		} finally {
			session.close();
		}
	}

}