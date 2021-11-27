package com.epam.ratingmovies.controller.command.impl;

import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.google.protobuf.ServiceException;

public class GoToMoviesPageCommand implements Command {

    public static final String GAME = "/jsp/pages/movies.jsp";

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        return CommandResponse.forward(GAME);
    }
}
