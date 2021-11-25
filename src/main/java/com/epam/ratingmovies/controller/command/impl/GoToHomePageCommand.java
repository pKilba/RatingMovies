package com.epam.ratingmovies.controller.command.impl;

import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;

public class GoToHomePageCommand implements Command {
     public static final String HOME = "/jsp/pages/home.jsp";

    @Override
    public CommandResponse execute(RequestContext request) {
        return CommandResponse.forward(HOME);
    }
}
