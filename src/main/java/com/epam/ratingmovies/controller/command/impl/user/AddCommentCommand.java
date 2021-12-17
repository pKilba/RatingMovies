package com.epam.ratingmovies.controller.command.impl.user;

import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.CommandName;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.Comment;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.service.CommentService;
import com.epam.ratingmovies.util.Attribute;

import java.sql.Timestamp;

public class AddCommentCommand implements Command {

    private static final String RATING_MOVIES_COMMAND = "ratingMovies?command=" + CommandName.MOVIE_PAGE + "&id=";
    private static final CommentService commentService = CommentService.getInstance();
    private static final String LeaveCommentParameter = "leaveComment";


    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        long id = ParameterTaker.takeId(request);
        long idMovie = ParameterTaker.takeIdNow(request);
        Comment newComment = Comment.builder().
                setMessage(request.getRequestParameter(LeaveCommentParameter))
                .setMovie(idMovie).
                setUser(id).
                setCreateTime(new Timestamp(System.currentTimeMillis())).build();
        commentService.save(newComment);
        request.addAttribute(Attribute.ID, idMovie);
        return CommandResponse.redirect(RATING_MOVIES_COMMAND + idMovie);
    }
}
