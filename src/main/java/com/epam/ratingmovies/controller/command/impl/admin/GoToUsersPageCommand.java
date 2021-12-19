package com.epam.ratingmovies.controller.command.impl.admin;

import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.service.UsersPagesWithPagination;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;

public class GoToUsersPageCommand implements Command {
    private static final String USERS = "/jsp/pages/users.jsp";
    private static final UsersPagesWithPagination usersPagesWithPagination = UsersPagesWithPagination.getInstance();


    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {

        usersPagesWithPagination.processCommandWithPagination(request);
        return CommandResponse.forward(USERS);
    }
}
