package com.kousik.HibernateHQL;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HQLExample1 {

	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure()
				.addAnnotatedClass(Student.class);

		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();

		SessionFactory sf = cfg.buildSessionFactory(reg);
		
		Session session=sf.openSession();
		session.beginTransaction();
		
		/*1. Fetching all data from table
		Query q1 = session.createQuery("from Student");
		List<Student> result = q1.list();
		
		for(Student st:result) {
			System.out.println(st);
		}*/
		
		/*2. Query with where condition
		Query q2 = session.createQuery("from Student where marks > 95");
		List<Student> toppers = q2.list();
		for(Student top:toppers) {
			System.out.println(top);
		}*/
		
		//3. Query with unique result
		Query q3 = session.createQuery("from Student where rollNo =10");
		Student student = (Student)q3.uniqueResult();
		System.out.println(student);
		
//		Random r = new Random();
//		
//		for(int i=1;i<=100;i++) {
//			
//			Student st = new Student();
//			st.setName("NAME"+i);
//			st.setMarks(r.nextInt(100));
//			session.save(st);
//		}
//		
		session.getTransaction().commit();
		session.close();
	}

}
