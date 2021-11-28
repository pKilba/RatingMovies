package com.epam.ratingmovies.dao.api;

import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface MovieDao extends DAO<Movie,Integer>{



    Optional<Movie> findMovieById(Integer id) throws DaoException;
    List<Movie> findMoviesByGenre(String genre) throws DaoException;
}
