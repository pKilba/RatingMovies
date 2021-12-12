package com.epam.ratingmovies.controller.command.impl.user;

import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.exception.DaoException;
import com.epam.ratingmovies.util.Attribute;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.*;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.Comment;
import com.epam.ratingmovies.dao.impl.CommentDaoImpl;
import com.epam.ratingmovies.service.UserService;
import com.google.protobuf.ServiceException;

import java.sql.Timestamp;

public class AddCommentCommand implements Command {
    public static final String MOVIE = "/jsp/pages/movie.jsp";
    public static final UserService userService = new UserService();

    private static final String RATING_MOVIES_COMMAND = "ratingMovies?command=" + CommandName.MOVIE_PAGE + "&id=";

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException, DaoException {
        long id = ParameterTaker.takeId(request);
        long idMovie = ParameterTaker.takeIdNow(request);
        Comment newComment = Comment.builder().
                setMessage(request.getRequestParameter("leaveComment"))
                .setMovie(idMovie).
                setUser(id).
                setCreateTime(new Timestamp(System.currentTimeMillis())).build();
        CommentDaoImpl commentDao = new CommentDaoImpl();
        Comment comment = commentDao.save(newComment);


        request.addAttribute(Attribute.ID, idMovie);
        return CommandResponse.redirect(RATING_MOVIES_COMMAND + idMovie);

    }
}
