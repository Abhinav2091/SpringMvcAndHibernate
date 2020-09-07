package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// set up logger

	private static Logger logger = Logger.getLogger("CRMLoggingAspect.class");

	// set up point cut declaration
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	public void forControllerPackage() {
	}

	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	public void forServicePackage() {
	}

	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	public void forDaoPackage() {
	}

	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void appFlow() {
	}

	// add @Before advice
	@Before("appFlow()")
	public void beforeAdviceCall(JoinPoint joinPoint) {
		// display the method
		logger.info("******************IN Before call method::" + joinPoint.getSignature().toString());

		// display the argument we calling
		Object[] arguments = joinPoint.getArgs();
		for (Object arg : arguments) {
			logger.info("******************" + arg);
		}

	}

	// add @AfterReturing advice
	@AfterReturning(pointcut = "appFlow()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		// display the method
		logger.info(joinPoint.getSignature().toString() + "******************IN after Returning call method::");

		// display the data return
		logger.info(result + "******************");

	}
}
