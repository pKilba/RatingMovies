package com.epam.ratingmovies.controller.command.impl;

import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.google.protobuf.ServiceException;

import java.text.ParseException;

public class ActionBanUserCommand implements Command {
    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException, ParseException {
        System.out.println();

        return null;
    }
}
