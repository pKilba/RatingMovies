package com.epam.ratingmovies.dao.mapper.impl;

import com.epam.ratingmovies.dao.entity.*;
import com.epam.ratingmovies.dao.mapper.api.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.ratingmovies.dao.entity.ColumnName.*;
import static com.epam.ratingmovies.dao.entity.ColumnName.USER_PROFILE_PICTURE;

public class MovieRowMapper implements RowMapper<Movie> {
    private static MovieRowMapper instance;

    public static MovieRowMapper getInstance() {
        if (instance == null) {
            instance = new MovieRowMapper();
        }
        return instance;
    }

    @Override
    public Movie map(ResultSet resultSet) throws SQLException {
        return  Movie.builder()
                .setMovieId(resultSet.getInt(MOVIE_ID))
                .setPoster(resultSet.getString(MOVIE_POSTER))
                .setAbout(resultSet.getString(MOVIE_ABOUT))
                .setReleaseTime(resultSet.getTimestamp(MOVIE_RELEASE_DATE))
                .setAmountLike(resultSet.getInt(MOVIE_AMOUNT_LIKE))
                .setAmountDislike(resultSet.getInt(MOVIE_AMOUNT_DISLIKE))
                .setMovieGenre(Genre.getById(resultSet.getInt(MOVIE_GENRE_ID)))
                .build();
    }
}
