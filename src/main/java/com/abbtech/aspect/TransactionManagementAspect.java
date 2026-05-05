package com.abbtech.aspect;

import com.abbtech.annotation.CustomTransactionAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class TransactionManagementAspect {

    @Pointcut("@annotation(com.abbtech.annotation.CustomTransactionAnnotation)")
    public void transactionManagementPointcut() {
    }

    @Pointcut("execution(* com.abbtech.controller..*.*(..))")
    public void allControllerMethodExecutionPointcut() {
    }


    @AfterThrowing(value = "transactionManagementPointcut()", throwing = "exception")
    public void afterThrowingTransactionManagement(JoinPoint joinPoint, Throwable exception) throws Throwable {
        System.out.println("Rollback transaction");
    }


    @Around("transactionManagementPointcut()")
    public Object aroundTransactionManagement(ProceedingJoinPoint joinPoint) throws Throwable {


        // Get method
        var methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        System.out.println("Method name: " + method.getName());

        // Annotation
        CustomTransactionAnnotation annotation = method.getAnnotation(CustomTransactionAnnotation.class);

        if (annotation != null) {
            System.out.println("Annotation read only value: " + annotation.readOnlyTrue());
        }

        // Parameters (types + values)
        Object[] args = joinPoint.getArgs();
        Class<?>[] paramTypes = method.getParameterTypes();
        String[] paramNames = methodSignature.getParameterNames();

        for (int i = 0; i < args.length; i++) {
            System.out.println("Parameter name: " + paramNames[i]);
            System.out.println("Parameter type: " + paramTypes[i]);
            System.out.println("Parameter value: " + args[i]);
        }

        // Transaction start
        System.out.println("Create JDBC connection");
        System.out.println("Start transaction");

        Object result;
        try {
            result = joinPoint.proceed(); // execute method

            // Return type + value
            System.out.println("Return type: " + method.getReturnType());
            System.out.println("Return value: " + result);

            System.out.println("Commit transaction");
        } catch (Throwable ex) {
            System.out.println("Rollback transaction");
            throw ex;
        }

        return result;
    }

}
