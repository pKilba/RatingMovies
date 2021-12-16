package com.epam.ratingmovies.controller.command.api;

import com.epam.ratingmovies.controller.command.ApplicationCommand;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.exception.DaoException;
import com.google.protobuf.ServiceException;

import java.sql.SQLException;
import java.text.ParseException;

public interface Command {

    CommandResponse execute(RequestContext request) throws Exception; //throws ServiceException, InvalidParametersException;;

    static Command withName(String name) {
        return ApplicationCommand.of(name);
    }

}
