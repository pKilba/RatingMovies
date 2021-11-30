package com.epam.ratingmovies.controller.command.impl;

import com.epam.ratingmovies.Attribute;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.*;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.Comment;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.impl.CommentDaoImpl;
import com.epam.ratingmovies.service.UserService;
import com.fasterxml.jackson.databind.introspect.AnnotationMap;
import com.google.protobuf.ServiceException;

import java.sql.Timestamp;

public class AddCommentCommand implements Command {
    public static final String MOVIE = "/jsp/pages/movie.jsp";
    public static final UserService userService = new UserService();

    private static final String RATING_MOVIES_COMMAND = "ratingMovies?command=" + CommandName.MOVIE_PAGE + "&id=";

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        long id = ParameterTaker.takeId(request);
        long idUser = -1;
        User user;
        if (request.getRequestParameter("idUser") != null) {
            idUser = Long.parseLong(request.getRequestParameter("idUser"));
            user = userService.findUserById(idUser);
            Comment newComment = Comment.builder().
                    setMessage(request.getRequestParameter("leaveComment"))
                    .setMovie(id).
                    setUser(idUser).
                    setCreateTime(new Timestamp(System.currentTimeMillis())).build();
            CommentDaoImpl commentDao = new CommentDaoImpl();
            Comment comment = commentDao.save(newComment);
        }
        request.addAttribute(Attribute.ID, id);


        return CommandResponse.redirect(RATING_MOVIES_COMMAND + id);

    }
}