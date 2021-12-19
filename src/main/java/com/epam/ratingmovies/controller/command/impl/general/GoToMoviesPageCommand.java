package com.epam.ratingmovies.controller.command.impl.general;

import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.service.MoviesPagesWithPagination;

public class GoToMoviesPageCommand implements Command {

    private static final String MOVIES = "/jsp/pages/movies.jsp";
    private static final MoviesPagesWithPagination moviesPagesWithPagination = MoviesPagesWithPagination.getInstance();


    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {

        moviesPagesWithPagination.processCommandWithPagination(request);
        return CommandResponse.forward(MOVIES);
    }
}
