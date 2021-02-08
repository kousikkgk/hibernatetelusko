package com.kousik.HibernateOneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class OneToOneEx {

	public static void main(String[] args) {
		
		Configuration cfg = new Configuration()
							.configure()
							.addAnnotatedClass(Person.class)
							.addAnnotatedClass(PersonDetail.class);
		
		ServiceRegistry reg = new ServiceRegistryBuilder()
							.applySettings(cfg.getProperties())
							.buildServiceRegistry();
		
		SessionFactory sf = cfg.buildSessionFactory(reg);
		
		Session session = sf.openSession();
		
		Transaction txn = session.beginTransaction();
		
		PersonDetail pDetail = new PersonDetail();
		pDetail.setAge(23);
		pDetail.setSex("M");
		
		Person p1 = new Person();
		p1.setName("Kousik");
		p1.setpDetail(pDetail);
		
		session.save(p1);
		//no need to save person detail because of cascadetype.ALL
//		session.save(s1);
//		session.save(l1);
		txn.commit();
	}

}
