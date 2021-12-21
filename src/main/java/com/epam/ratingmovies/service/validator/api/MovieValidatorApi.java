package com.epam.ratingmovies.service.validator.api;

public interface MovieValidatorApi {


    /**
     * @param name     name movie
     * @param like     like movie
     * @param dislike  dislike movie
     * @param duration duration movie
     * @param producer producer movie
     * @param about    about movie
     * @return true or false, result correct or not correct date about film
     */
    boolean isValid(String name, int like, int dislike, int duration, String producer, String about);
}
