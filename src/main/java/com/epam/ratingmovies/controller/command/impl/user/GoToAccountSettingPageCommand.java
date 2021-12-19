package com.epam.ratingmovies.controller.command.impl.user;

import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.service.UserService;
import com.epam.ratingmovies.util.Attribute;

public class GoToAccountSettingPageCommand implements Command {


    private static final String SETTING = "/jsp/pages/account-settings.jsp";
    private static final UserService userService = new UserService();

    @Override
    public CommandResponse execute(RequestContext requestContext) throws ServiceException {

        long id = ParameterTaker.takeId(requestContext);
        User user = userService.findUserById(id);
        requestContext.addAttribute(Attribute.USER, user);
        return CommandResponse.forward(SETTING);
    }
}
