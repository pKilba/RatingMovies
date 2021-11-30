package com.epam.ratingmovies.controller.command.impl;

import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.Parameter;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.Genre;
import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.service.MovieService;
import com.google.protobuf.ServiceException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class CreateMovieCommand implements Command {

    MovieService movieService = new MovieService();
    public static final String MOVIES = "/jsp/pages/createMovie.jsp";

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException, ParseException {

        String about = ParameterTaker.takeString("about", request);
        String image = ParameterTaker.takeString("image", request);
        String str = ParameterTaker.takeString("data", request);
        // you can change format of date

        LocalDateTime dateTime = LocalDate.parse(str).atStartOfDay();
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        int like = ParameterTaker.takeNumber("like", request);
        int dislike = ParameterTaker.takeNumber("dislike", request);
        String name = ParameterTaker.takeString("name", request);
        int idGenre = ParameterTaker.takeNumber("genre", request);
        Movie movie = Movie.builder()
                .setAbout(about).
                setMovieGenre(Genre.getById(idGenre)).
                setPoster(image).
                setAmountDislike(dislike).
                setAmountLike(like).
                setReleaseTime(timestamp).
                setMovieName(name).
                build();

        movieService.save(movie);

        return CommandResponse.forward(MOVIES);
    }
}
