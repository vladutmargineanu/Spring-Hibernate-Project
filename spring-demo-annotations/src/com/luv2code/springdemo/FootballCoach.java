package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FootballCoach implements Coach {

	private FortuneService fortuneService;
	
	// define default constructor
	public FootballCoach() {
		System.out.println(">> FootballCoach: inside default constructor");
	}
	
	// setter injection - define a setter method
	@Autowired
	@Qualifier("happyFortuneService")		// qualifier for dependency injection DI
	public void setFortuneService(FortuneService theFortuneService) {
		System.out.println(">> FootballCoach: inside setFortuneService() method");
		fortuneService = theFortuneService;
	}
	
	/*
	// method injection - define a method
	@Autowired
	public void doSomeCrazyStuff(FortuneService theFortuneService) {
		System.out.println(">> FootballCoach: inside doSomeCrazyStuff() method");
		fortuneService = theFortuneService;
	}
	*/

	@Override
	public String getDailyWorkout() {
		return "Practice football";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
