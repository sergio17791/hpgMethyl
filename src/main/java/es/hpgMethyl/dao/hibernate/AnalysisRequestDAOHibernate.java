package es.hpgMethyl.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.AnalysisRequestNotFound;
import es.hpgMethyl.utils.HibernateUtils;

public class AnalysisRequestDAOHibernate extends BaseDAOHibernate<AnalysisRequest,String> implements AnalysisRequestDAO {

	@Override
	public AnalysisRequest findByIdentifier(User user, String identifier) throws AnalysisRequestNotFound {
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		
		try {		
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<AnalysisRequest> criteriaQuery = criteriaBuilder.createQuery(AnalysisRequest.class);
			
			Root<AnalysisRequest> root = criteriaQuery.from(AnalysisRequest.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("user"), user));
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("identifier"), identifier));
			
			Query<AnalysisRequest> query = session.createQuery(criteriaQuery);	
			
			return query.getSingleResult();
		} catch(NoResultException exception) {
			throw new AnalysisRequestNotFound("Analysis Request " + identifier + " not found", exception);
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
			if(user != null) {
				criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("user"), user));
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