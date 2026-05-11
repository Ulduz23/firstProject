package com.abbtech.productservice.common.aspect;

import com.abbtech.productservice.common.annotation.CustomTransactionAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
@Order(2)
public class TransactionManagementAspect {

    @Pointcut("@annotation(com.abbtech.productservice.common.annotation.CustomTransactionAnnotation)")
    public void transactionManagementPointcut() {
    }

    @Pointcut("execution(* com.abbtech.productservice..*.*(..))")
    public void allControllerMethodExecutionPointcut() {
    }

    @Before("transactionManagementPointcut()")
    public void beforeTransactionManagement(JoinPoint joinPoint) {
        Method method = extractMethod(joinPoint);
        CustomTransactionAnnotation annotation = method.getAnnotation(CustomTransactionAnnotation.class);
        log.info("Create JDBC connection for {}.{}()", method.getDeclaringClass().getSimpleName(), method.getName());
        log.info("Start transaction. readOnly={}", annotation != null && annotation.readOnlyTrue());
    }

    @After("transactionManagementPointcut()")
    public void afterTransactionManagement(JoinPoint joinPoint) {
        Method method = extractMethod(joinPoint);
        log.info("Close transaction resources for {}.{}()", method.getDeclaringClass().getSimpleName(), method.getName());
    }

    @AfterReturning(value = "transactionManagementPointcut()", returning = "result")
    public void afterReturningTransactionManagement(JoinPoint joinPoint, Object result) {
        Method method = extractMethod(joinPoint);
        log.info("Commit transaction for {}.{}(), returnType={}, returnValue={}",
                method.getDeclaringClass().getSimpleName(),
                method.getName(),
                method.getReturnType().getSimpleName(),
                result);
    }

    @AfterThrowing(value = "transactionManagementPointcut()", throwing = "exception")
    public void afterThrowingTransactionManagement(JoinPoint joinPoint, Throwable exception) {
        Method method = extractMethod(joinPoint);
        log.error("Rollback transaction for {}.{}() because of {}",
                method.getDeclaringClass().getSimpleName(),
                method.getName(),
                exception.getMessage(),
                exception);
    }


    @Around("transactionManagementPointcut()")
    public Object aroundTransactionManagement(ProceedingJoinPoint joinPoint) throws Throwable {
        var methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        CustomTransactionAnnotation annotation = method.getAnnotation(CustomTransactionAnnotation.class);
        log.debug("Transaction aspect entered: method={}, readOnly={}, args={}",
                method.getName(),
                annotation != null && annotation.readOnlyTrue(),
                Arrays.toString(joinPoint.getArgs()));
        return joinPoint.proceed();
    }

    private Method extractMethod(JoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod();
    }
}
