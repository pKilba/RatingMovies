package com.epam.ratingmovies.service.validator;

import com.epam.ratingmovies.dao.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


//todo можно логику немного поменять дорабоать + проверить

public class UserValidator implements Validator {
    private static final String NAME_PATTERN = "[A-zА-яЁё]+";
    private static final String TELEGRAM_PATTERN = "^@[a-zA-Z0-9+]+$";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    private static final Pattern COMPILED_PATTERN_NAME = Pattern.compile(NAME_PATTERN);
    private static final Pattern COMPILED_PATTERN_TELEGRAM = Pattern.compile(TELEGRAM_PATTERN);
    private static final Pattern COMPILED_PATTERN_EMAIL = Pattern.compile(EMAIL_PATTERN);

    private static final int MIN_FIELD_USER_LENGTH = 2;
    private static final int MAX_FIELD_USER_LENGTH = 32;
    private static final int MIN_LOGIN_LENGTH = 4;
    private static final int MAX_EMAIL_LENGTH = 64;
    private static final int MIN_EMAIL_LENGTH = 4;

    private static final int MAX_PASSWORD_LENGTH = 32;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int LENGTH_HASH_PASSWORD = 64;
    private static final int MAX_TELEGRAM_LENGTH = 64;
    private static final int MIN_TELEGRAM_LENGTH = 2;
    private static UserValidator instance;

    private UserValidator() {
    }

    public static UserValidator getInstance() {
        if (instance == null) {
            instance = new UserValidator();
        }
        return instance;
    }

    @Override
    public boolean isValid(User user) {
        //   if (!isValidGeneralInfo(user)) {
        //   return false;
        //}
        String login = user.getLogin();
        if (login == null
                || login.length() < MIN_LOGIN_LENGTH) {
            return false;
        }
        String password = user.getPassword();
        //  if (password == null || password.length() != LENGTH_HASH_PASSWORD) {
        //   return false;
        //}
        String email = user.getEmail();
        if (email == null || email.isEmpty()
                || email.length() > MAX_EMAIL_LENGTH
                || email.length() < MIN_EMAIL_LENGTH
                || !isValidEmail(email)) {
            return false;
        }
        String telegram = user.getTelegramAccount();
        if (telegram == null || telegram.isEmpty()
                || telegram.length() > MAX_TELEGRAM_LENGTH
                || telegram.length() < MIN_TELEGRAM_LENGTH
                || !isValidTelegram(telegram)) {
            return false;
        }

return true;
    }



    public boolean isValidTelegram(String telegram) {
        Matcher matcher = COMPILED_PATTERN_TELEGRAM.matcher(telegram);
        return matcher.matches();
    }

//    public boolean isValidName(String name) {
//        if (name == null || name.length() > MAX_PASSWORD_LENGTH
//                || name.length() < MIN_PASSWORD_LENGTH) {
//            return false;
//        }
//return true;




    public boolean isValidPassword(String line) {
        if (line == null || line.length() !=LENGTH_HASH_PASSWORD) {
            return false;
        }
        return true;
    }


    //todo раскоментить и прчекать
//    public boolean isValidGeneralInfo(User user) {
//        if (user.getName() == null
//                || user.getName().length() > MAX_FIELD_USER_LENGTH
//                || user.getName().length() < MIN_FIELD_USER_LENGTH
//                || !isValidName(user.getName())) {
//            return false;
//        }
//
//        return true;
//    }


    public boolean isValidName(String name) {
        Matcher matcher = COMPILED_PATTERN_NAME.matcher(name);
        return matcher.matches();
    }


    public boolean isValidEmail(String email) {
        Matcher matcher = COMPILED_PATTERN_EMAIL.matcher(email);
        return matcher.matches();
    }

}
