package com.epam.ratingmovies.service;


import com.epam.ratingmovies.service.validator.UserValidator;

public class AccountInfoChangeService {

    private boolean isCorrectInfoAccount;
    UserValidator userValidator = UserValidator.getInstance();


    public boolean isValidTelegram(String telegram) {

        return userValidator.isValidTelegram(telegram);
    }


    public boolean isValidInfoAccount(String email, String telegram, String name) {
      boolean isCorrect =  isValidTelegram(telegram);
       isCorrect = isValidName(name);
      isCorrect =  isValidEmail(email);
        return isCorrect;
    }


    public boolean isValidEmail(String email) {

        return userValidator.isValidEmail(email);
    }

    public boolean isValidName(String name) {
        return  userValidator.isValidName(name);
    }


}
