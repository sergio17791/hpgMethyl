package es.hpgMethyl.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import es.hpgMethyl.dao.HPGMethylFileDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.types.AnalysisStatus;
import es.hpgMethyl.utils.HibernateUtils;

public class HPGMethylFileDAOHibernate extends BaseDAOHibernate<HPGMethylFile, UUID> implements HPGMethylFileDAO {

	@Override
	public Boolean existsFile(User user, String fileName, Boolean stored) {
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		
		try {		
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<HPGMethylFile> criteriaQuery = criteriaBuilder.createQuery(HPGMethylFile.class);
			
			Root<HPGMethylFile> root = criteriaQuery.from(HPGMethylFile.class);
			criteriaQuery.select(root);
			
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(criteriaBuilder.equal(root.get("user"), user));
			predicates.add(criteriaBuilder.equal(root.get("fileName"), fileName));
			if(stored != null) {
				predicates.add(criteriaBuilder.equal(root.get("stored"), stored));	
			}				
			
			criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));

			Query<HPGMethylFile> query = session.createQuery(criteriaQuery);	
			query.setMaxResults(1);
			
			return query.getSingleResult() != null;
		} catch(NoResultException exception) {
			return false;
		} finally {
			session.close();
		}
	}

	@Override
	public List<HPGMethylFile> list(User user, Boolean stored) {
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		
		try {		
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<HPGMethylFile> criteriaQuery = criteriaBuilder.createQuery(HPGMethylFile.class);
			
			Root<HPGMethylFile> root = criteriaQuery.from(HPGMethylFile.class);
			criteriaQuery.select(root);
			
			List<Predicate> predicates = new ArrayList<Predicate>();			
			if(user != null) {
				predicates.add(criteriaBuilder.equal(root.get("user"), user));
			}			
			if(stored != null) {
				predicates.add(criteriaBuilder.equal(root.get("stored"), stored));
			}
			
			if(!predicates.isEmpty()) {
				criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
			}
			
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get("fileName")));

			Query<HPGMethylFile> query = session.createQuery(criteriaQuery);	
			
			return query.getResultList();
		} catch(NoResultException exception) {
			return new ArrayList<HPGMethylFile>();
		} finally {
			session.close();
		}
	}	
}