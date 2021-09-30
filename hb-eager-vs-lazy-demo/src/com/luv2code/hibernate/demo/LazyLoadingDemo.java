package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class LazyLoadingDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		// use the session object to save Java object
		try {

			// start a transaction
			session.beginTransaction();
			
			// get the instructor from db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Vladut: Instructor: " + tempInstructor);

			// get course for the instructor - session is still open
			// option 1: call getter method while session is open => we can acess lazy data even if the session was closed
			
			System.out.println("Vladut: Courses: " + tempInstructor.getCourses());
			
			// commit transaction
			session.getTransaction().commit();
			
			// close the session - session is closed
			session.close();
			System.out.println("\nVladut: The session is now closed!\n");
			
			// get course for the instructor - load lazy data while the session is closed
			System.out.println("Vladut: Courses: " + tempInstructor.getCourses());
			
			

			System.out.println("Vladut: Done!");
		} finally {
			// add clean up code
			session.close();
			factory.close();
		}
	}
}
