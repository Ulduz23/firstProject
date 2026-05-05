package com.abbtech.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
@Order(1)
public class LoggingAspect {

    @Pointcut("execution(public * com.abbtech.controller..*.*(..))")
    public void controllerLayerPointcut() {
    }

    @Pointcut("execution(public * com.abbtech.service..*.*(..))")
    public void serviceLayerPointcut() {
    }

    @Pointcut("controllerLayerPointcut() || serviceLayerPointcut()")
    public void applicationLayerPointcut() {
    }

    @Around("applicationLayerPointcut()")
    public Object logExecution( org.aspectj.lang.ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();
        long start = System.currentTimeMillis();

        log.info("Entering {}.{} with args={}", className, methodName, Arrays.toString(joinPoint.getArgs()));
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;
            log.info("Exiting {}.{} with result={} in {} ms", className, methodName, result, duration);
            return result;
        } catch (Throwable exception) {
            long duration = System.currentTimeMillis() - start;
            log.error("Exception in {}.{} after {} ms: {}", className, methodName, duration, exception.getMessage(), exception);
            throw exception;
        }
    }

    @AfterThrowing(value = "applicationLayerPointcut()", throwing = "exception")
    public void logFailure(JoinPoint joinPoint, Throwable exception) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.warn("Failure observed in {}.{}(): {}",
                signature.getDeclaringType().getSimpleName(),
                signature.getName(),
                exception.getClass().getSimpleName());
    }
}
