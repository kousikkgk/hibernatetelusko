package com.kousik.HibernateJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAExample {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");
		EntityManager em = emf.createEntityManager();
		
		Student s1 = em.find(Student.class, 11);
		System.out.println(s1);
	}

}
