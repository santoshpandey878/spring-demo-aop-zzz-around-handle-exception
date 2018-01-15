package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		
		//read spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// get menbership bean from spring container
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		//call the business method
		Account theAccount = new Account();
		theAccount.setName("Madhu");
		theAccount.setLevel("Gold");
		theAccountDAO.addAccount(theAccount, true);
		
		//call the membership business method
		theMembershipDAO.addAccount();
		
		//call the membership business method
		//it is for wild card
		theMembershipDAO.addSillyMember();
		
		//do it again
		//System.out.println("\n let's call again");
		
		//call the business method again
		//theAccountDAO.addAccount();
		
		//call new added method
		theAccountDAO.doWork();
		theMembershipDAO.goToSleep();
		
		// call the accountDAO getter/setter methods
		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("silver");
		
		String name = theAccountDAO.getName();
		String code = theAccountDAO.getServiceCode();
		
		//close the context
		context.close();

	}

}
