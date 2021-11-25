package com.epam.ratingmovies.controller.command;

import com.epam.ratingmovies.controller.command.request.RequestContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {

    CommandResponse execute(RequestContext request); //throws ServiceException, InvalidParametersException;;

    static Command withName(String name) {
        return ApplicationCommand.of(name);
    }

}
