package com.epam.ratingmovies.service;


import com.epam.ratingmovies.service.validator.impl.UserValidator;

public class AccountInfoChangeService {

    UserValidator userValidator = UserValidator.getInstance();


    static private AccountInfoChangeService instance ;

    private AccountInfoChangeService() {

    }

    public static AccountInfoChangeService getInstance() {
        if (instance == null) {
            instance = new AccountInfoChangeService();
        }
        return instance;
    }


    public boolean isValidTelegram(String telegram) {

        return userValidator.isValidTelegram(telegram);
    }


    public boolean isValidInfoAccount(String email, String telegram, String name) {
        return isValidTelegram(telegram)&&
         isValidName(name)&&
         isValidEmail(email);

    }


    public boolean isValidEmail(String email) {

        return userValidator.isValidEmail(email);
    }

    public boolean isValidName(String name) {
        return userValidator.isValidName(name);
    }


}
