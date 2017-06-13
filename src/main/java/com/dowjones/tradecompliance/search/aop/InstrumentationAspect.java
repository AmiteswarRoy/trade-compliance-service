package com.dowjones.tradecompliance.search.aop;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class InstrumentationAspect {
	private static Logger logger = LogManager.getLogger(InstrumentationAspect.class);
	
	@Pointcut("@annotation(com.dowjones.tradecompliance.search.aop.EnableInstrumentation)")
	private void callInstrumentation() {}
	
	@Around("callInstrumentation())")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch sw = new StopWatch();
        String method = joinPoint.getSignature().getDeclaringTypeName() + "." 
        		+ joinPoint.getSignature().getName() + "()";
        
        sw.start();
        try
        {
            return joinPoint.proceed();
        }
        finally
        {
            sw.stop();
            logger.debug("Method: " + method + " - Time taken: " + sw.getTime() + " ms");
        }
	}

}
