package com.epam.ratingmovies.service;

import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.dao.impl.MovieDaoImpl;
import com.epam.ratingmovies.dao.impl.UserDaoImpl;
import com.epam.ratingmovies.service.exeption.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class MovieService {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    MovieDaoImpl movieDao = new MovieDaoImpl();

    public int findMoviesAmount() {
        return movieDao.findMoviesAmount();
    }

    public Movie findMovieById(long id) {
        Optional<Movie> movie = movieDao.findById(id);
        if (movie.isPresent()) {
            return movie.get();
        } else {
            logger.error("movie not found.") ;
            throw new ServiceException("Error Service");
        }
    }


    public void save(Movie movie) {
        movieDao.save(movie);
    }

    public List<Movie> findMoviesRange(int amountQuery, int size) {

        return movieDao.findMoviesRange(amountQuery, size);
    }
}
