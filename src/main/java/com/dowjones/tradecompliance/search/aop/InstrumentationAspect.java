package com.dowjones.tradecompliance.search.aop;

import org.springframework.stereotype.Component;

@Component
//@Aspect
public class InstrumentationAspect {
	/*private static Logger logger = LogManager.getLogger(InstrumentationAspect.class);
	
	@Pointcut("@annotation(com.dowjones.rs.search.aop.EnableInstrumentation)")
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
            logger.info("Method: " + method + " - Time taken: " + sw.getTime() + " ms");
        }
	}*/

}
