package com.abbtech.annotation.repeatable;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Target;

@Target({METHOD, FIELD})
public @interface RepeatableAnnotations {
    RepeatableAnnotation[] value();
}
