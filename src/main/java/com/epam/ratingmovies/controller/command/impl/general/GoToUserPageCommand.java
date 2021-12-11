package com.epam.ratingmovies.controller.command.impl.general;

import com.epam.ratingmovies.util.Attribute;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.service.UserService;
import com.google.protobuf.ServiceException;

public class GoToUserPageCommand implements Command {

    public static final String USER = "/jsp/pages/profile.jsp";
    private static final UserService userService = UserService.getInstance();

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        long id = ParameterTaker.takeId(request);
        User user = userService.findUserById(id);
        request.addAttribute(Attribute.USER, user);
        return CommandResponse.forward(USER);
    }
    }
