package com.kousik.HibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Alien a1=new Alien();
        AlienName aname= new AlienName();
        aname.setAlienFname("Kousik");
        aname.setAlienMname("Daniel");
        aname.setAlienLname("K");
        
        a1.setAid(1);
        a1.setAname(aname);
        a1.setColor("Green");
        
        Configuration cfg = new Configuration().configure().addAnnotatedClass(Alien.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
        SessionFactory sf = cfg.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction	txn = session.beginTransaction();
        session.save(a1);
        //a2=(Alien)session.get(Alien.class, 002);
        //System.out.println(a2);
        txn.commit();
        
    }
}
