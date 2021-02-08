package com.kousik.HibernateMapping;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class OneToManyExample {

	public static void main(String[] args) {
		Laptop laptop = new Laptop();
		laptop.setlId(101);
		laptop.setlName("dell");
		
		/*Laptop l2 = new Laptop();
		l2.setlId(102);
		l2.setlName("HP");
		
		Laptop l3 = new Laptop();
		l3.setlId(103);
		l3.setlName("Mac");*/
		
		Student s1 = new Student();
		s1.setRollNo(111);
		s1.setName("KK");
		s1.setMarks(95.0);
//		s1.setLaptop(l1);
		s1.getLaptop().add(laptop);
		laptop.getStudent().add(s1);
	//	s1.getLaptop().add(l2);
		
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
		
		session.save(laptop);
		//session.save(l2);
		session.save(s1);
		txn.commit();
	}

}
