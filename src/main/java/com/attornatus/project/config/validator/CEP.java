package com.attornatus.project.config.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;

@Constraint(validatedBy = CEPValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CEP {
  String message() default "Invalid CEP or already exists";

  Class<?>[] groups() default {};

  Class<?>[] payload() default {};
}
