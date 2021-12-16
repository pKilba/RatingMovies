package com.epam.ratingmovies.controller.command.impl.admin;

import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.exception.ServiceException;


public class GoToAddMoviePageCommand implements Command {
    public static final String CREATE_MOVIE = "/jsp/pages/createMovie.jsp";


    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {




        return CommandResponse.forward(CREATE_MOVIE);
    }
}
