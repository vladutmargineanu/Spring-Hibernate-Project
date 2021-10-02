package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .addAnnotatedClass(Course.class)
								 .addAnnotatedClass(Review.class)
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		// use the session object to save Java object
		try {

			// start a transaction
			session.beginTransaction();
			
			// create a course
			Course tempCourse = new Course("Pacman - How To Score One Million Points");
			
			// save the course
			System.out.println("\nSaving the course" + tempCourse);
			session.save(tempCourse);
			System.out.println("Saved the course: " + tempCourse);
			
			// create the students
			Student tempStudent1 = new Student("Vladut", "Margineanu", "vladut.margineanu@gmail.com");
			Student tempStudent2 = new Student("Dragos", "Sandulescu", "dragos.sandulescu97@gmail.com");

			// add students to the course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			// save the students
			System.out.println("\nSaving students: " + tempCourse.getStudents());
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("\nSaved students: " + tempCourse.getStudents());

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {
			// add clean up code
			session.close();
			factory.close();
		}
	}
}