package com.epam.ratingmovies.controller.command.impl.user;

import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.controller.command.util.Parameter;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.service.AccountChangePassword;
import com.epam.ratingmovies.service.UserService;
import com.epam.ratingmovies.util.Attribute;
import com.epam.ratingmovies.util.LineHasher;

public class ChangePasswordCommand implements Command {
    private static final String INVALID_DATA_KEY = "invalid.pass";
    private static final String VALID_DATA_KEY = "success.pass";
    UserService userService = new UserService();
    AccountChangePassword accountChangePassword = new AccountChangePassword();
    public static final String SETTINGS = "/jsp/pages/account-settings.jsp";
    LineHasher lineHasher = new LineHasher();

    @Override
    public CommandResponse execute(RequestContext requestContext)
            throws ServiceException {
        long id = ParameterTaker.takeId(requestContext);
        User user = userService.findUserById(id);
        requestContext.addAttribute(Attribute.USER, user);
        String currentPassword = ParameterTaker.takeString(Parameter.CURRENT_PASSWORD, requestContext);
        String newPasswordFirst = ParameterTaker.takeString(Parameter.NEW_PASSWORD_FIRST, requestContext);
        String newPasswordSecond = ParameterTaker.takeString(Parameter.NEW_PASSWORD_SECOND, requestContext);
        String hashCurrentPassword = lineHasher.hashingLine(currentPassword);
        String hashNewPasswordFirst = lineHasher.hashingLine(newPasswordFirst);
        if (accountChangePassword.isCorrectPassword(newPasswordFirst, newPasswordSecond, user, hashCurrentPassword)) {
            userService.updatePasswordByUserId(id, hashNewPasswordFirst);
            requestContext.addAttribute(Attribute.SUCCESS_MESSAGE, VALID_DATA_KEY);
            return CommandResponse.forward(SETTINGS);
        }
        requestContext.addAttribute(Attribute.ERROR_MESSAGE, INVALID_DATA_KEY);
        return CommandResponse.forward(SETTINGS);
    }
}
