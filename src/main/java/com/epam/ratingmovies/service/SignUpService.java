package com.epam.ratingmovies.service;


import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.impl.UserDaoImpl;
import com.epam.ratingmovies.exception.DaoException;
import com.epam.ratingmovies.service.converter.Converter;
//import com.epam.ratingmovies.service.converter.UserConverter;
import com.epam.ratingmovies.service.dto.UserDto;
import com.epam.ratingmovies.service.validator.UserValidator;
import com.epam.ratingmovies.service.validator.Validator;

import java.util.Optional;

public class SignUpService {
    //  private static final Validator<User> userValidator = UserValidator.getInstance();
  //  private final Converter<UserDto, User> converter = new UserConverter();
    private static SignUpService instance;
    private final UserDaoImpl userDao = new UserDaoImpl();
    private final Validator<User> userValidator= UserValidator.getInstance();

    private SignUpService() {
    }

    public static SignUpService getInstance() {
        if (instance == null) {
            instance = new SignUpService();
        }
        return instance;
    }

    //todo нужен ли тут вообще юзердто??!
    //todo userDto ВМЕСТО ЮЗЕР В ПАРАМЕТРАХ
    public long signUp(User user) throws DaoException {
        if (!userValidator.isValid(user)) {
            System.out.println("что то плохо заполнили");
            return -1;
        }

        long userId = 1;
        // final String encryptedPassword = passwordToMd5(userDto);
        //user.setPassword(encryptedPassword);
        userDao.save(user);
        //userDao.save(converter.convert(user));
        //return converter.convert(user);  } catch (Exception e) {
        System.out.println("12");
        System.out.println("12");
        System.out.println("12");

        return userId;
    }


    //todo isPresent ёт не работатет
    public boolean isUserLoginExist(String login) throws DaoException {
        Optional<User> user = userDao.findUserByLogin(login);
        if (user!=null){
            return true;
        }
        else {
            return false;
        }

    }


    //todo крч при регистрации и ввода несущ данныъ или сущ логина
    // всё гуд при вводе сущ емейл или телега при повторном заполнении страница долго грузится


    //todo проверить сущ емейла и телеги не проверял
    public boolean isUserEmailExist(String email) throws DaoException {
        Optional<User> user = userDao.findUserByEmail(email);
        if (user!=null){
            return true;
        }
        else {
            return false;
        }

    }
    public boolean isUserTelegramExist(String telegram) throws DaoException {
        Optional<User> user = userDao.findUserByTelegram(telegram);
        if (user!=null){
            return true;
        }
        else {
            return false;
        }

    }
}
