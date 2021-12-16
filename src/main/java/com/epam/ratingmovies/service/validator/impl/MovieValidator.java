package com.epam.ratingmovies.service.validator.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieValidator {
    private static final String NAME_PATTERN = "[A-zА-яЁё]+";
    private static final String NUMBER_PATTERN = "[0-9]+";
    private static final Pattern COMPILED_PATTERN_NAME = Pattern.compile(NAME_PATTERN);
    private static final Pattern COMPILED_PATTERN_NUMBER = Pattern.compile(NUMBER_PATTERN);
    private static final int MAX_FILM_NAME_LENGTH = 32;
    private static final int MAX_FILM_ABOUT_LENGTH = 2040;
    private static final int MAX_FILM_PRODUCER_LENGTH = 32;
    private static final int MAX_FILM_NUMBER_ = 10000;
    private static final int MIN_FILM_NUMBER_ = 0;
    private static final int MIN_FILM_ABOUT_LENGTH = 8;
    private static final int MIN_FILM_PRODUCER_LENGTH = 2;
    private static final int MIN_FILM_NAME_LENGTH = 2;

    static private MovieValidator instance ;

    private MovieValidator() {

    }

    public static MovieValidator getInstance() {
        if (instance == null) {
            instance = new MovieValidator();
        }
        return instance;
    }



    public boolean isValid(String name, int like, int dislike, int duration, String producer, String about) {
        if (isValidName(name) && isValidNameProducer(producer)
                && isValidNumber(like) && isValidNumber(dislike) && isValidNumber(duration) && isValidAbout(about)) {
            return true;
        }
        return false;

    }

    public boolean isValidName(String name) {
        Matcher matcher = COMPILED_PATTERN_NAME.matcher(name);
        boolean isCorrect = matcher.matches();
        System.out.println(isCorrect);
        if (!isCorrect || name.length() > MAX_FILM_NAME_LENGTH
                || name.length() < MIN_FILM_NAME_LENGTH) {
            return false;
        }
        return true;
    }

    public boolean isValidNumber(int number) {
        Matcher matcher = COMPILED_PATTERN_NUMBER.matcher(Integer.toString(number));
        boolean isCorrect = matcher.matches();
        System.out.println(isCorrect);
        if (!isCorrect || number > MAX_FILM_NUMBER_
                || number < MIN_FILM_NUMBER_) {
            return false;
        }
        return true;
    }

    public boolean isValidNameProducer(String name) {
        Matcher matcher = COMPILED_PATTERN_NAME.matcher(name);
        boolean isCorrect = matcher.matches();
        System.out.println(isCorrect);
        if (!isCorrect || name.length() > MAX_FILM_PRODUCER_LENGTH
                || name.length() < MIN_FILM_PRODUCER_LENGTH) {
            return false;
        }
        return true;
    }

    public boolean isValidAbout(String name) {
        Matcher matcher = COMPILED_PATTERN_NAME.matcher(name);
        boolean isCorrect = matcher.matches();
        System.out.println(isCorrect);
        if (!isCorrect || name.length() > MAX_FILM_ABOUT_LENGTH
                || name.length() < MIN_FILM_ABOUT_LENGTH) {
            return false;
        }
        return true;
    }


}