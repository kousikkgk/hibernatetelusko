package com.kousik.HibernateCaching;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class FirstLevelCacheEx {

	public static void main(String[] args) {
		
		StudentCache cache = null;
		Configuration cfg = new Configuration().configure()
				.addAnnotatedClass(StudentCache.class);

		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();

		SessionFactory sf = cfg.buildSessionFactory(reg);
		
		/*First level cache is done with in same session
		 * if the query is same and it will run on same session it will fired one time and fetch and stored in first level cache 
		 * */
		Session session1 = sf.openSession();
		session1.beginTransaction();
		cache=(StudentCache)session1.get(StudentCache.class, 1);
		System.out.println(cache);
		cache=(StudentCache)session1.get(StudentCache.class, 1);
		System.out.println(cache);
		session1.getTransaction().commit();
		session1.close();
		
		/*if the query is same and it will run on different session 
		 * it will fired each session and stored in first level cache 
		 * */
		Session session2 = sf.openSession();
		session2.beginTransaction();
		cache=(StudentCache)session2.get(StudentCache.class, 1);
		System.out.println(cache);	
		session2.getTransaction().commit();
		session2.close();
	}

}
