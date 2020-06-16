package com.dev.cinema.annotation.validator;

import com.dev.cinema.annotation.EmailConstraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
    private static final String MATCH_STRING = "^\\S+@\\S+\\.\\S+$";

    @Override
    public boolean isValid(String email,
                           ConstraintValidatorContext cxt) {
        return email != null && email.matches(MATCH_STRING);
    }

}
