package com.epam.ratingmovies.dao.mapper.impl;

import com.epam.ratingmovies.dao.entity.Comment;
import com.epam.ratingmovies.dao.entity.Genre;
import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.dao.impl.MovieDaoImpl;
import com.epam.ratingmovies.dao.mapper.api.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.ratingmovies.dao.entity.ColumnName.*;

public class CommentRowMapper implements RowMapper<Comment> {

    private static CommentRowMapper instance;

    public static CommentRowMapper getInstance() {
        if (instance == null) {
            instance = new CommentRowMapper();
        }
        return instance;
    }

    //todo хз корректно или нет скоррее точно нет
    @Override
    public Comment map(ResultSet resultSet) throws SQLException {
        return  Comment.builder()
                .setCommendId(resultSet.getInt(COMMENT_ID))
                .setMessage(resultSet.getString(COMMENT_MESSAGE))
                .setMovie(resultSet.getInt(MOVIE_ID))
                .setUser(resultSet.getInt(USER_ID))
                .setCreateTime(resultSet.getTimestamp(COMMENT_CREATE_TIME))
                .build();
    }
}
