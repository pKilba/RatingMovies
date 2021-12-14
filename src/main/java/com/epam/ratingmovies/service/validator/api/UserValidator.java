package com.epam.ratingmovies.service.validator.api;

public interface UserValidator {

    boolean isValid(String login, String email, String telegram, String password, String name);
}