package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class HockeyCoach implements Coach {
	
	// field injection - without any setter or method
	@Autowired
	@Qualifier("randomFortuneService")		// qualifier for dependency injection DI
	private FortuneService fortuneService;
	
	// define default constructor
	public HockeyCoach() {
		System.out.println(">> HockeyCoach: inside default constructor");
	}

	@Override
	public String getDailyWorkout() {
		return "Practice hockey";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
