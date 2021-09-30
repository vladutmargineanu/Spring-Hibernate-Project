package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		// use the session object to save Java object
		try {

			// create the objects

			Instructor tempInstructor = new Instructor("Vladut", "Margineanu", "vladut.margineanu@gmail.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail(
					"https://www.youtube.com/channel/UCxaSSwE5lC8ra5G3CdRTZCA", "Sleep all the time!");

			Instructor tempInstructor2 = new Instructor("Dragos", "Sandulescu", "dragos.sandulescu97@gmail.com");

			InstructorDetail tempInstructorDetail2 = new InstructorDetail("https://www.youtube.com/channel",
					"Eat all the time!");

			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			tempInstructor2.setInstructorDetail(tempInstructorDetail2);

			// start a transaction
			session.beginTransaction();

			// save the instructor

			/*
			 * Note: this will ALSO save the details object because of CascadeType.ALL -
			 * save teh actual object and any associated objects
			 */

			System.out.println("Saving instructor 1: " + tempInstructor);
			System.out.println("Saving instructor 2: " + tempInstructor2);

			session.save(tempInstructor);
			session.save(tempInstructor2);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}
}
