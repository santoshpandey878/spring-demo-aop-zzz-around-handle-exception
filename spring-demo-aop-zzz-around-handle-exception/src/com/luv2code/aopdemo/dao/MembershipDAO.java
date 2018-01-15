package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {
	
	public void addAccount(){
		System.out.println(getClass() + " :Doing my membership dao work");
	}
   
	public boolean addSillyMember(){
		System.out.println(getClass() + " :Doing my add silly member work");
		return true;
	}
	
	public void goToSleep(){
		System.out.println(getClass() + " :i am going to sleep");
	}
}
