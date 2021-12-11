package com.epam.ratingmovies.controller.command.impl.general;

import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;

public class GoToLoginPageCommand implements Command {
    public static final String LOGIN = "/jsp/pages/login.jsp";

    @Override
    public CommandResponse execute(RequestContext request) {
        return CommandResponse.forward(LOGIN);
    }
}
