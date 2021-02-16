package com.kousik.HibernateHQL;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;



public class HQLExample3 {
	public static void main(String[] args) {
		Configuration cfg = new Configuration()
							.configure()
							.addAnnotatedClass(Student.class);
		
		ServiceRegistry reg = new ServiceRegistryBuilder()
							.applySettings(cfg.getProperties())
							.buildServiceRegistry();

		SessionFactory sf = cfg.buildSessionFactory(reg);

		Session session = sf.openSession();
		session.beginTransaction();
		
		//1.Fetch all columns from the table
		SQLQuery query = session.createSQLQuery("select * from student where marks > 60");
		query.addEntity(Student.class);
		List students=query.list();
		
		for(Object student:students) {
//			System.out.println(student);
		}
		
		//2.Fetch particular column and by map interface to get the values
		SQLQuery query1 = session.createSQLQuery("select name,marks from student where marks > 60");
		query1.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List students1=query1.list();
		
		for(Object o:students1) {
			Map m = (Map)o;
			System.out.println(m.get("name")+":"+m.get("marks"));
		}
		session.getTransaction().commit();
		session.close();
	}
	
}
