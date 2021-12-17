package com.epam.ratingmovies.dao.mapper.impl;

import com.epam.ratingmovies.dao.entity.Genre;
import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.dao.mapper.api.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.ratingmovies.dao.entity.ColumnName.MOVIE_ABOUT;
import static com.epam.ratingmovies.dao.entity.ColumnName.MOVIE_AMOUNT_DISLIKE;
import static com.epam.ratingmovies.dao.entity.ColumnName.MOVIE_AMOUNT_LIKE;
import static com.epam.ratingmovies.dao.entity.ColumnName.MOVIE_BACKGROUND;
import static com.epam.ratingmovies.dao.entity.ColumnName.MOVIE_DURATION;
import static com.epam.ratingmovies.dao.entity.ColumnName.MOVIE_GENRE_ID;
import static com.epam.ratingmovies.dao.entity.ColumnName.MOVIE_ID;
import static com.epam.ratingmovies.dao.entity.ColumnName.MOVIE_NAME;
import static com.epam.ratingmovies.dao.entity.ColumnName.MOVIE_POSTER;
import static com.epam.ratingmovies.dao.entity.ColumnName.MOVIE_PRODUCER;
import static com.epam.ratingmovies.dao.entity.ColumnName.MOVIE_RELEASE_DATE;

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
                .setMovieName(resultSet.getString(MOVIE_NAME))
                .setMovieProducer(resultSet.getString(MOVIE_PRODUCER))
                .setMovieDuration(resultSet.getInt(MOVIE_DURATION))
                .setMovieBackground(resultSet.getString(MOVIE_BACKGROUND))
                .build();
    }
}
