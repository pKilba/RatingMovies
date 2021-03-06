package com.epam.ratingmovies.controller.command.impl.general;

import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;

public class GoToSignUpPageCommand implements Command {

    private static final String SIGN_UP = "/jsp/pages/sign-up.jsp";

    @Override
    public CommandResponse execute(RequestContext request) {

        return CommandResponse.forward(SIGN_UP);
    }
}
