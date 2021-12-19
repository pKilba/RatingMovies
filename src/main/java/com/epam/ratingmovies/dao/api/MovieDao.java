package com.epam.ratingmovies.dao.api;

import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.exception.DaoException;

import java.util.List;

public interface MovieDao extends DAO<Movie, Long> {


    List<Movie> findMoviesByGenre(String genre) throws DaoException;
}
