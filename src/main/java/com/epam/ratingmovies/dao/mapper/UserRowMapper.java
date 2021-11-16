package com.epam.ratingmovies.dao.mapper;

import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.entity.UserRole;
import com.epam.ratingmovies.dao.entity.UserStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.ratingmovies.dao.entity.ColumnName.*;

public class UserRowMapper implements RowMapper<User>{
    private static UserRowMapper instance;

    public static UserRowMapper getInstance() {
        if (instance == null) {
            instance = new UserRowMapper();
        }
        return instance;
    }

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        return  User.builder()
                .setUserId(resultSet.getInt(USER_ID))
                .setLogin(resultSet.getString(USER_LOGIN))
                .setPassword(resultSet.getString(USER_PASSWORD))
                .setUserRole(UserRole.valueOf(resultSet.getString(USER_ROLE)))
                .setName(resultSet.getString(USER_NAME))
                .setEmail(resultSet.getString(USER_EMAIL))
                .setTelegram (resultSet.getString(USER_TELEGRAM))
                .setUserStatus(UserStatus.valueOf(resultSet.getString(USER_STATUS)))
                .setCreateTime(resultSet.getTimestamp(USER_CREATE_TIME))
                .setProfilePicture(resultSet.getInt(USER_PROFILE_PICTURE))
                .build();
    }
}
