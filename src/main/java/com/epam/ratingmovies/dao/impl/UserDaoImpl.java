package com.epam.ratingmovies.dao.impl;

import com.epam.ratingmovies.dao.api.UserDAO;
import com.epam.ratingmovies.dao.connectionpool.api.ConnectionPool;
import com.epam.ratingmovies.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.exception.DaoException;
import com.epam.ratingmovies.dao.mapper.api.RowMapper;
import com.epam.ratingmovies.dao.mapper.impl.UserRowMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

import static com.epam.ratingmovies.dao.entity.ColumnName.USER_ID;

public class UserDaoImpl implements UserDAO {

    private final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    private final RowMapper<User> mapper = new UserRowMapper();
    private static final String SQL_SAVE_USER = "INSERT INTO users( login, password," +
            " role_id, name, mail, account_telegram, status_id, create_time," +
            " profile_picture)" +
            " values (?,?,?,?,?,?,?,?,?)";


    private static final String SQL_FIND_ALL_USERS = "SELECT user_id, login, password," + "role_id,name,mail,account_telegram,status_id,create_time,profile_picture FROM users";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT user_id, login, password," + "role_id,name,mail,account_telegram,status_id,create_time,profile_picture FROM users WHERE login = ?";
    private static final String SQL_FIND_ID_BY_LOGIN = "SELECT user_id FROM users WHERE login = ?";
    private static final String SQL_FIND_USER_BY_ID = "SELECT user_id, login, password," + "role_id,name,mail,account_telegram,status_id,create_time,profile_picture FROM users WHERE user_id = ?";
    private static final String SQL_FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT user_id, login, password," + "role_id,name,mail,account_telegram,status_id,create_time,profile_picture FROM users WHERE login = ? and password = ?";
    private static final String SQL_FIND_USER_BY_ACCOUNT_TELEGRAM = "SELECT user_id, login, password," + "role_id,name,mail,account_telegram,status_id,create_time,profile_picture FROM users WHERE account_telegram = ?";
    private static final String SQL_FIND_USER_BY_MAIL = "SELECT user_id, login, password," + "role_id,name,mail,account_telegram,status_id,create_time,profile_picture FROM users WHERE mail = ?";


    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE user_id = ?";

    private static final String SQL_UPDATE_USER_BY_ID = "UPDATE users SET login = ?, password = ?, role_id = ? ,name =  ?, mail = ? , account_telegram = ? , status_id = ? , create_time = ? , profile_picture = ?  WHERE user_id = ?";
    private static final String SQL_UPDATE_PHOTO_BY_ID = "UPDATE users SET profile_picture  = ? WHERE user_id = ?";
    private static final String SQL_UPDATE_PASSWORD_BY_ID = "UPDATE users SET password  = ? WHERE user_id = ?";
    private static final String SQL_UPDATE_STATUS_BY_ID = "UPDATE users SET status_id  = ? WHERE user_id = ?";
    private static final String SQL_UPDATE_NAME_EMAIL_TELEGRAM_BY_ID = "UPDATE users SET name = ?, mail = ?, account_telegram = ? WHERE user_id = ?";
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    public UserDaoImpl() {
    }

    public void updateNameEmailTelegramById(String name, String email, String telegram, long id) throws SQLException {
        Connection connection = connectionPool.takeConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_NAME_EMAIL_TELEGRAM_BY_ID);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, telegram);
        preparedStatement.setLong(4, id);
        preparedStatement.executeUpdate();
        connectionPool.returnConnection(connection);

    }

    @Override
    public User save(User user) throws DaoException {

        Connection connection = connectionPool.takeConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getUserRole().ordinal());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getTelegramAccount());
            preparedStatement.setInt(7, user.getUserStatus().getId());
            preparedStatement.setTimestamp(8, user.getDate());
            preparedStatement.setString(9, user.getProfilePicture());


            int execute = preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new DaoException("Creating user failed, no ID obtained.");
                }

                connectionPool.returnConnection(connection);
            }

        } catch (SQLException e) {

            throw new DaoException(e);
        }
        return user;
    }

    public long findIdByLogin(String login) throws DaoException {
        ResultSet resultSet = null;
        long id = 0;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ID_BY_LOGIN)) {
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                id = resultSet.getInt(USER_ID);

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }

        }
        return id;
    }

    @Override
    public void delete(Long id) throws DaoException {

        Connection connection = connectionPool.takeConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
            preparedStatement.setLong(1, id);
            Boolean result = Objects.equals(preparedStatement.executeUpdate(), 1);
            preparedStatement.close();
            System.out.println(result);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        connectionPool.returnConnection(connection);
    }

    public Optional<User> findUserById(long id) throws DaoException {
        ResultSet resultSet = null;
        Optional<User> userOptional = null;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                userOptional = Optional.of(mapper.map(resultSet));
            }

            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }

        }
        return userOptional;

    }


    @Override
    public List<User> findAll() throws DaoException {
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

            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public Optional<User> findById(Long idEntity) {

        return Optional.empty();
    }

    public boolean blockById(Long id) throws DaoException {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STATUS_BY_ID);
            preparedStatement.setInt(1, 2);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            connectionPool.returnConnection(connection);

        } catch (SQLException e) {
            logger.throwing(Level.WARN, e);
            throw new DaoException(e);

        }
        return true;
    }


    public boolean isUnblockedById(long id) throws DaoException {
        User user = findUserById(id).get();
        if (user.getUserStatus().getId() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBlockedById(long id) throws DaoException {
        User user = findUserById(id).get();
        if (user.getUserStatus().getId() == 2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean unblockById(Long id) throws DaoException {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STATUS_BY_ID);
            preparedStatement.setInt(1, 1);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            connectionPool.returnConnection(connection);

        } catch (SQLException e) {
            logger.throwing(Level.WARN, e);
            throw new DaoException(e);

        }
        return true;
    }


    //todo изменить логику крч я сравниваю по айди а например в моей бд
    //были удалены юзеры с некоторыми айди поэтому не ровно выводит
    //todo!!!! obezatelno
    @Override
    public List<User> findUsersRange(int offset, int amount,List<User> users) throws DaoException {
        int count = 0;

        //todo проверить правильно или не!!!!

        List<User> result = new ArrayList<>();

        for (User user :users){
            if (offset < count && count <= offset + amount) {
                result.add(user);
            }
            count++;
        }

        return result;
    }


    //todo мб поменять логику
    @Override
    public int findUsersAmount() throws DaoException {
        Connection connection = connectionPool.takeConnection();
        int counter = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                counter++;
            }
            preparedStatement.close();
            connectionPool.returnConnection(connection);

        } catch (SQLException e) {
            logger.throwing(Level.WARN, e);
            throw new DaoException(e);
        }
        return counter;
    }

    @Override
    public Optional<User> findUserByLoginPassword(String login, String password) throws DaoException {

        ResultSet resultSet = null;
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userOptional = Optional.of(mapper.map(resultSet));
            }

            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            logger.throwing(Level.WARN, e);
            e.printStackTrace();

        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.throwing(Level.WARN, e);
                throw new DaoException(e);
            }

        }
        return userOptional;
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

            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.throwing(Level.WARN, e);

                throw new DaoException(e);
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

            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e);
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

            connectionPool.returnConnection(connection);
            return resultSet.next();
        } catch (SQLException e) {

        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.throwing(Level.WARN, e);

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

            connectionPool.returnConnection(connection);
            return resultSet.next();
        } catch (SQLException e) {
            logger.throwing(Level.WARN, e);


        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.throwing(Level.WARN, e);

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

            connectionPool.returnConnection(connection);
            if (resultSet.next()) {

                userOptional = Optional.of(mapper.map(resultSet));
            }
        } catch (SQLException e) {
            logger.throwing(Level.WARN, e);
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

            connectionPool.returnConnection(connection);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.throwing(Level.WARN, e);

            }
        }
        //todo исправить
        return false;
    }


    public void updatePhotoByUserId(long id, String photo) throws DaoException {
        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PHOTO_BY_ID);
            preparedStatement.setString(1, photo);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            logger.throwing(Level.WARN, e);

            throw new DaoException(e);
        }
    }

    public boolean updatePasswordByUserId(long id, String password) throws DaoException {

        try {
            Connection connection = connectionPool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PASSWORD_BY_ID);
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            connectionPool.returnConnection(connection);

        } catch (SQLException e) {
            logger.throwing(Level.WARN, e);

            throw new DaoException(e);
        }
        return true;
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
            preparedStatement.setInt(3, user.getUserRole().ordinal());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getTelegramAccount());
            preparedStatement.setInt(7, user.getUserStatus().getId());
            preparedStatement.setTimestamp(8, user.getDate());
            preparedStatement.setString(9, user.getProfilePicture());
            System.out.println(user.getId());
            preparedStatement.setLong(10, user.getId());
            Boolean result = Objects.equals(preparedStatement.executeUpdate(), 1);
            preparedStatement.close();

            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            logger.throwing(Level.WARN, e);
            throw new DaoException(e);
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
    public boolean updateGeneralInfoByUserId(long userId, User user) throws DaoException {
        return false;
    }
}
