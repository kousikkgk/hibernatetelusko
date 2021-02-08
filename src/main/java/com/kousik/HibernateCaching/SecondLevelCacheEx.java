package com.kousik.HibernateCaching;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SecondLevelCacheEx {

	public static void main(String[] args) {
		
		EmployeeCache empCache = null;
//		emp1.setEmpName("Kousik");
//		
//		EmployeeCache emp2 = new EmployeeCache();
//		emp1.setEmpName("Daniel");
		
		Configuration cfg = new Configuration().configure()
				.addAnnotatedClass(EmployeeCache.class);

		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();

		SessionFactory sf = cfg.buildSessionFactory(reg);
		/*First level cache is done with in same session
		 * if the query is same and it will run on same session it will fired one time and fetch and stored in first level cache 
		 * */
		Session session1 = sf.openSession();
		session1.beginTransaction();
		empCache=(EmployeeCache)session1.get(EmployeeCache.class, 1);
		System.out.println(empCache);
		empCache=(EmployeeCache)session1.get(EmployeeCache.class, 1);
		System.out.println(empCache);
		session1.getTransaction().commit();
		session1.close();
		
		/*if the session is different the data is seeking for second level cache
		 * steps to acheive SECOND LEVEL CACHE
		 * 1.Download EHCACHE jar net.sf.ehcache
		 * 2.Download hibernate-ehcache jar note: this version is equalent to Hiberate core jar version
		 * 3.Enable the second level cache in cfg file  <property name="hibernate.cache.use_second_level_cache">true</property>
		 * 4.provider detail should be given <property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
		 * 5.@Cacheable annotation must be given in the entity
		 * 6.@Cache(usage=CacheConcurrencyStrategy.READ_ONLY) annotation must be given
		 * */
		Session session2 = sf.openSession();
		session2.beginTransaction();
		empCache=(EmployeeCache)session2.get(EmployeeCache.class, 1);
		System.out.println(empCache);	
		session2.getTransaction().commit();
		session2.close();
	}

}
