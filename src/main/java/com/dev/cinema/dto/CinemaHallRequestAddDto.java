package com.dev.cinema.dto;

import javax.validation.constraints.NotNull;

public class CinemaHallRequestAddDto {
    @NotNull(message = "Empty capacity field!")
    private Integer capacity;
    @NotNull(message = "Empty description field!")
    private String description;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
