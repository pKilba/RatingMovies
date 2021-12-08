package com.epam.ratingmovies.service;

import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.impl.UserDaoImpl;
import com.google.protobuf.ServiceException;

import java.sql.SQLException;
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



public void updateNameEmailTelegramById(String name,String email,String telegram,long id) throws SQLException {
        userDao.updateNameEmailTelegramById(name,email, telegram, id);
}
    public int findUsersAmount() {
        return userDao.findUsersAmount();
    }
    public void updatePhotoByUserId(long userId,String fileName) throws SQLException {
        userDao.updatePhotoByUserId(userId,fileName);
    }
    public void updatePasswordByUserId(long userId,String password) throws SQLException {
        userDao.updatePasswordByUserId(userId,password);
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
