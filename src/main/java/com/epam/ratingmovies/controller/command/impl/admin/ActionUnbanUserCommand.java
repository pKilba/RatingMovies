package com.epam.ratingmovies.controller.command.impl.admin;

import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.service.AdminService;


public class ActionUnbanUserCommand implements Command {

    private static final AdminService adminService = AdminService.getInstance();
    private static final String JSON = "json";

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        String response = adminService.unbanUserById(request);
        CommandResponse commandResult = new CommandResponse(JSON, false);
        commandResult.setLine(response);
        return commandResult;
    }
}
