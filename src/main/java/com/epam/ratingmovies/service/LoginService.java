package com.epam.ratingmovies.service;

import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.impl.UserDaoImpl;
import com.epam.ratingmovies.exception.DaoException;
import com.epam.ratingmovies.service.validator.UserValidator;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginService {

    private static final String SIGN_UP_PROBLEM = "Error sign-up";
    private static final String EXIST_PROBLEM_LOGIN = "Login exist";
    private static final String EXIST_PROBLEM_EMAIL = "Email exist";
    private static final String EXIST_PROBLEM_TELEGRAM = "Telegram exist";
    private static LoginService instance;
    private final UserDaoImpl userDao = new UserDaoImpl();
    private final UserValidator userValidator = UserValidator.getInstance();
    private static final Logger logger = LogManager.getLogger();

    private LoginService() {
    }

    public static LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }
        return instance;
    }


    public boolean isValid(String login, String password) {
        return userValidator.isValidPassword(password) && userValidator.isValidLogin(login);
    }


}
