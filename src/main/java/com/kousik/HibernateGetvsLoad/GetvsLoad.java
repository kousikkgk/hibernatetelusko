package com.kousik.HibernateGetvsLoad;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.kousik.HibernateHQL.Student;

public class GetvsLoad {
	public static void main(String[] args) throws InterruptedException {
		
		Configuration cfg = new Configuration().
							configure()
							.addAnnotatedClass(Student.class);

		ServiceRegistry reg = new ServiceRegistryBuilder()
							.applySettings(cfg.getProperties())
							.buildServiceRegistry();

		SessionFactory sf = cfg.buildSessionFactory(reg);
		Session session = sf.openSession();
		session.beginTransaction();
		
		/***************DIFFERENCE# 1------************/
		
		//1.GET will hit the database each time, if not printing the object
		Student s1=(Student) session.get(Student.class, 12);
		System.out.println(s1);
		
		//2.LOAD will hit the database when we try to printing the object
		// it will give proxy of the object
		Student s2=(Student) session.load(Student.class,12);
		Thread.sleep(5000);
		System.out.println(s2);
		/**********************************************/
		
		/***************DIFFERENCE# 2------************/
		
		//1.GET will not throw null pointer exception when the data is not present in the database
		// it will give null value
		Student s3=(Student) session.get(Student.class, 112);
		System.out.println(s3);
		
		//2.LOAD will throw  org.hibernate.ObjectNotFoundException 	when the data is not present in the database
		Student s4=(Student) session.load(Student.class,112);
		Thread.sleep(5000);
		System.out.println(s4);
		/**********************************************/
		session.getTransaction().commit();
		session.close();
	}
}
