package com.bringoz.driverscore;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bringoz.driverscore.model.Driver;

public class HibernateConf {
	 private static SessionFactory sessionFactory;
 
    public static void shutdown() {
    	sessionFactory.close();
    }
    
    
    public static Session getHibernateSession() {

        		if(sessionFactory==null) {
        		 Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        		 configuration.addAnnotatedClass(Driver.class);
        		 
        		 sessionFactory  = configuration.buildSessionFactory();
        		}
        final Session session = sessionFactory.openSession();
        return session;
        }
}