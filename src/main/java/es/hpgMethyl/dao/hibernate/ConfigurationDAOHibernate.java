package es.hpgMethyl.dao.hibernate;

import java.util.UUID;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import es.hpgMethyl.dao.ConfigurationDAO;
import es.hpgMethyl.entities.Configuration;
import es.hpgMethyl.utils.HibernateUtils;

public class ConfigurationDAOHibernate extends BaseDAOHibernate<Configuration, UUID> implements ConfigurationDAO {

	@Override
	public Configuration getApplicationConfiguration() {

		Session session = HibernateUtils.getSessionFactory().openSession();
		
		try {		
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Configuration> criteriaQuery = criteriaBuilder.createQuery(Configuration.class);
			
			Root<Configuration> root = criteriaQuery.from(Configuration.class);
			criteriaQuery.select(root);
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createdAt")));
			
			Query<Configuration> query = session.createQuery(criteriaQuery);	
			query.setMaxResults(1);
			
			return query.getSingleResult();
		} catch(NoResultException exception) {
			return null;
		} finally {
			session.close();
		}
	}

}
