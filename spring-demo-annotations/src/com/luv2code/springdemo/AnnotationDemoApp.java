package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

	public static void main(String[] args) {
		
		// read spring config file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// get the bean - explicit component name - from spring container
		Coach theCoach = context.getBean("thatSillyCoach", Coach.class);
		
		// get the bean - default component name (default bean id) - from spring container
		Coach alphaCoach = context.getBean("footballCoach", Coach.class);
		
		// call a method on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		System.out.println(alphaCoach.getDailyWorkout());
		
		// close the context
		context.close();

	}

}
