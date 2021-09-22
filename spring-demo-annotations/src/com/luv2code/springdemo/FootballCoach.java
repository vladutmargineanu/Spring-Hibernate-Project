package com.luv2code.springdemo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")				// Prototype scope: new object for each request
public class FootballCoach implements Coach, DisposableBean {

	private FortuneService fortuneService;
	
	// define default constructor
	public FootballCoach() {
		System.out.println(">> FootballCoach: inside default constructor");
	}
	
	@PostConstruct						// after constructor
	public void doMyStartupStuff() {
		System.out.println(">> FootballCoach: inside doMyStartupStuff()");
	}
	
	// setter injection - define a setter method
	@Autowired
	@Qualifier("happyFortuneService")	// qualifier for dependency injection DI
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
	
	/*
	 * For "prototype" scoped beans, Spring does not call the @PreDestroy method.
	 * 
	 * 1. Create a custom bean processor. This bean processor will keep track of 
	 * 	  prototype scoped beans. During shutdown it will call the destroy() method 
	 *    on the prototype scoped beans.
	 * 
	 * 2. The prototype scoped beans MUST implement the DisposableBean interface.
	 *    This interface defines a "destroy()" method. This method should be used 
	 *    instead of the @PreDestroy annotation.
	 *  
	 *  3. In this app, AnnotationDemoApp.java is the main program. 
	 *     FootballCoach.java is the prototype scoped bean. FootballCoach implements 
	 *     the DisposableBean interface and provides the destroy() method. 
	 *     The custom bean processing is handled in the MyCustomBeanProcessor class.
	 *  
	 */
	
	@Override
	public void destroy() throws Exception {
		System.out.println(">> FootballCoach: inside destroy()");
		
	}
}
