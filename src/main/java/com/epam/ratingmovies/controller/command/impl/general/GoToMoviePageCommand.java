package com.epam.ratingmovies.controller.command.impl.general;

import com.epam.ratingmovies.Attribute;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandName;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.Comment;
import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.impl.CommentDaoImpl;
import com.epam.ratingmovies.service.CommentService;
import com.epam.ratingmovies.service.MovieService;
import com.epam.ratingmovies.service.UserService;
import com.google.protobuf.ServiceException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GoToMoviePageCommand implements Command {
    private static final String RATING_MOVIES_COMMAND = "ratingMovies?command=" + CommandName.MOVIE_PAGE + "&id=";
    public static final String MOVIE = "/jsp/pages/movie.jsp";
    public static final MovieService movieService = new MovieService();
    public static final CommentService commentService = new CommentService();
    public static final UserService userService = new UserService();


    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        long id = ParameterTaker.takeId(request);
        List<User> users = new ArrayList<>();
        Movie movie = movieService.findMovieById(id);
        request.addAttribute(Attribute.MOVIE, movie);
        request.addAttribute(Attribute.ID, id);
      /*  if (request.getRequestParameter("idUser") != null) {
            return CommandResponse.redirect(RATING_MOVIES_COMMAND + id);
        }*/
        return CommandResponse.forward(MOVIE);
    }
}
