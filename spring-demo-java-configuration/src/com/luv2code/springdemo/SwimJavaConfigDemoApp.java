package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SwimJavaConfigDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(SportConfig.class);
		
		// get the bean - explicit component name - from spring container
		SwimCoach swimCoach = context.getBean("swimCoach", SwimCoach.class);

		// call a method on the bean
		System.out.println(swimCoach.getDailyWorkout());

		// call method to get the daily fortune
		System.out.println(swimCoach.getDailyFortune());
		
		// call our new swim coach methods ... has the props values injected
		System.out.println("email: " + swimCoach.getEmail());
		System.out.println("team: " + swimCoach.getTeam());

		// close the context
		context.close();
	}

}
