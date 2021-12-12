package com.epam.ratingmovies.service;

import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.service.validator.UserValidator;
import com.epam.ratingmovies.util.LineHasher;

public class AccountChangePassword {
    UserValidator userValidator = UserValidator.getInstance();
    private boolean isCorrectPassword;

    public boolean isCorrectPassword(String newPasswordFirst, String newPasswordSecond, User user, String currentPassword) {


        isCorrectPassword =
                isValidPassword(newPasswordFirst) &&
                        isValidPassword(newPasswordFirst) &&
                        isEqualFirstNewPasswordAndSecond(newPasswordFirst, newPasswordSecond) &&
                        isValidPassword(currentPassword) && isEqualPasswordUserAndCurrentPassword(user, currentPassword);
        return isCorrectPassword;
    }

    public boolean isEqualFirstNewPasswordAndSecond(String newPasswordFirst, String newSecondPassword) {
        return newPasswordFirst.equals(newSecondPassword);
    }

    public boolean isEqualPasswordUserAndCurrentPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    public boolean isValidPassword(String password) {

        return userValidator.isValidPassword(password);
    }


}
