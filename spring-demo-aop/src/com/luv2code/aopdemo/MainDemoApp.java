package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get the bean spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// get membership bean from spring container
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

		// call the business method
		theAccountDAO.addAccount();
		
		// call the membership business method
		theMembershipDAO.addSillyMember();
		
		// call the membership business method
		theMembershipDAO.addSillyMember1();
		
		// call the business method again
		System.out.println("\n Let's call it again!\n");
		theAccountDAO.addAccount();

		// close the context
		context.close();
	}
}
