package com.epam.ratingmovies.controller.command.impl;

import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.google.protobuf.ServiceException;

import java.sql.SQLException;
import java.text.ParseException;


public class GoToShowYourCommentsCommand implements Command {

    public static final String SHOW_YOUR_COMMENTS = "/jsp/pages/yourComments.jsp";


    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException, ParseException, SQLException {
        return CommandResponse.forward(SHOW_YOUR_COMMENTS);
    }
}
