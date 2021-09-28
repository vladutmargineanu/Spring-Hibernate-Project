package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		// use the session object to save Java object
		try {

			// create 3 student objects
			System.out.println("Creating 3 student objects ...");
			Student tempStudent01 = new Student("John", "Doe", "john@luv2code.com");
			Student tempStudent02 = new Student("Mary", "Public", "mary@luv2code.com");
			Student tempStudent03 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

			// start a transaction
			session.beginTransaction();

			// save the student object
			System.out.println("Saving the students ...");
			session.save(tempStudent01);
			session.save(tempStudent02);
			session.save(tempStudent03);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {
			factory.close();
		}

	}

}
