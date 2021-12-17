package com.epam.ratingmovies.controller.command.impl.admin;

import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.service.AdminService;

public class ActionBanUserCommand implements Command {

   private static final AdminService adminService = AdminService.getInstance();

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        String response = adminService.banUserById(request);
        CommandResponse commandResult = new CommandResponse("json", false);
        commandResult.setLine(response);
        return commandResult;
    }
}
