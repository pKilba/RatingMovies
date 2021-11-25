package com.epam.ratingmovies.controller.command.impl;

import com.epam.ratingmovies.Attribute;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.Parameter;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.entity.UserRole;
import com.epam.ratingmovies.dao.entity.UserStatus;
import com.epam.ratingmovies.service.SignUpService;

import java.sql.Timestamp;

public class SignUpCommand implements Command {

    public static final String SIGN_UP = "/jsp/pages/sign-up.jsp";
    public static final String LOGIN = "/jsp/pages/login.jsp";
    public final SignUpService signUpService = SignUpService.getInstance();


    @Override
    public CommandResponse execute(RequestContext requestContext) {
        String login = ParameterTaker.takeString(Parameter.LOGIN, requestContext);
        String email = ParameterTaker.takeString(Parameter.EMAIL, requestContext);
        String telegram = ParameterTaker.takeString(Parameter.TELEGRAM,requestContext);
        boolean isLoginOrMailOrTelegramExist = false;
        isLoginOrMailOrTelegramExist = signUpService.isUserLoginExist(login) || signUpService.isUserEmailExist(email) || signUpService.isUserTelegramExist(telegram);
        if (!isLoginOrMailOrTelegramExist) {
            requestContext.addAttribute(Attribute.SAVED_LOGIN, login);
            requestContext.addAttribute(Attribute.SAVED_EMAIL, email);
            Timestamp nowTime = new Timestamp(System.currentTimeMillis());
            String password = ParameterTaker.takeString(Parameter.PASSWORD, requestContext);

            User user = User.builder().
                    setLogin(login).
                    setPassword(password).
                    setUserRole(UserRole.USER).
                    setName(ParameterTaker.takeString(Parameter.NAME, requestContext)).
                    setEmail(ParameterTaker.takeString(Parameter.EMAIL, requestContext)).
                    setTelegram(ParameterTaker.takeString(Parameter.TELEGRAM, requestContext)).
                    setUserStatus(UserStatus.Asleep).
                    setCreateTime(nowTime).
                    setProfilePicture(100 - 7).build();

            signUpService.signUp(user);

            return CommandResponse.forward(LOGIN);
        }
        System.out.println("Такой пользователь уже есть ");
        return CommandResponse.forward(SIGN_UP);

    }
}

