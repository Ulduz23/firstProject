package com.abbtech.annotation.repeatable;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@Repeatable(RepeatableAnnotations.class)
@Target({METHOD, FIELD})

public @interface RepeatableAnnotation {
    String value();
}
