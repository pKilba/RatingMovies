package com.epam.ratingmovies.service;

import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.service.validator.UserValidator;

public class AccountChangePassword {

    UserValidator userValidator = UserValidator.getInstance();
    private boolean isCorrectPassword;

    public boolean isCorrectPassword(String newPassword, User user,String currentPassword) {
        isCorrectPassword = isValidPassword(newPassword);
        isCorrectPassword = isEqualPasswordUserAndCurrentPassword(user, currentPassword);
        return isCorrectPassword;
    }

    public boolean isEqualPasswordUserAndCurrentPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    public boolean isValidPassword(String password) {

        return userValidator.isValidPassword(password);
    }


}
