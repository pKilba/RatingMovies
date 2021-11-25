package com.epam.ratingmovies.dao.impl;

import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.Parameter;
import com.epam.ratingmovies.dao.api.UserDAO;
import com.epam.ratingmovies.dao.connectionpool.api.ConnectionPool;
import com.epam.ratingmovies.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.entity.UserRole;
import com.epam.ratingmovies.dao.entity.UserStatus;
import com.epam.ratingmovies.dao.exception.DaoException;
import com.epam.ratingmovies.dao.mapper.api.RowMapper;
import com.epam.ratingmovies.dao.mapper.impl.UserRowMapper;

import java.sql.*;
import java.util.*;

public class UserDaoImpl implements UserDAO {

    private final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    //todo мб закинуть в абстрактную штуку какуюнибудь маппер везде будет прст во всех энтити
    private final RowMapper<User> mapper = new UserRowMapper();

    //todo ctreate suka create!
    private static final String SQL_SAVE_USER = "INSERT INTO users( login, password," +
            " role_id, name, mail, account_telegram, status_id, create_time," +
            " profile_picture_id)" +
            " values (?,?,?,?,?,?,?,?,?)";


    private static final String SQL_FIND_ALL_USERS = "SELECT user_id, login, password," + "role_id,name,mail,account_telegram,status_id,create_time,profile_picture_id FROM users";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT user_id, login, password," + "role_id,name,mail,account_telegram,status_id,create_time,profile_picture_id FROM users WHERE login = ?";
    private static final String SQL_FIND_USER_BY_ID = "SELECT user_id, login, password," + "role_id,name,mail,account_telegram,status_id,create_time,profile_picture_id FROM users WHERE user_id = ?";
    private static final String SQL_FIND_USER_BY_ACCOUNT_TELEGRAM = "SELECT user_id, login, password," + "role_id,name,mail,account_telegram,status_id,create_time,profile_picture_id FROM users WHERE account_telegram = ?";
    private static final              String SQL_FIND_USER_BY_MAIL = "SELECT user_id, login, password," + "role_id,name,mail,account_telegram,status_id,create_time,profile_picture_id FROM users WHERE mail = ?";


    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE user_id = ?";

    private static final String SQL_UPDATE_USER_BY_ID = "UPDATE users SET login = ?, password = ?, role_id = ? ,name =  ?, mail = ? , account_telegram = ? , status_id = ? , create_time = ? , profile_picture_id = ?  WHERE user_id = ?";


    public UserDaoImpl() {
    }


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
            System.out.println("gen id = ! ! !" + generatedId + "!! ! ! ! ! ");

            connection.close();

            System.out.println(execute);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("что то совсем не так");
        }
        return user;
    }

    @Override
    public void delete(Long id) {

        Connection connection = connectionPool.takeConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
            preparedStatement.setLong(1, id);
            Boolean result = Objects.equals(preparedStatement.executeUpdate(), 1);
            preparedStatement.close();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return result;
    }

    @Override
    public void delete(User entity) {

    }

    public Optional<User> findUserById(long id){
        ResultSet resultSet = null;
        Optional<User> userOptional = null;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                userOptional = Optional.of(mapper.map(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return userOptional;

    }


    @Override
    public List<User> findAll() {
        Connection connection = connectionPool.takeConnection();
        List<User> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = mapper.map(resultSet);
                result.add(user);
            }
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(result.toArray()));
        return result;
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


    public Optional<User> findUserByTelegram(String telegram) throws DaoException {
        ResultSet resultSet = null;
        Optional<User> userOptional = null;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ACCOUNT_TELEGRAM)) {
            statement.setString(1, telegram);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                userOptional = Optional.of(mapper.map(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return userOptional;
    }


    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        ResultSet resultSet = null;
        Optional<User> userOptional = null;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                userOptional = Optional.of(mapper.map(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
              e.printStackTrace();
            }

        }
        return userOptional;
    }

    @Override
    public boolean findUserByLogin(User user) throws DaoException {
        ResultSet resultSet = null;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {
            statement.setString(1, user.getLogin());
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                //    logger.error("ResultSet wasn't closed");

            }
        }
        //todo исправить
        return false;
    }

    @Override
    public boolean findUserByTelegram(User user) throws DaoException {
        ResultSet resultSet = null;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ACCOUNT_TELEGRAM)) {
            statement.setString(1, user.getTelegramAccount());
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                //    logger.error("ResultSet wasn't closed");

            }
        }
        //todo исправить
        return false;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {

        ResultSet resultSet = null;
        Optional<User> userOptional = null;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_MAIL)) {
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                userOptional = Optional.of(mapper.map(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return userOptional;


    }

    @Override
    public boolean findUserByEmail(User user) throws DaoException {
        ResultSet resultSet = null;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_MAIL)) {
            statement.setString(1, user.getEmail());
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                //    logger.error("ResultSet wasn't closed");

            }
        }
        //todo исправить
        return false;
    }


    //todo мб буду вызывать здесь функцию апдейта юзера по айди а в этой просто получать этот айди
    @Override
    //todo брать возвр ти или сменить его на булеан !!!
    public User update(User user) throws DaoException {
        Connection connection = connectionPool.takeConnection();
        try {


            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_BY_ID);
            //todo что за конченый цвет подсвечивает
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getUserRole().getId());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getTelegramAccount());
            preparedStatement.setInt(7, user.getUserStatus().getId());
            preparedStatement.setTimestamp(8, user.getDate());
            preparedStatement.setInt(9, user.getProfilePictureId());

            Boolean result = Objects.equals(preparedStatement.executeUpdate(), 1);
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;


    }


    //todo check nizhe
    //чем адд отличается от сейв или креэйт ну у меня там чтото выше
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
