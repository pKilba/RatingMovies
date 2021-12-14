package com.epam.ratingmovies.controller.command.impl.general;

import com.epam.ratingmovies.exception.DaoException;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.util.Attribute;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.util.Parameter;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.service.MovieService;

import java.util.List;

public class GoToMoviesPageCommand implements Command {

    public static final MovieService movieService = new MovieService();
    public static final String MOVIES = "/jsp/pages/movies.jsp";

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        List movieList;
        int page = ParameterTaker.takeNumber(Parameter.PAGE, request);
      //todo разобраться с page and size wtf отрицательное
        if (page < 0 )
            page = 1 ;
        int size = ParameterTaker.takeNumber(Parameter.SIZE, request);
        if (size <1 )
            size = 10;
        int amount = movieService.findMoviesAmount();
        int amountQuery = (page ) * size;
        if (amountQuery > amount) {
        }
        if (amount < size) {
            size = amount;
        }
        movieList = movieService.findMovies();

        //convert movie
        List movieListByRange;
if (movieList.size() > 10) {
     movieListByRange = movieService.findMoviesRange(amount - amountQuery, size, movieList);
}
movieListByRange = movieList;
       request.addAttribute(Attribute.CURRENT_PAGE, page);
        int maxPage = amount / size;
        if (amount % size != 0) {
            ++maxPage;
        }
        request.addAttribute(Attribute.AMOUNT_OF_PAGE, size);
        request.addAttribute(Attribute.MAX_PAGE, maxPage);
        request.addAttribute(Attribute.MOVIE_LIST, movieListByRange);

        return CommandResponse.forward(MOVIES);
    }
}
