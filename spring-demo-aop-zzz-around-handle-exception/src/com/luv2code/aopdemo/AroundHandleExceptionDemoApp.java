package com.luv2code.aopdemo;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.service.TrafficFortuneService;

public class AroundHandleExceptionDemoApp {

	private static Logger myLogger = Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());
	
	public static void main(String[] args) {
		
		//read spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the bean from spring container
		TrafficFortuneService theTrafficFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		myLogger.info("\nMain Program : AroundDemoApp");
		
		myLogger.info("\ncalling getFortune");
		
		boolean tripWire = true;
		String data = theTrafficFortuneService.getFortune(tripWire);
		
		myLogger.info("\nMain Program : My fortune is : "+data);
		
		myLogger.info("\nMain Program : finished");
		
		//close the context
		context.close();

	}

}
