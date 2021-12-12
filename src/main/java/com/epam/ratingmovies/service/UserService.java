package com.epam.ratingmovies.service;

import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.impl.UserDaoImpl;
import com.epam.ratingmovies.exception.DaoException;
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

    public List findUsers() throws DaoException {
        return userDao.findAll();
    }


    public void updateNameEmailTelegramById(String name, String email, String telegram, long id) throws SQLException {
        userDao.updateNameEmailTelegramById(name, email, telegram, id);
    }

    public int findUsersAmount() throws DaoException {
        return userDao.findUsersAmount();
    }

    public void updatePhotoByUserId(long userId, String fileName) throws SQLException, DaoException {
        userDao.updatePhotoByUserId(userId, fileName);
    }

    public void updatePasswordByUserId(long userId, String password) throws SQLException, DaoException {
        userDao.updatePasswordByUserId(userId, password);
    }


    //todo через isPresent чекать нал или нет
    public User findUserById(long id) throws ServiceException, DaoException {
        Optional<User> user = userDao.findUserById(id);
        if (user.isPresent()) {
            return user.get();
        } else throw new ServiceException("Error Service");
    }

    public List<User> findUsersRange(int amountQuery, int size,List<User> users) throws DaoException {

        return userDao.findUsersRange(amountQuery, size,users);
    }

    public boolean blockedById(long id) throws DaoException {
        return userDao.blockById(id);
    }

    public boolean unblockById(long id) throws DaoException {
        return userDao.unblockById(id);
    }


    public boolean isBlockedById(long id) throws DaoException {
        return userDao.isBlockedById(id);
    }

    public boolean isUnblockedById(long id) throws DaoException {
        return userDao.isUnblockedById(id);
    }

    //todo optional return
    public User findUserByLogin(String login) throws DaoException {
        Optional<User> user = userDao.findUserByLogin(login);
        return user.get();
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        Optional<User> user = userDao.findUserByLoginPassword(login, password);
        return user;
    }

}
