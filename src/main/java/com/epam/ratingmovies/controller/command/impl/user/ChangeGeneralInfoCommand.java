package com.epam.ratingmovies.controller.command.impl.user;

import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.util.Attribute;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.util.Parameter;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.service.AccountInfoChangeService;
import com.epam.ratingmovies.service.UserService;

public class ChangeGeneralInfoCommand implements Command {

    private static final String INVALID_DATA_KEY = "invalid.data";
    private static final String VALID_DATA_KEY = "success";
    private static final UserService userService = UserService.getInstance();
    public static final String USER_ACCOUNT_SETTINGS = "/jsp/pages/account-settings.jsp";
    private final AccountInfoChangeService accountInfoChangeService = AccountInfoChangeService.getInstance();

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {

        long id = ParameterTaker.takeId(request);
        String name = ParameterTaker.takeString(Parameter.NAME, request);
        String email = ParameterTaker.takeString(Parameter.EMAIL, request);
        String telegram = ParameterTaker.takeString(Parameter.TELEGRAM, request);
        User user = userService.findUserById(id);
        if (accountInfoChangeService.isValidInfoAccount(email, telegram, name)) {
            userService.updateNameEmailTelegramById(name, email, telegram, id);
            request.addAttribute(Attribute.SUCCESS_MESSAGE, VALID_DATA_KEY);
            return CommandResponse.forward(USER_ACCOUNT_SETTINGS);
        }
        request.addAttribute(Attribute.ERROR_MESSAGE, INVALID_DATA_KEY);
        request.addAttribute(Attribute.USER, user);
        return CommandResponse.forward(USER_ACCOUNT_SETTINGS);
    }
}
