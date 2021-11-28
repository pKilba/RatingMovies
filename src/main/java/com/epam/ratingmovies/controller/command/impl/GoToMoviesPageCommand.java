package com.epam.ratingmovies.controller.command.impl;

import com.epam.ratingmovies.Attribute;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.Parameter;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.service.MovieService;
import com.google.protobuf.ServiceException;

import java.util.List;

public class GoToMoviesPageCommand implements Command {

    public static final MovieService movieService = new MovieService();
    public static final String MOVIES = "/jsp/pages/movies.jsp";

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        List<Movie> movieList;
        int page = ParameterTaker.takeNumber(Parameter.PAGE, request);
      //todo разобраться с page and size wtf отрицательное
        if (page < 0 )
            page = 1 ;
        int size = ParameterTaker.takeNumber(Parameter.SIZE, request);
        if (size <0 )
            size = 10;
        int amount = movieService.findMoviesAmount();
        int amountQuery = (page - 1) * size;
        if (amountQuery > amount) {
        }
        if (amount < size) {
            size = (int) amount;
        }
        movieList= movieService.findMoviesRange(amountQuery, size);
        request.addAttribute(Attribute.CURRENT_PAGE, page);
        int maxPage = (int) (amount / size);
        if (amount % size != 0) {
            ++maxPage;
        }
        request.addAttribute(Attribute.AMOUNT_OF_PAGE, size);
        request.addAttribute(Attribute.MAX_PAGE, maxPage);
        request.addAttribute(Attribute.MOVIE_LIST, movieList);
        return CommandResponse.forward(MOVIES);
    }
}
