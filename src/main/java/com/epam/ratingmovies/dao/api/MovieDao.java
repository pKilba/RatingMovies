package com.epam.ratingmovies.dao.api;

import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface MovieDao extends DAO<Movie, Long> {


    List<Movie> findMoviesRange(int offset, int amount) throws DaoException;

    int findMoviesAmount() throws DaoException;

    Optional<Movie> findById(Long idMovie) throws DaoException;
}
