package mptc.co.movie_rating.utils;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotEmptyStringValidator implements ConstraintValidator<NotEmptyString, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Reject null or empty strings
        return value != null && !value.trim().isEmpty();
    }
}