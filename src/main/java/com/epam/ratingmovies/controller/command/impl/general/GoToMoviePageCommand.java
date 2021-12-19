package com.epam.ratingmovies.controller.command.impl.general;

import com.epam.ratingmovies.util.ParameterTaker;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.service.MovieService;
import com.epam.ratingmovies.util.Attribute;

public class GoToMoviePageCommand implements Command {
    private static final String MOVIE = "/jsp/pages/movie.jsp";
    private static final MovieService movieService = MovieService.getInstance();

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        long id = ParameterTaker.takeId(request);
        Movie movie = movieService.findMovieById(id);
        request.addAttribute(Attribute.MOVIE, movie);
        request.addAttribute(Attribute.ID, id);
        return CommandResponse.forward(MOVIE);
    }
}
