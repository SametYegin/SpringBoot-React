package com.samti.ws.controller;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull.List;

@Target({ FIELD })
@Retention(RUNTIME) //runtime zamanında çözümlenir.
@Constraint(validatedBy = { UniqueEmailValidator.class }) //validasyon logic ilgili class
public @interface UniqueEmail {
	
	String message() default "Email must be unique";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
