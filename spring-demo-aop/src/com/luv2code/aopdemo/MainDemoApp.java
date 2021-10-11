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
		
		// call the business method with one param
		Account myAccount = new Account();
		theAccountDAO.addAccount(myAccount);
		
		// call the business method with two param
		theAccountDAO.addAccount(myAccount, true);
		theAccountDAO.doWork();
		
		// call the membership business method
		theMembershipDAO.addSillyMember();
		
		// call the membership business method
		theMembershipDAO.addSillyMember1();
		theMembershipDAO.goToSleep();
		
		// call the business method again
 		theAccountDAO.addAccount();

		// close the context
		context.close();
	}
}
