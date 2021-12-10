package com.epam.ratingmovies.controller.command.impl.admin;

import com.epam.ratingmovies.Attribute;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.Genre;
import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.service.MovieService;
import com.google.protobuf.ServiceException;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class CreateMovieCommand implements Command {

    MovieService movieService = new MovieService();
    public static final String MOVIE = "/jsp/pages/createMovie.jsp";

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException, ParseException {

        String about = ParameterTaker.takeString("about", request);

      // String image ="/images/photo/"+(String)request.getSessionAttribute(Attribute.PHOTO_MOVIE);
       String image ="/images/photo/"+ ParameterTaker.takeString("img",request);

        String str =ParameterTaker.takeString("data", request);

        // you can change format of dateSi
        Timestamp timestamp = Timestamp.valueOf(str);
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

        return CommandResponse.redirect(MOVIE);
    }
}
