package com.epam.ratingmovies.controller.command.impl;

import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;

public class LoginCommand implements Command {

    private static final String PROFILE_PAGE_COMMAND = "/jsp/pages/profile.jsp" ;


    @Override
    public CommandResponse execute(RequestContext request) {
        System.out.println("Вы на страничке авторизации , Поздравляю!! Вы лучший");

        return  CommandResponse.redirect(PROFILE_PAGE_COMMAND );
    }
}
