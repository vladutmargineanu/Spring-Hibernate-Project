package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	/*
	 * this is where we add all of our related advises for Logging let's start with
	 * an @Before advice
	 */
	
	// Create pointcut declaration

	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	// Apply pointcut declaration to advice
	
	@Before("forDaoPackage()")
	public void beforeAddAccountAdvice() {

		System.out.println("\n======>>> Executing @Before advice on method()");
	}
	
	// Reuse the pointcut declaration 
	
	@Before("forDaoPackage()")
	public void performApiAnalytics() {
		
		System.out.println("\n======>>> Performing API analytics");
		
	}

}
