package com.dev.cinema.dto;

import javax.validation.constraints.NotNull;

public class MovieRequestAddDto {
    @NotNull(message = "Empty title field!")
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
