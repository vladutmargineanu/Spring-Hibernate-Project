package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationsBeanScopeDemoApp {

	public static void main(String[] args) {
		
		// load spring config file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// retrieve bean from spring container
		Coach footballCoach01 = context.getBean("footballCoach", Coach.class);
		
		Coach footballCoach02 = context.getBean("footballCoach", Coach.class);
		
		Coach hockeyCoach = context.getBean("hockeyCoach", Coach.class);
		
		// check if they are the same
		boolean result = (footballCoach01 == footballCoach02);
		
		// print out the result - default scope is singleton
		System.out.println("\nPointing to the same object: " + result);
		
		System.out.println("\nMemory location for footballCoach01: " + footballCoach01);
		
		System.out.println("\nMemory location for footballCoach02: " + footballCoach02);
		
		System.out.println("\nMemory location for alphaCoach: " + hockeyCoach);


		// close the context
		context.close();

	}

}
