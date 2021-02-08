package com.kousik.HibernateManyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class ManyToManyEx {
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure().addAnnotatedClass(Delegate.class)
				.addAnnotatedClass(Event.class);

		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();

		SessionFactory sf = cfg.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction txn = session.beginTransaction();

		Delegate d1 = new Delegate();
		d1.setDelegateName("AA");
		
		Delegate d2 = new Delegate();
		d2.setDelegateName("BB");
		
		Delegate d3 = new Delegate();
		d3.setDelegateName("CC");
		
		Delegate d4 = new Delegate();
		d4.setDelegateName("DD");
		
		Event e1 = new Event();
		e1.setEventName("Java");
		Event e2 = new Event();
		e2.setEventName("C++");
		Event e3 = new Event();
		e3.setEventName("Python");
		
		
		e1.getDelegate().add(d1);
		e1.getDelegate().add(d2);
		e1.getDelegate().add(d3);
		
		e2.getDelegate().add(d1);
		e2.getDelegate().add(d2);
		
		e3.getDelegate().add(d4);
		
		session.save(d1);
		session.save(d2);
		session.save(d3);
		session.save(d4);
		
		session.save(e1);
		session.save(e2);
		session.save(e3);
		txn.commit();
	}
}
