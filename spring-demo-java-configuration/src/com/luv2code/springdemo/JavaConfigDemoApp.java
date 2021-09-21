package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(SportConfig.class);
		
		// get the bean - explicit component name - from spring container
		Coach tennisCoach = context.getBean("tennisCoach", Coach.class);

		// call a method on the bean
		System.out.println(tennisCoach.getDailyWorkout());

		// call method to get the daily fortune
		System.out.println(tennisCoach.getDailyFortune());

		// close the context
		context.close();
	}

}
