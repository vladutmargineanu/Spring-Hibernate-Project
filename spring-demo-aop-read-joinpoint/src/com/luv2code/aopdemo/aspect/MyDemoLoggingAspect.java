package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	/*
	 * this is where we add all of our related advises for Logging let's start with
	 * an @Before advice
	 */

	// Apply pointcut declaration to advice

	@Before("com.luv2code.aopdemo.aspect.PointcutDeclarationsAop.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {

		System.out.println("\n======>>> Executing @Before advice on method()");

		// display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();

		System.out.println("Method: " + methodSig);

		// display method arguments

		// get args
		Object[] methodArgs = theJoinPoint.getArgs();

		// loop thru args
		for (Object tempArg : methodArgs) {
			
			System.out.println(tempArg);
			
			if (tempArg instanceof Account) {
				
				// downcast and print Account specific stuff
				Account theAccount = (Account) tempArg;
				
				System.out.println("Account name: " + theAccount.getName());
				System.out.println("Account name: " + theAccount.getLevel());
			}
		}
		
	}
}
