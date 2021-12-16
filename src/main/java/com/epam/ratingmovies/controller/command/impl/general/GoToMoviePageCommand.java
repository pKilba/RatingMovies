package com.epam.ratingmovies.controller.command.impl.general;

import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.CommandName;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.service.CommentService;
import com.epam.ratingmovies.service.MovieService;
import com.epam.ratingmovies.service.UserService;
import com.epam.ratingmovies.util.Attribute;

public class GoToMoviePageCommand implements Command {
    private static final String RATING_MOVIES_COMMAND = "ratingMovies?command=" + CommandName.MOVIE_PAGE + "&id=";
    public static final String MOVIE = "/jsp/pages/movie.jsp";
    public static final MovieService movieService =MovieService.getInstance();
    public static final CommentService commentService = CommentService.getInstance();
    public static final UserService userService = new UserService();


    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        long id = ParameterTaker.takeId(request);
        Movie movie = movieService.findMovieById(id);
        request.addAttribute(Attribute.MOVIE, movie);
        request.addAttribute(Attribute.ID, id);
        return CommandResponse.forward(MOVIE);
    }
}
