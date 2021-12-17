package com.epam.ratingmovies.controller.command.impl.admin;

import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.controller.command.util.Parameter;
import com.epam.ratingmovies.dao.entity.Genre;
import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.service.MovieService;
import com.epam.ratingmovies.util.Attribute;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class CreateMovieCommand implements Command {

    private static final String INVALID_DATA_KEY = "invalid.data";
    private static final String VALID_DATA_KEY = "success";
    private static final MovieService movieService = MovieService.getInstance();
    private static final String MOVIE = "/jsp/pages/createMovie.jsp";

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {

        String about = ParameterTaker.takeString(Parameter.ABOUT, request);
        String image = ParameterTaker.takeString(Parameter.IMAGE_FILM, request);
        String str = ParameterTaker.takeString(Parameter.DATA, request);
        LocalDateTime dateTime = LocalDate.parse(str).atStartOfDay();
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        int like = ParameterTaker.takeNumber(Parameter.LIKE, request);
        int dislike = ParameterTaker.takeNumber(Parameter.DISLIKE, request);
        String name = ParameterTaker.takeString(Parameter.NAME, request);
        String producer = ParameterTaker.takeString(Parameter.PRODUCER, request);
        int duration = ParameterTaker.takeNumber(Parameter.DURATION, request);
        int idGenre = ParameterTaker.takeNumber(Parameter.GENRE, request);
        String imageBack = ParameterTaker.takeString(Parameter.IMAGE_FILM_BACK, request);
        if (movieService.isValid(name, like, dislike, duration, producer, about)) {
            request.addAttribute(Attribute.SUCCESS_MESSAGE, VALID_DATA_KEY);
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
        } else {
            request.addAttribute(Attribute.ERROR_MESSAGE, INVALID_DATA_KEY);
        }

        return CommandResponse.forward(MOVIE);
    }
}
