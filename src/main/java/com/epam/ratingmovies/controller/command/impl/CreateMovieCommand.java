package com.epam.ratingmovies.controller.command.impl;

import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.Parameter;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.google.protobuf.ServiceException;

import java.sql.Timestamp;

public class CreateMovieCommand implements Command {
    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        System.out.println("f");
        System.out.println("f");
        System.out.println("f");

        String about = ParameterTaker.takeString("about", request);
        String image = ParameterTaker.takeString("image", request);
        String timestamp = ParameterTaker.takeString("data",request);
        int like = ParameterTaker.takeNumber("like",request);
        int dislike = ParameterTaker.takeNumber("dislike",request);
        System.out.println("fff");



        return null ;
    }
}
