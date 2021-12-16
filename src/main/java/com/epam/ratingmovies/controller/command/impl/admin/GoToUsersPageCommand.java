package com.epam.ratingmovies.controller.command.impl.admin;

import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.service.UsersPagesWithPagination;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;

public class GoToUsersPageCommand implements Command {
    public static final String USERS = "/jsp/pages/users.jsp";

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {

        UsersPagesWithPagination usersPagesWithPagination = UsersPagesWithPagination.getInstance();
        usersPagesWithPagination.processCommandWithPagination(request);
        return CommandResponse.forward(USERS);
    }
}
