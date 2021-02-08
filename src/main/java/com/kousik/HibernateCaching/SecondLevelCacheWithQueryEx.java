package com.kousik.HibernateCaching;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SecondLevelCacheWithQueryEx {

	public static void main(String[] args) {
		EmployeeCache empCache = null;

		Configuration cfg = new Configuration().configure()
				.addAnnotatedClass(EmployeeCache.class);

		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();

		SessionFactory sf = cfg.buildSessionFactory(reg);
		
		/* To enable Query Cache follow the below steps
		 * 1. Enable the Query cache in cfg file
		 *  		<property name="hibernate.cache.use_query_cache">true</property>
		 * 2. set the query has been cachable
		 * 			 q1.setCacheable(true);
		 * */
		
		Session session1=sf.openSession();
		session1.beginTransaction();
		Query q1 = session1.createQuery("from EmployeeCache where empId=1");
		q1.setCacheable(true);
		
		empCache=(EmployeeCache)q1.uniqueResult();
		System.out.println(empCache);
		
		session1.getTransaction().commit();
		session1.close();
		
		Session session2=sf.openSession();
		session2.beginTransaction();
		Query q2 = session2.createQuery("from EmployeeCache where empId=1");
		q2.setCacheable(true);
		
		empCache=(EmployeeCache)q2.uniqueResult();
		System.out.println(empCache);
		
		session2.getTransaction().commit();
		session2.close();
	
	
	}

}
