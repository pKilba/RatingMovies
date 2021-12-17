package com.epam.ratingmovies.controller.command.impl.general;

import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.service.UserService;
import com.epam.ratingmovies.util.Attribute;


public class GoToUserPageCommand implements Command {

    private static final String USER = "/jsp/pages/profile.jsp";
    private static final UserService userService = UserService.getInstance();

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        long id = ParameterTaker.takeId(request);
        User user = userService.findUserById(id);
        request.addAttribute(Attribute.USER, user);
        return CommandResponse.forward(USER);
    }
}

