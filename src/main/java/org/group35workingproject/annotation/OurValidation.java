package org.group35workingproject.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OurValidator.class)
public @interface OurValidation {
    String message() default "Wrong value";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
