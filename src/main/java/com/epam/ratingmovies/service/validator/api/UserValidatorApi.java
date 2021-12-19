package com.epam.ratingmovies.service.validator.api;

public interface UserValidatorApi {

    boolean isValid(String login, String email, String telegram, String password, String name);
}