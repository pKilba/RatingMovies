package com.epam.ratingmovies.controller.command.impl;

import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.google.protobuf.ServiceException;

import java.text.ParseException;

public class ChangeGeneralInfoProfile implements Command {

    public static final String HOME = "/jsp/pages/home.jsp";

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException, ParseException {
        return CommandResponse.forward(HOME);
    }
}
