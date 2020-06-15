package com.dev.cinema.dto;

import com.dev.cinema.annotation.EmailConstraint;
import com.dev.cinema.annotation.ValidRepeatPasswordConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ValidRepeatPasswordConstraint
public class UserRegisterDto {
    @NotNull(message = "Empty email field!")
    @EmailConstraint
    private String email;
    @NotNull(message = "Empty password field!")
    @Size(min = 5, message = "Less than 5 symbols in input")
    private String password;
    @NotNull(message = "Empty repeatPassword field!")
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
