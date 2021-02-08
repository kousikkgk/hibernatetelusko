package com.kousik.HibernateHQL;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HQLExample2 {
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
		
		/*---------------------*/
		Query q1 = session.createQuery("select rollNo,name,marks from Student where rollno=7");
		Object[] student = (Object[])q1.uniqueResult();
		System.out.println(student[0]+"||"+student[1]+"||"+student[2]);
		
		/*---------------------*/
		Query q2 = session.createQuery("select rollNo,name,marks from Student");
		List<Object[]> students = (List<Object[]>)q2.list();
		
		for(Object[] st:students) {
			System.out.println(st[0]+"||"+st[1]+"||"+st[2]);
		}
		
		/*---------------------*/
		Query q3 = session.createQuery("select rollNo,name,marks from Student s where s.marks>60");
		List<Object[]> stu = (List<Object[]>)q3.list();
		
		for(Object[] st1:stu) {
			System.out.println(st1[0]+"||"+st1[1]+"||"+st1[2]);
		}
		
		/*---------------------*/
		Query q4 = session.createQuery("select sum(marks) from Student");
		Long sum = (Long)q4.uniqueResult();
		
		System.out.println(sum);
		
		/*---------------------*/
		int b=10;
		/*---------------------*/
		Query q5 = session.createQuery("select sum(marks) from Student s where s.marks> :b");
		q5.setParameter("b", b);
		Long sum1 = (Long)q5.uniqueResult();
		
		System.out.println(sum1);
		
		session.getTransaction().commit();
		session.close();
	}

}
