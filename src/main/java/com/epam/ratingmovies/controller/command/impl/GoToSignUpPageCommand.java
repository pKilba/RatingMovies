package com.epam.ratingmovies.controller.command.impl;

import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;

public class GoToSignUpPageCommand implements Command {

    public static final String SIGN_UP = "/jsp/pages/sign-up.jsp";

    @Override
    public CommandResponse execute(RequestContext request) {

        return CommandResponse.forward(SIGN_UP);
    }
}
