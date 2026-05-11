package com.abbtech.productservice.common.aspect;

import com.abbtech.productservice.common.annotation.full.FullValueAnnotation;
import com.abbtech.productservice.common.annotation.marked.MarkedAnnotation;
import com.abbtech.productservice.common.annotation.repeatable.RepeatableAnnotation;
import com.abbtech.productservice.common.annotation.singlevalue.SingleValueAnnotation;
import com.abbtech.productservice.common.annotation.typed.TypedAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
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
@Order(3)
public class AnnotationInspectionAspect {

    @Pointcut("@annotation(com.abbtech.productservice.common.annotation.marked.MarkedAnnotation) || " +
            "@annotation(com.abbtech.productservice.common.annotation.singlevalue.SingleValueAnnotation) || " +
            "@annotation(com.abbtech.productservice.common.annotation.full.FullValueAnnotation) || " +
            "@annotation(com.abbtech.productservice.common.annotation.typed.TypedAnnotation) || " +
            "@annotation(com.abbtech.productservice.common.annotation.repeatable.RepeatableAnnotation) || " +
            "@annotation(com.abbtech.productservice.common.annotation.repeatable.RepeatableAnnotations)")
    public void customAnnotationPointcut() {
    }

    @Before("customAnnotationPointcut()")
    public void inspectAnnotations(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        if (method.isAnnotationPresent(MarkedAnnotation.class)) {
            log.info("MarkedAnnotation detected on {}.{}()", method.getDeclaringClass().getSimpleName(), method.getName());
        }

        SingleValueAnnotation singleValueAnnotation = method.getAnnotation(SingleValueAnnotation.class);
        if (singleValueAnnotation != null) {
            log.info("SingleValueAnnotation value='{}' on {}.{}()",
                    singleValueAnnotation.value(),
                    method.getDeclaringClass().getSimpleName(),
                    method.getName());
        }

        FullValueAnnotation fullValueAnnotation = method.getAnnotation(FullValueAnnotation.class);
        if (fullValueAnnotation != null) {
            log.info("FullValueAnnotation order={}, name='{}', description='{}' on {}.{}()",
                    fullValueAnnotation.order(),
                    fullValueAnnotation.name(),
                    fullValueAnnotation.description(),
                    method.getDeclaringClass().getSimpleName(),
                    method.getName());
        }

        TypedAnnotation typedAnnotation = resolveTypedAnnotation(method);
        if (typedAnnotation != null) {
            log.info("TypedAnnotation returnType='{}' on {}.{}()",
                    typedAnnotation.returnType(),
                    method.getDeclaringClass().getSimpleName(),
                    method.getName());
        }

        RepeatableAnnotation[] repeatableAnnotations = method.getAnnotationsByType(RepeatableAnnotation.class);
        if (repeatableAnnotations.length > 0) {
            log.info("RepeatableAnnotation values={} on {}.{}()",
                    Arrays.stream(repeatableAnnotations).map(RepeatableAnnotation::value).toList(),
                    method.getDeclaringClass().getSimpleName(),
                    method.getName());
        }
    }

    private TypedAnnotation resolveTypedAnnotation(Method method) {
        TypedAnnotation typedAnnotation = method.getAnnotation(TypedAnnotation.class);
        if (typedAnnotation != null) {
            return typedAnnotation;
        }
        return method.getDeclaringClass().getAnnotation(TypedAnnotation.class);
    }
}
