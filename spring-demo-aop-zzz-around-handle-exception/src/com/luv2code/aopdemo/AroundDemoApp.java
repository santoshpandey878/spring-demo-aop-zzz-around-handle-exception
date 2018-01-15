package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.service.TrafficFortuneService;

public class AroundDemoApp {

	public static void main(String[] args) {
		
		//read spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the bean from spring container
		TrafficFortuneService theTrafficFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		System.out.println("\nMain Program : AroundDemoApp");
		
		System.out.println("\ncalling getFortune");
		
		String data = theTrafficFortuneService.getFortune();
		
		System.out.println("\nMain Program : My fortune is : "+data);
		
		System.out.println("\nMain Program : finished");
		
		//close the context
		context.close();

	}

}
