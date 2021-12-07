package com.epam.ratingmovies.controller.command;

import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.google.protobuf.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.text.ParseException;

public interface Command {

    CommandResponse execute(RequestContext request) throws ServiceException, ParseException, SQLException; //throws ServiceException, InvalidParametersException;;

    static Command withName(String name) {
        return ApplicationCommand.of(name);
    }

}
