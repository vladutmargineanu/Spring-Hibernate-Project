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
	 * @Before("execution(public void add*())" ) public void
	 * beforeAddAccountAdvice() {
	 * 
	 * System.out.println("\n======>>> Executing @Before advice on addAccount()"); }
	 */

	// match method with based on Return Type

	/*
	 * @Before("execution(void add*())" ) public void beforeAddAccountAdvice() {
	 * 
	 * System.out.println("\n======>>> Executing @Before advice on addAccount()"); }
	 */

	// match method with ANY Return Type - use wildcards

	/*
	 * @Before("execution(* add*())") public void beforeAddAccountAdvice() {
	 * 
	 * System.out.println("\n======>>> Executing @Before advice on addAccount()"); }
	 */
	
	// Match method with "Account" Parameter Type (for the add* method)
	
	/*
	 * @Before("execution(* add*(com.luv2code.aopdemo.Account))") public void
	 * beforeAddAccountAdvice() {
	 * 
	 * System.out.println("\n======>>> Executing @Before advice on addAccount()"); }
	 */
	
	// Match method with "Account" Param and more Param types
	// (..) => Match on any number of arguments
	
	/*
	 * @Before("execution(* add*(com.luv2code.aopdemo.Account, ..))") public void
	 * beforeAddAccountAdvice() {
	 * 
	 * System.out.println("\n======>>> Executing @Before advice on addAccount()"); }
	 */
	
	// Match method with ANY parameters
	
	/*
	 * @Before("execution(* add*(..))") public void beforeAddAccountAdvice() {
	 * 
	 * System.out.println("\n======>>> Executing @Before advice on addAccount()"); }
	 */
	
	// Match methods in a given Package
	
	// 1. * return type
	// 2. com.luv2code.aopdemo.dao -> the package
	// 3. .*.* -> the class and the method
	// 4. (..) -> params
		
	@Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	public void beforeAddAccountAdvice() {

		System.out.println("\n======>>> Executing @Before advice on addAccount()");
	}

}
