package com.epam.ratingmovies.controller.command.impl.admin;

import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.Genre;
import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.service.MovieService;
import com.google.protobuf.ServiceException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class CreateMovieCommand implements Command {

    MovieService movieService = new MovieService();
    public static final String MOVIE = "/jsp/pages/createMovie.jsp";

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException, ParseException {

        String about = ParameterTaker.takeString("about", request);

        String image = ParameterTaker.takeString("img", request);

        String str = ParameterTaker.takeString("data", request);
        LocalDateTime dateTime = LocalDate.parse(str).atStartOfDay();
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        int like = ParameterTaker.takeNumber("like", request);
        int dislike = ParameterTaker.takeNumber("dislike", request);
        String name = ParameterTaker.takeString("name", request);
        String producer = ParameterTaker.takeString("producer", request);
        int duration = ParameterTaker.takeNumber("duration", request);
        int idGenre = ParameterTaker.takeNumber("genre", request);
        String imageBack = ParameterTaker.takeString("imgBack", request);
        Movie movie = Movie.builder()
                .setAbout(about).
                setMovieGenre(Genre.getById(idGenre)).
                setPoster(image).
                setAmountDislike(dislike).
                setAmountLike(like).
                setReleaseTime(timestamp).
                setMovieDuration(duration).
                setMovieProducer(producer).
                setMovieName(name).
                setMovieBackground(imageBack).
                build();

        movieService.save(movie);

        return CommandResponse.redirect(MOVIE);
    }
}