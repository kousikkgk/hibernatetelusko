package com.kousik.HibernateObjectStates;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.kousik.HibernateHQL.Student;

public class PersistetLifeCycleExample {

	public static void main(String[] args) {
		Configuration cfg = new Configuration()
							.configure()
							.addAnnotatedClass(Student.class);

		ServiceRegistry reg = new ServiceRegistryBuilder()
							.applySettings(cfg.getProperties())
							.buildServiceRegistry();

		SessionFactory sf = cfg.buildSessionFactory(reg);
		Session session=sf.openSession();
		session.beginTransaction();
		
		//1.Transient State	
		Student s1 = new Student();
		s1.setRollNo(101);
		s1.setName("Kousik");
		s1.setMarks(99);
		
		
		session.save(s1);
		s1.setMarks(95);
	
		//2.Persistent State
		session.getTransaction().commit();
		
		
		session.close();
		
	}

}
