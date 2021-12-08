package com.epam.ratingmovies.controller.command.impl;

import com.epam.ratingmovies.Attribute;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandName;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.Parameter;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.service.UserService;
import com.epam.ratingmovies.service.validator.UserValidator;
import com.google.protobuf.ServiceException;

import java.sql.SQLException;
import java.text.ParseException;

public class ChangePasswordCommand implements Command {
    private static final String PROFILE_PAGE_COMMAND = "poker?command=" + CommandName.PROFILE_PAGE + "&id=";
    private static final String INVALID_DATA_KEY = "invalid.pass";
    private static final String VALID_DATA_KEY = "success.pass";
    //private static final AccountInfoChangeService changeDataOfAccountUser = AccountInfoChangeServiceImpl.getInstance();
 //   private static final JwtProvider jwtProvider = JwtProvider.getInstance();
UserService userService = new UserService();
UserValidator userValidator =UserValidator.getInstance();
    @Override
    public CommandResponse execute(RequestContext requestContext)
            throws ServiceException, SQLException {
        long id = ParameterTaker.takeId(requestContext);
        User user = userService.findUserById(id);
        requestContext.addAttribute(Attribute.USER, user);
        String currentPassword = ParameterTaker.takeString(Parameter.CURRENT_PASSWORD, requestContext);
        String newPassword = ParameterTaker.takeString(Parameter.NEW_PASSWORD, requestContext);
        if (user.getPassword().equals(currentPassword) && userValidator.isValidPassword(newPassword) ){
            userService.updatePasswordByUserId(id,newPassword);
        }



        return CommandResponse.forward("");
    }
}
