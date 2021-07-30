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

import es.hpgMethyl.dao.AnalysisResultDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.AnalysisResult;
import es.hpgMethyl.utils.HibernateUtils;

public class AnalysisResultDAOHibernate extends BaseDAOHibernate<AnalysisResult, UUID> implements AnalysisResultDAO {

	@Override
	public AnalysisResult findByAnalysisRequest(AnalysisRequest analysisRequest) {

		Session session = HibernateUtils.getSessionFactory().openSession();
		
		try {		
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<AnalysisResult> criteriaQuery = criteriaBuilder.createQuery(AnalysisResult.class);
			
			Root<AnalysisResult> root = criteriaQuery.from(AnalysisResult.class);
			criteriaQuery.select(root);
			
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(criteriaBuilder.equal(root.get("analysisRequest"), analysisRequest));
			
			criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
			
			Query<AnalysisResult> query = session.createQuery(criteriaQuery);	
			query.setMaxResults(1);
			
			return query.getSingleResult();
		} catch(NoResultException exception) {
			return null;
		} finally {
			session.close();
		}
	}

}
