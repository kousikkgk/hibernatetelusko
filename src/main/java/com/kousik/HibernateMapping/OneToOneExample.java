package com.kousik.HibernateMapping;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;



public class OneToOneExample {

	public static void main(String[] args) {
		
		Laptop l1 = new Laptop();
		l1.setlId(101);
		l1.setlName("dell");
		
		Student s1 = new Student();
		s1.setRollNo(111);
		s1.setName("KK");
		s1.setMarks(95.0);
		//s1.setLaptop(l1);
		
		Configuration cfg = new Configuration().
							configure().
							addAnnotatedClass(Laptop.class).
							addAnnotatedClass(Student.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().
							applySettings(cfg.getProperties()).
							buildServiceRegistry();
		SessionFactory sf = cfg.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction txn	= session.beginTransaction();
		session.save(s1);
		session.save(l1);
		txn.commit();
	}

}
