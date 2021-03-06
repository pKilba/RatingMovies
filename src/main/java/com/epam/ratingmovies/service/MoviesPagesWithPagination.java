package com.epam.ratingmovies.service;

import com.epam.ratingmovies.dao.entity.MovieDto;
import com.epam.ratingmovies.util.ParameterTaker;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.controller.command.util.Parameter;
import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.util.Attribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MoviesPagesWithPagination {

    private static final MovieService movieService = MovieService.getInstance();
    private static final Logger logger = LogManager.getLogger();
    private static final String INVALID_PAGE_OR_SIZE = "Invalid page or size!";
    private static final String PARAMETER_INVALID = "Parameter in query invalid";

    private static MoviesPagesWithPagination instance;

    private MoviesPagesWithPagination() {

    }

    public static MoviesPagesWithPagination getInstance() {
        if (instance == null) {
            instance = new MoviesPagesWithPagination();
        }
        return instance;
    }


    public void processCommandWithPagination(RequestContext requestContext) throws ServiceException {
        int page = ParameterTaker.takeNumber(Parameter.PAGE, requestContext);
        int size = ParameterTaker.takeNumber(Parameter.SIZE, requestContext);
        long amount = movieService.findMoviesAmount();
        long amountQuery = (long) (page - 1) * size;
        if (amountQuery > amount) {
            logger.warn(PARAMETER_INVALID);
            throw new ServiceException(PARAMETER_INVALID);
        }
        if (amount < size) {
            size = (int) amount;
        }
        List<Movie> movieList1 = buildMovieList(page, size);

        List<MovieDto> movieList = new ArrayList<>();
        for (Movie movie : movieList1) {

            MovieDto movieDto = new MovieDto(movie.getName(), movie.getPoster(), movie.getAbout(),
                    movie.getReleaseDate().toString().substring(0, 11), movie.getamountLike(),
                    movie.getamountDislike(), movie.getGenre(), movie.getProducer(),
                    movie.getDuration(), movie.getBackground());
            movieDto.setId(movie.getId());

            movieList.add(movieDto);
        }


        requestContext.addAttribute(Attribute.MOVIE_LIST, movieList);


        requestContext.addAttribute(Attribute.CURRENT_PAGE, page);
        int maxPage = (int) (amount / size);
        if (amount % size != 0) {
            ++maxPage;
        }
        requestContext.addAttribute(Attribute.AMOUNT_OF_PAGE, size);
        requestContext.addAttribute(Attribute.MAX_PAGE, maxPage);
    }

    private List<Movie> buildMovieList(int page, int size) throws ServiceException {
        int offset = (page - 1) * size;
        List<Movie> movieList;
        try {
            movieList = movieService.findMoviesRange(offset, size);
        } catch (ServiceException e) {
            logger.warn(INVALID_PAGE_OR_SIZE + e);
            throw new ServiceException(INVALID_PAGE_OR_SIZE);
        }

        return movieList;
    }

}
