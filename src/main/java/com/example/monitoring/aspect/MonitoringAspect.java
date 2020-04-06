package com.example.monitoring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Aspect
@Service
public class MonitoringAspect {

    Logger logger = LoggerFactory.getLogger(MonitoringAspect.class);

    @Around("execution(* com.example.monitoring.repository..*(..)))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        //Log method execution time
        logger.info("Execution time of "
                + className + "."
                + methodName + " "
                + ": "
                + stopWatch.getTotalTimeMillis()
                + " ms");

        return result;
    }

}
