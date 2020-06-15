package com.dev.cinema.annotation.validator;

import com.dev.cinema.annotation.ValidRepeatPasswordConstraint;
import com.dev.cinema.dto.UserRegisterDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidRepeatPasswordConstraintValidator
        implements ConstraintValidator<ValidRepeatPasswordConstraint, UserRegisterDto> {

    public void initialize(ValidRepeatPasswordConstraint constraintAnnotation) {
    }

    public boolean isValid(UserRegisterDto userRegisterDto, ConstraintValidatorContext context) {
        return userRegisterDto.getPassword() != null
               && userRegisterDto.getPassword().equals(userRegisterDto.getRepeatPassword());
    }
}
