package es.hpgMethyl.dao.hibernate;

import java.util.UUID;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import es.hpgMethyl.dao.FileDAO;
import es.hpgMethyl.entities.File;
import es.hpgMethyl.utils.HibernateUtils;

public class FileDAOHibernate extends BaseDAOHibernate<File, UUID> implements FileDAO {

	@Override
	public Boolean existsFile(String fileName, String folder) {
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		
		try {		
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<File> criteriaQuery = criteriaBuilder.createQuery(File.class);
			
			Root<File> root = criteriaQuery.from(File.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("fileName"), fileName));
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("folder"), folder));
			
			Query<File> query = session.createQuery(criteriaQuery);	
			
			return !query.list().isEmpty();
		} catch(NoResultException exception) {
			return false;
		} finally {
			session.close();
		}
	}

}