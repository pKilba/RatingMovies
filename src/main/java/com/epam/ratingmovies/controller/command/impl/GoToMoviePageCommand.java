package com.epam.ratingmovies.controller.command.impl;

import com.epam.ratingmovies.Attribute;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.impl.MovieDaoImpl;
import com.epam.ratingmovies.service.MovieService;
import com.google.protobuf.ServiceException;

public class GoToMoviePageCommand implements Command {
    public static final String CINEMA = "/jsp/pages/cinema.jsp";
    public static final MovieService movieService = new MovieService();

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        int id = (int) ParameterTaker.takeId(request);

        //todo хуита
        Movie movie = movieService.findMovieById(id);
        request.addAttribute(Attribute.CINEMA, movie);
        return CommandResponse.forward(CINEMA);

    }
}
