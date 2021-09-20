package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

	public static void main(String[] args) {
		
		// read spring config file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// get the bean - explicit component name - from spring container
		Coach tennisCoach = context.getBean("thatSillyCoach", Coach.class);
		
		// get the bean - default component name (default bean id) - from spring container
		Coach footballCoach = context.getBean("footballCoach", Coach.class);
		
		// get the bean - default component name (default bean id) - from spring container
		Coach hockeyCoach = context.getBean("hockeyCoach", Coach.class);
		
		// get the bean from spring container
		SwimCoach swimCoach = context.getBean("swimCoach", SwimCoach.class);

		// call a method on the bean
		System.out.println(tennisCoach.getDailyWorkout());
		
		System.out.println(footballCoach.getDailyWorkout());
		
		System.out.println(hockeyCoach.getDailyWorkout());
		
		System.out.println(swimCoach.getDailyWorkout());


		// call method to get the daily fortune
		System.out.println(tennisCoach.getDailyFortune());
		
		System.out.println(hockeyCoach.getDailyFortune());
		
		System.out.println(swimCoach.getDailyFortune());
		
		// call our new swim coach methods ... has the props values injected
		System.out.println("email: " + swimCoach.getEmail());
		System.out.println("team: " + swimCoach.getTeam());

		// close the context
		context.close();
	}

}
