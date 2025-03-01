package mptc.co.movie_rating.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyStringValidator.class)
public @interface NotEmptyString {
    String message() default "Field cannot be empty or blank";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}