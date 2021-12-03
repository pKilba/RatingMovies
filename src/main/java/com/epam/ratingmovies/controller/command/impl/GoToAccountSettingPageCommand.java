package com.epam.ratingmovies.controller.command.impl;

import com.epam.ratingmovies.Attribute;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.service.UserService;
import com.google.protobuf.ServiceException;
import jakarta.servlet.http.Cookie;

import java.text.ParseException;

public class GoToAccountSettingPageCommand implements Command {


    public static final String SETTING = "/jsp/pages/account-settings.jsp";
    public static final UserService userService = new UserService();


    @Override
    public CommandResponse execute(RequestContext requestContext) throws ServiceException {

        long id = ParameterTaker.takeId(requestContext);
        User user = userService.findUserById(id);
        requestContext.addAttribute(Attribute.USER, user);
        return CommandResponse.forward(SETTING);
    }
}
