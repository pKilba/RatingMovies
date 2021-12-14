package com.epam.ratingmovies.service;


import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.impl.UserDaoImpl;
import com.epam.ratingmovies.exception.DaoException;
import com.epam.ratingmovies.service.converter.Converter;
//import com.epam.ratingmovies.service.converter.UserConverter;
import com.epam.ratingmovies.service.dto.UserDto;
import com.epam.ratingmovies.service.validator.UserValidator;
import com.epam.ratingmovies.service.validator.Validator;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class SignUpService {

    private static final String SIGN_UP_PROBLEM ="Error sign-up";
    private static final String EXIST_PROBLEM_LOGIN ="Login exist";
    private static final String EXIST_PROBLEM_EMAIL ="Email exist";
    private static final String EXIST_PROBLEM_TELEGRAM ="Telegram exist";
    private static SignUpService instance;
    private final UserDaoImpl userDao = new UserDaoImpl();
    private final UserValidator userValidator = UserValidator.getInstance();
    private static final Logger logger = LogManager.getLogger();
    private SignUpService() {
    }

    public static SignUpService getInstance() {
        if (instance == null) {
            instance = new SignUpService();
        }
        return instance;
    }


    public boolean isValid(String login,String email,String telegram ,String password,String name) {
        return userValidator.isValid(login,email,telegram,password,name);
    }

    //todo нужен ли тут вообще юзердто??!
    //todo userDto ВМЕСТО ЮЗЕР В ПАРАМЕТРАХ
    public long signUp(User user) throws ServiceException {
        long userId = 1;
        try {
            userDao.save(user);
        } catch (DaoException e) {
            logger.error(SIGN_UP_PROBLEM + e);
            throw new ServiceException(SIGN_UP_PROBLEM + e);

        }

        return userId;
    }

    public boolean isUserLoginExist(String login) throws ServiceException {
        Optional<User> user = Optional.empty() ;
        try {
            user = userDao.findUserByLogin(login);
        } catch (DaoException e) {
            logger.warn(EXIST_PROBLEM_LOGIN);
            throw new ServiceException(EXIST_PROBLEM_LOGIN + e);

        }
        if (user.isPresent()) {
            logger.warn(EXIST_PROBLEM_LOGIN);
            return true;
        } else {
            return false;
        }

    }


    //todo крч при регистрации и ввода несущ данныъ или сущ логина
    // всё гуд при вводе сущ емейл или телега при повторном заполнении страница долго грузится


    //todo проверить сущ емейла и телеги не проверял
    public boolean isUserEmailExist(String email) throws ServiceException {
        Optional<User> user = Optional.empty();
        try {
            user = userDao.findUserByEmail(email);
        } catch (DaoException e) {
            logger.warn(EXIST_PROBLEM_EMAIL);
            throw new ServiceException(EXIST_PROBLEM_EMAIL + e);
        }
        if (user.isPresent()) {
            logger.warn(EXIST_PROBLEM_EMAIL);
            return true;
        } else {
            return false;
        }

    }

    public boolean isUserTelegramExist(String telegram) throws ServiceException {
        Optional<User> user = Optional.empty();
        try {
            user = userDao.findUserByTelegram(telegram);
        } catch (DaoException e) {
            logger.warn(EXIST_PROBLEM_TELEGRAM);
            throw new ServiceException(EXIST_PROBLEM_TELEGRAM + e);
        }
        if (user.isPresent()) {
            logger.warn(EXIST_PROBLEM_TELEGRAM);
            return true;
        } else {
            return false;
        }

    }
}
