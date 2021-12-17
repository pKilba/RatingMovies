package com.epam.ratingmovies.dao.api;

import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDAO extends DAO<User, Long> {
    /**
     * Gets list users in range described as offset and amount of users.
     *
     * @param offset an amount of users to get.
     * @param amount the amount
     * @return a received list of users.
     * @throws DaoException if database errors occurs.
     */
    List<User> findUsersRange(int offset, int amount) throws DaoException;

    /**
     * Gets users amount in database.
     *
     * @return an amount of all users.
     * @throws DaoException if database errors occurs.
     */
    int findUsersAmount() throws DaoException;

    /**
     * Finds user in database by login and password and returns container of account
     * or empty container if not found.
     *
     * @param login    a login(username) of user.
     * @param password a password of user.
     * @return an optional container of account.
     * @throws DaoException if database errors occurs.
     */
    Optional<User> findUserByLoginPassword(String login, String password) throws DaoException;

    /**
     * Finds user in database by login and returns container of account
     * or empty container if not found.
     *
     * @param login a login(username) of account.
     * @return an optional container of account.
     * @throws DaoException if database errors occurs.
     */
    Optional<User> findUserByLogin(String login) throws DaoException;

    boolean findUserByLogin(User user) throws DaoException;

    boolean findUserByTelegram(User user) throws DaoException;

    /**
     * Finds user in database by login and returns container of account
     * or empty container if not found.
     *
     * @param email a email of user.
     * @return an optional container of user.
     * @throws DaoException if database errors occurs.
     */
    Optional<User> findUserByEmail(String email) throws DaoException;

    boolean findUserByEmail(User user) throws DaoException;


    /**
     * Saves specific object to database.
     * Object User should be conatin UserId field
     *
     * @param item User object to update.
     * @return boolean value successe or fail update
     * @throws DaoException if database errors occurs.
     */
    User update(User item) throws DaoException;

    /**
     * Add long.
     *
     * @param t the t
     * @return the long
     * @throws DaoException the dao exception
     */
    long add(User t) throws DaoException;

    /**
     * Update password by user id boolean.
     *
     * @param userId   the user id
     * @param password the password
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updatePasswordByUserId(long userId, String password) throws DaoException, SQLException;

    /**
     * Update general info by user id boolean.
     *
     * @param userId the user id
     * @param user   the user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateGeneralInfoByUserId(long userId, User user) throws DaoException;


}
