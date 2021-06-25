package es.hpgMethyl.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.types.AnalysisStatus;
import es.hpgMethyl.utils.HibernateUtils;

public class AnalysisRequestDAOHibernate extends BaseDAOHibernate<AnalysisRequest, UUID> implements AnalysisRequestDAO {

	@Override
	public AnalysisRequest findByIdentifier(User user, String identifier) {
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		
		try {		
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<AnalysisRequest> criteriaQuery = criteriaBuilder.createQuery(AnalysisRequest.class);
			
			Root<AnalysisRequest> root = criteriaQuery.from(AnalysisRequest.class);
			criteriaQuery.select(root);
			
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(criteriaBuilder.equal(root.get("user"), user));
			predicates.add(criteriaBuilder.equal(root.get("identifier"), identifier));
			
			criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
			
			Query<AnalysisRequest> query = session.createQuery(criteriaQuery);	
			query.setMaxResults(1);
			
			return query.getSingleResult();
		} catch(NoResultException exception) {
			return null;
		} finally {
			session.close();
		}
	}
	
	@Override
	public AnalysisRequest getNextPendingAnalysis() {
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		
		try {		
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<AnalysisRequest> criteriaQuery = criteriaBuilder.createQuery(AnalysisRequest.class);
			
			Root<AnalysisRequest> root = criteriaQuery.from(AnalysisRequest.class);
			criteriaQuery.select(root);
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get("createdAt")));
			
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(criteriaBuilder.equal(root.get("status"), AnalysisStatus.CREATED));

			criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));

			Query<AnalysisRequest> query = session.createQuery(criteriaQuery);	
			query.setMaxResults(1);
			
			return query.getSingleResult();
		} catch(NoResultException exception) {
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<AnalysisRequest> list(User user) {
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		
		try {		
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<AnalysisRequest> criteriaQuery = criteriaBuilder.createQuery(AnalysisRequest.class);
			
			Root<AnalysisRequest> root = criteriaQuery.from(AnalysisRequest.class);
			criteriaQuery.select(root);
			
			List<Predicate> predicates = new ArrayList<Predicate>();
			if(user != null) {
				predicates.add(criteriaBuilder.equal(root.get("user"), user));
			}
			
			if(!predicates.isEmpty()) {
				criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
			}

			Query<AnalysisRequest> query = session.createQuery(criteriaQuery);	
			
			return query.getResultList();
		} catch(NoResultException exception) {
			return new ArrayList<AnalysisRequest>();
		} finally {
			session.close();
		}
	}
}