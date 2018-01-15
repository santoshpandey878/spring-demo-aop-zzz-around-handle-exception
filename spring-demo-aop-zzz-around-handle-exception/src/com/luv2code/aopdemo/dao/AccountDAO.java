package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {

	private String name;
	private String serviceCode;
	
	//add a new method findAccounts
	public List<Account> findAccounts(boolean tripWire){
		
		//simulate exception
		if(tripWire){
			throw new RuntimeException("no soup for you!");
		}
		List<Account> myAccounts = new ArrayList<>();
		
		//create sample accounts
		Account acc1 = new Account("John", "Silver");
		Account acc2 = new Account("Madhu", "Platinum");
		Account acc3 = new Account("Luca", "Gold");
		
		//add them to our account list
		myAccounts.add(acc1);
		myAccounts.add(acc2);
		myAccounts.add(acc3);		
		
		return myAccounts;
	}
	
	public void addAccount(Account theAccount, boolean vipFlag){
		System.out.println(getClass() + " :Doing my add account work");
	}
	
	public boolean doWork(){
		System.out.println(getClass() + " :dpWork()");
		return true;
	}

	public String getName() {
		System.out.println(getClass() + " :getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + " :setName()");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + " :getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + " :setServiceCode()");
		this.serviceCode = serviceCode;
	}
	
	
}
