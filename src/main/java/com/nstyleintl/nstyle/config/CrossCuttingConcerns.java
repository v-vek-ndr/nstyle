package com.nstyleintl.nstyle.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CrossCuttingConcerns {
	
	@Before("execution(* com.nstyleintl.nstyle.controller.*.*(..))")
	public void logBeforeControllerMethods() {
		System.out.println("Before Controller");
	}
	
	@AfterReturning(pointcut = "execution(* com.nstyleintl.nstyle.controller.*.*(..))", 
			returning = "result")
	public void logAfterControllerREturning(Object result) {
		System.out.println("After Controller " + result);
	}
	
	@AfterThrowing(pointcut = "execution(* com.nstyleintl.nstyle.controller.*.*(..))", 
			throwing = "exception")
	public void logAfterThrowingController(Exception exception) {
		System.out.println("Exception thrown " + exception);
	}
	
	@Around("execution(* com.nstyleintl.nstyle.controller.*.*(..))")
	public Object logAroundControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
		
		// Before starting of the method
		long start = System.currentTimeMillis();
		System.out.println("Entering Method");
		
		// Here Method will be executed
		Object proceed = joinPoint.proceed();
		
		// Before returning we will come here form the executing method and log this
		long executionTime = System.currentTimeMillis() - start;
		System.out.println(joinPoint.getSignature() + " executed in " + executionTime);
		
		// Again we go and return the return value from that method
		return proceed;
	}

}