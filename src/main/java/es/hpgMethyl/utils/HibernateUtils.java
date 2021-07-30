package es.hpgMethyl.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;

public final class HibernateUtils {
	
    private static SessionFactory sessionFactory;
    
    private HibernateUtils() {};
    
    public static synchronized void buildSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.username", System.getProperty("DB_USER")); 
            configuration.setProperty("hibernate.connection.password", System.getProperty("DB_PASSWORD"));
            configuration.setProperty("hibernate.current_session_context_class", "thread");         
            sessionFactory = configuration.configure().buildSessionFactory();
        }
    }
    
    public static void openSessionAndBindToThread() {
        Session session = sessionFactory.openSession();
        ThreadLocalSessionContext.bind(session);
    }
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory==null)  {
            buildSessionFactory();
        }
        return sessionFactory;
    }
    
    public static void closeSessionAndUnbindFromThread() {
        Session session = ThreadLocalSessionContext.unbind(sessionFactory);
        if (session!=null) {
            session.close();
        }
    }
   
    public static void closeSessionFactory() {
    	if ((sessionFactory!=null) && (sessionFactory.isClosed()==false)) {
            sessionFactory.close();
        }
    }
}
