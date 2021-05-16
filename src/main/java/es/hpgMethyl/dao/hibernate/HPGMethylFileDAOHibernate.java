package es.hpgMethyl.dao.hibernate;

import java.util.UUID;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import es.hpgMethyl.dao.HPGMethylFileDAO;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.utils.HibernateUtils;

public class HPGMethylFileDAOHibernate extends BaseDAOHibernate<HPGMethylFile, UUID> implements HPGMethylFileDAO {

	@Override
	public Boolean existsFile(String path) {
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		
		try {		
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<HPGMethylFile> criteriaQuery = criteriaBuilder.createQuery(HPGMethylFile.class);
			
			Root<HPGMethylFile> root = criteriaQuery.from(HPGMethylFile.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("path"), path));
			
			Query<HPGMethylFile> query = session.createQuery(criteriaQuery);	
			
			return !query.list().isEmpty();
		} catch(NoResultException exception) {
			return false;
		} finally {
			session.close();
		}
	}
}