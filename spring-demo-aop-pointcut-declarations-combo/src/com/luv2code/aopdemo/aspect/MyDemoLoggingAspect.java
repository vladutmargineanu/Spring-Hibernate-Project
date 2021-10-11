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
	
	// create pointcut for getter methods
	
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
	private void getterDao() {}
	
	// create pointcut for setter methods
	
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
	private void setterDao() {}
	
	// create point: include package, but exclude getter/setter
	
	@Pointcut("forDaoPackage() && !(getterDao() || setterDao())")
	private void forDaoPackageNoGetterSetter() {}
	
	// Apply pointcut declaration to advice
	
	@Before("forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice() {

		System.out.println("\n======>>> Executing @Before advice on method()");
	}
	
	// Reuse the pointcut declaration 
	
	@Before("forDaoPackageNoGetterSetter()")
	public void performApiAnalytics() {
		
		System.out.println("\n======>>> Performing API analytics");
		s
	}
}
