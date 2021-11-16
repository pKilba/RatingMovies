package com.epam.ratingmovies.dao;

import com.epam.ratingmovies.dao.connectionpool.ConnectionPool;
import com.epam.ratingmovies.dao.connectionpool.ConnectionPoolImpl;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserDaoImpl implements UserDAO {

    private final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    //todo ctreate suka create!
    private static final String SQL_SAVE_USER = "INSERT INTO users( login, password, role_id, name, mail, account_telegram, status_id, ctreate_time, profile_picture_id) values (?,?,?,?,?,?,?,?,?)";

    private static final String SQL_SAVE_STATUSES = "INSERT INTO statuses(status_id, type) values (?,?)";

    private static final String SQL_FIND_ALL_USERS = "SELECT id, login, password, account_id, is_active, role_id FROM users";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT id, login, password, account_id, is_active, role_id FROM users WHERE login = ?";

    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE user_id = ?";

    private static final String SQL_UPDATE_USER_BY_ID = "UPDATE users SET login = ?, password = ?, account_id = ?, is_active = ?, role_id = ? WHERE id = ?";


    @Override
    public User save(User user) {

            Connection connection = connectionPool.takeConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getUserRole().getId());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getTelegramAccount());
            preparedStatement.setInt(7, user.getUserStatus().getId());
            preparedStatement.setTimestamp(8, user.getDate());
            preparedStatement.setInt(9, user.getProfilePictureId());



            //todo добавить заполнение айди юзера из бд


            int execute = preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
           long generatedId = resultSet.getLong(1);
            user.setId(generatedId);
            System.out.println("gen id = ! ! !"+generatedId + "!! ! ! ! ! ");

            connection.close();

            System.out.println(execute);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(Long id){

        Connection connection = connectionPool.takeConnection();
try {

    PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
    preparedStatement.setLong(1, id);
    Boolean result = Objects.equals(preparedStatement.executeUpdate(), 1);
    preparedStatement.close();
    System.out.println(result);
}
catch (Exception e){
    e.printStackTrace();
}
       // return result;
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long idEntity) {

        return Optional.empty();
    }

    @Override
    public List<User> findUsersRange(int offset, int amount) throws DaoException {
        return null;
    }

    @Override
    public int findUsersAmount() throws DaoException {
        return 0;
    }

    @Override
    public Optional<User> findUserByLoginPassword(String login, String password) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        return Optional.empty();
    }

    @Override
    public User update(User item) throws DaoException {
        return null;
    }

    @Override
    public long add(User t) throws DaoException {
        return 0;
    }

    @Override
    public boolean updatePasswordByUserId(long userId, String password) throws DaoException {
        return false;
    }

    @Override
    public boolean updateGeneralInfoByUserId(long userId, User user) throws DaoException {
        return false;
    }
}
