package com.epam.ratingmovies.controller.command.impl;

import com.epam.ratingmovies.Attribute;
import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.google.protobuf.ServiceException;

public class LogOutCommand implements Command {

    public static final String MOVIES = "/jsp/pages/movies.jsp";
    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        request.addSession(Attribute.INVALIDATE_ATTRIBUTE, true);
        return CommandResponse.redirect(MOVIES);
    }
}
