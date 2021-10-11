package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	/*
	 * this is where we add all of our related advises for Logging let's start with
	 * an @Before advice
	 */

	// it is applied for all classes which have addAccount() method

	/*
	 * @Before("execution(public void addAccount())") public void
	 * beforeAddAccountAdvice() {
	 * 
	 * System.out.println("\n======>>> Executing @Before advice on addAccount()"); }
	 */

	// it is applied just for AccounDAO class

	/*
	 * @Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())"
	 * ) public void beforeAddAccountAdvice() {
	 * 
	 * System.out.println("\n======>>> Executing @Before advice on addAccount()"); }
	 */

	// match method starting with "add" in any class

	/*
	 * @Before("execution(public void add*t())" ) public void
	 * beforeAddAccountAdvice() {
	 * 
	 * System.out.println("\n======>>> Executing @Before advice on addAccount()"); }
	 */

	// match method with based on Return Type

	/*
	 * @Before("execution(void add*t())" ) public void beforeAddAccountAdvice() {
	 * 
	 * System.out.println("\n======>>> Executing @Before advice on addAccount()"); }
	 */

	// match method with ANY Return Type - use wildcards

	@Before("execution(* add*())")
	public void beforeAddAccountAdvice() {

		System.out.println("\n======>>> Executing @Before advice on addAccount()");
	}

}
