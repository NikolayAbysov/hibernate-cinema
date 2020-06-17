package com.dev.cinema.dto;

import javax.validation.constraints.NotNull;

public class OrderRequestAddDto {
    @NotNull(message = "Empty email field!")
    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
