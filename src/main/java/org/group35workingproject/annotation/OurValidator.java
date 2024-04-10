package org.group35workingproject.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OurValidator implements ConstraintValidator<OurValidation, String> {
    @Override
    public void initialize(OurValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }
        return s.startsWith("S"); // проверка о том, что значение должно начинаться с литеры 'S'
    }
}
