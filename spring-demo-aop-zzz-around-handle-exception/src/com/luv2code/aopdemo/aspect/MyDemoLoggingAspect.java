package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	//this is where we add all of our related advices for logging
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint theProceedingJointPoint) throws Throwable{
	
		String method = theProceedingJointPoint.getSignature().toString();
		myLogger.info("\n=======>>> Executing @Around on method : "+method);
		
		//get begin timestamp
		
		long begin = System.currentTimeMillis();
		
		//now let's execute the method
		Object result = null;
		 try {
			result = theProceedingJointPoint.proceed();
		} catch (Exception e) {
			
			//log the exception
			
			myLogger.warning(e.getMessage());
			
			//give user a custom message
			
			//result = "Major accident ! but no worries, your private helicopter is on the way!";
			
			throw e;
			
		}
		
		//get end timestamp
		
		long end = System.currentTimeMillis();
	
		//compute duration and print
		
		long duration = end-begin;
		myLogger.info("\n=======>>> Duration : "+duration/1000.0 + " seconds");
		
		return result;
		
	}
	
	@AfterThrowing(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="theExc"
			)
	public void afterThrowingFindAccountsAdvice(JoinPoint theJointPoint, Throwable theExc){
	
		String method = theJointPoint.getSignature().toString();
		myLogger.info("\n=======>>> Executing @AfterThrowing on method : "+method);
		
		//log the exception
		myLogger.info("\n=======>>> the exception is : "+theExc);
		
	}
	
	
	//add a new advice for @AfterReturning on the findAccounts method
	
	@AfterReturning(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result"
			)
	public void afterReturningFindAccountsAdvice(JoinPoint theJointPoint, List<Account> result){
		
		//print out which method we are advising on
		
		String method = theJointPoint.getSignature().toString();
		myLogger.info("\n=======>>> Executing @AfterReturning on method : "+method);
		
		//print out the result of the method call
		myLogger.info("\n=======>>> Result is : "+result);
		
		//let's post process the data
		
		//convert the account name to upper case
		convertAccountNamesToUpperCase(result);
		
		myLogger.info("\n=======>>> Result after modification is : "+result);
		
	}
		
		private void convertAccountNamesToUpperCase(List<Account> result) {
		
			//loop through the accounts
			
			for(Account tempAccount : result){
			
				//get upper case version of accounts
				
				String theUpperName = tempAccount.getName().toUpperCase();
				
				//update the anme of account
				tempAccount.setName(theUpperName);
			}
		
	}


		//let's start with an @Before advice

		@Before("com.luv2code.aopdemo.aspect.LovAopExpressions.forDaoPackageNoGetterSetter()")
		public void beforeAddAccountAdvice(JoinPoint theJoinPoint){
			myLogger.info("\n======>>> Executing @Before advice method");
			
			//display the method signature
			MethodSignature methodSign = (MethodSignature) theJoinPoint.getSignature();
			myLogger.info("\n Method signature: "+methodSign);
			
			//display the method args
			Object[] args = theJoinPoint.getArgs();
			
			//loop thr args
			for(Object tempArgs : args){
				myLogger.info(" Method Args: "+tempArgs);
				
				if(tempArgs instanceof Account){
					//downcast and print Account specific stuff
					Account theAccount = (Account) tempArgs;
					myLogger.info(" account name: "+theAccount.getName());
					myLogger.info(" account level: "+theAccount.getLevel());
				}
			}
		}
		
		
}
