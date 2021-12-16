package com.epam.ratingmovies.controller.command.impl.general;

import com.epam.ratingmovies.service.MoviesPagesWithPagination;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.service.MovieService;

public class GoToMoviesPageCommand implements Command {

    public static final MovieService movieService = new MovieService();
    public static final String MOVIES = "/jsp/pages/movies.jsp" ;

    @Override
    public CommandResponse execute(RequestContext request) throws Exception {


        MoviesPagesWithPagination moviesPagesWithPagination = new MoviesPagesWithPagination();
        moviesPagesWithPagination.processCommandWithPagination(request);
        return CommandResponse.forward(MOVIES);
    }
}
