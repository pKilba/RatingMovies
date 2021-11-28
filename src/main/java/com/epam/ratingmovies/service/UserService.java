package com.epam.ratingmovies.service;

import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.impl.UserDaoImpl;
import com.google.protobuf.ServiceException;

import java.util.List;
import java.util.Optional;

public class UserService {
    private static UserService instance;
    //todo мб надо синглтон на юзердао
    private static final UserDaoImpl userDao = new UserDaoImpl();

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }


    public int findUsersAmount() {
        return userDao.findUsersAmount();
    }


    //todo через isPresent чекать нал или нет
    public User findUserById(long id) throws ServiceException {
        Optional<User> user = userDao.findUserById(id);
        if (user.isPresent()) {
            return user.get();
        } else throw new ServiceException("Error Service");
    }

    public List<User> findUsersRange(int amountQuery, int size) {

        return userDao.findUsersRange(amountQuery, size);
    }


    //todo optional return
    public User findUserByLogin(String login) {
        Optional<User> user = userDao.findUserByLogin(login);
        return user.get();
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        Optional<User> user = userDao.findUserByLoginPassword(login, password);
        return user;
    }

}
