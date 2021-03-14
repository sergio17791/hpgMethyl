package es.hpgMethyl.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.hpgMethyl.dao.BaseDAO;
import es.hpgMethyl.exceptions.DeleteObjectException;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.ListObjectsException;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.utils.HibernateUtils;

public class BaseDAOHibernate<T, ID extends Serializable> implements BaseDAO<T, ID> {
	
	private Class<T> persistentClass; 
	
	private final static Logger LOGGER = Logger.getLogger(BaseDAOHibernate.class.getName());
	
	@SuppressWarnings("unchecked")
	public BaseDAOHibernate() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public Class<T> getPersistentClass() {  
        return persistentClass;  
    }  

	@Override
	public void save(T entity) throws SaveObjectException {
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(entity);
			transaction.commit();
		} catch(HibernateException hibernateException) {
			try {
				if (transaction != null && transaction.isActive()) {
					transaction.rollback();
	            }
			} catch(Exception ex) {
				LOGGER.log(Level.WARNING,"Rollback on save failed", ex);
			} 			
			
			throw new SaveObjectException(hibernateException);
			
		} finally {
			session.close();
		}		
	}

	@Override
	public T get(ID id) throws GetObjectException {
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			T entity = (T) session.get(getPersistentClass(), id);
			transaction.commit();
			
			return entity;
		} catch(HibernateException hibernateException) {
			try {
				if (transaction != null && transaction.isActive()) {
					transaction.rollback();
	            }
			} catch(Exception ex) {
				LOGGER.log(Level.WARNING,"Rollback on get failed", ex);
			} 			
			
			throw new GetObjectException(hibernateException);
			
		} finally {
			session.close();
		}	
	}

	@Override
	public void delete(T entity) throws DeleteObjectException {
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.delete(entity);
			transaction.commit();
		} catch(HibernateException hibernateException) {
			try {
				if (transaction != null && transaction.isActive()) {
					transaction.rollback();
	            }
			} catch(Exception ex) {
				LOGGER.log(Level.WARNING,"Rollback on delete failed", ex);
			} 			
			
			throw new DeleteObjectException(hibernateException);
			
		} finally {
			session.close();
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listAll() throws ListObjectsException {
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		String queryStr = "from " + getPersistentClass().getName() + " entity";
		
		try {
			Query<T> query = session.createQuery(queryStr);
			return query.list();
		} catch(HibernateException hibernateException) {
			throw new ListObjectsException(hibernateException);
		} finally {
			session.close();
		}	
	}
}
