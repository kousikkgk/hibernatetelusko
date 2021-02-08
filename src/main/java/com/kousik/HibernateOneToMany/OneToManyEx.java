package com.kousik.HibernateOneToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class OneToManyEx {

	public static void main(String[] args) {
		Configuration cfg = new Configuration()
							.configure()
							.addAnnotatedClass(College.class)
							.addAnnotatedClass(Students.class);
		
		ServiceRegistry reg = new ServiceRegistryBuilder()
							.applySettings(cfg.getProperties())
							.buildServiceRegistry();
		
		SessionFactory sf = cfg.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction txn = session.beginTransaction();
		
		College col1= new College();
		col1.setCollegeName("ANJAC");
		
		College col2 = new College();
		col2.setCollegeName("SSN");
		
		Students st1 = new Students();
		st1.setStudentName("KK");
		
		Students st2 = new Students();
		st2.setStudentName("Bala");
		
		Students st3 = new Students();
		st3.setStudentName("Daniel");
		
		st1.setCollege(col1);
		st2.setCollege(col1);
		st3.setCollege(col2);
		
		session.save(col1);
		session.save(col2);
		
		session.save(st1);
		session.save(st2);
		session.save(st3);
		
		System.out.println(session.get(College.class, 1));
		txn.commit();
	}

}
