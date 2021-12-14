package com.epam.ratingmovies.service.validator;

public interface UserValidatorAPI {

    boolean isValid(String login, String email, String telegram, String password, String name);
}