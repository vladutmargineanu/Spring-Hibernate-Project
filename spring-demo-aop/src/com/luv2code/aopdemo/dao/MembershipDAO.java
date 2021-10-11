package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public void addAccount() {
		System.out.println(getClass() + ": Doing stuff: adding a membership account");
	}
	
	public void addSillyMember() {
		System.out.println(getClass() + ": Doing stuff: adding a membership account");
	}
	
	public boolean addSillyMember1() {
		System.out.println(getClass() + ": Doing stuff: adding a membership account");
		
		return true;
	}
}
