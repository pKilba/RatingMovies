package com.epam.ratingmovies.controller.command.api;

import com.epam.ratingmovies.controller.command.ApplicationCommand;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.exception.ServiceException;

public interface Command {

    CommandResponse execute(RequestContext request) throws ServiceException;

    static Command withName(String name) {
        return ApplicationCommand.of(name);
    }

}
