package com.epam.ratingmovies.service;

import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.dao.impl.MovieDaoImpl;
import com.epam.ratingmovies.dao.impl.UserDaoImpl;
import com.epam.ratingmovies.exception.DaoException;
import com.epam.ratingmovies.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieService {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    MovieDaoImpl movieDao = new MovieDaoImpl();

    public int findMoviesAmount() throws DaoException {
        return movieDao.findMoviesAmount();
    }

    public Movie findMovieById(long id) throws DaoException, ServiceException {
        Optional<Movie> movie = movieDao.findById(id);
        if (movie.isPresent()) {
            return movie.get();
        } else {
            logger.error("movie not found.") ;
            throw new ServiceException("Error Service");
        }
    }


    public void save(Movie movie) throws DaoException {
        movieDao.save(movie);
    }

    public List findMovies() throws DaoException {
        return movieDao.findAll();
    }

    public List<Movie> findMoviesRange(int amountQuery, int size,List<Movie> movies) {
        int count = 0;

        //todo проверить правильно или не!!!!

        List<Movie> result = new ArrayList<>();

        for (Movie movie :movies){
            if (amountQuery < count && count <= amountQuery + size) {
                result.add(movie);
            }
            count++;
        }

        return result;



    }
}
