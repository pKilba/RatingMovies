package com.epam.ratingmovies.service;

import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.impl.MovieDaoImpl;
import com.google.protobuf.ServiceException;

import java.util.List;
import java.util.Optional;

public class MovieService {

    MovieDaoImpl movieDao = new MovieDaoImpl();

    public int findMoviesAmount() {
        return movieDao.findMoviesAmount();
    }

    public Movie findMovieById(long id) throws ServiceException {
        Optional<Movie> movie = movieDao.findMovieById((int)id);
        if (movie.isPresent()) {
            return movie.get();
        } else throw new ServiceException("Error Service");
    }


    public List<Movie> findMoviesRange(int amountQuery, int size) {

        return movieDao.findMoviesRange(amountQuery, size);
    }
}
