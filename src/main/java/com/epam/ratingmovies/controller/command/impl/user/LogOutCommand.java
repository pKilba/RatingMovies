package com.epam.ratingmovies.controller.command.impl.user;

import com.epam.ratingmovies.util.Attribute;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.google.protobuf.ServiceException;

public class LogOutCommand implements Command {

    public static final String MOVIES = "/jsp/pages/login.jsp";
    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        request.addSession(Attribute.INVALIDATE_ATTRIBUTE, true);
        return CommandResponse.redirect(MOVIES);
    }
}
