package com.epam.ratingmovies.controller.command.impl.user;

import com.epam.ratingmovies.util.Attribute;
import com.epam.ratingmovies.util.LineHasher;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.util.Parameter;
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
    private static final String PRE_PHOTO = "notAva.jpg";
    private static final String USERNAME_EXIST_KEY = "username.exist";
    private static final String INVALID_DATA_KEY = "invalid.data";


    @Override
    public CommandResponse execute(RequestContext requestContext) {
        String login = ParameterTaker.takeString(Parameter.LOGIN, requestContext);
        String email = ParameterTaker.takeString(Parameter.EMAIL, requestContext);
        String telegram = ParameterTaker.takeString(Parameter.TELEGRAM, requestContext);
        boolean isLoginOrMailOrTelegramExist = false;
        isLoginOrMailOrTelegramExist = signUpService.isUserLoginExist(login) || signUpService.isUserEmailExist(email) || signUpService.isUserTelegramExist(telegram);
        if (!isLoginOrMailOrTelegramExist) {
            requestContext.addAttribute(Attribute.SAVED_LOGIN, login);
            requestContext.addAttribute(Attribute.SAVED_EMAIL, email);
            Timestamp nowTime = new Timestamp(System.currentTimeMillis());
            String password = ParameterTaker.takeString(Parameter.PASSWORD, requestContext);
            LineHasher lineHasher = new LineHasher();
            User user = User.builder().
                    setLogin(login).
                    setPassword(lineHasher.hashingLine(password)).
                    setUserRole(UserRole.USER).
                    setName(ParameterTaker.takeString(Parameter.NAME, requestContext)).
                    setEmail(ParameterTaker.takeString(Parameter.EMAIL, requestContext)).
                    setTelegram(ParameterTaker.takeString(Parameter.TELEGRAM, requestContext)).
                    setUserStatus(UserStatus.ACTIVE).
                    setCreateTime(nowTime).
                    setProfilePicture(PRE_PHOTO).build();

            long idUser = signUpService.signUp(user);
            if (idUser != -1) {
                return CommandResponse.forward(LOGIN);
            } else {
                requestContext.addAttribute(Attribute.ERROR_MESSAGE, INVALID_DATA_KEY);
            }
        } else {
            requestContext.addAttribute(Attribute.ERROR_MESSAGE, USERNAME_EXIST_KEY);


        }
        return CommandResponse.forward(SIGN_UP);

    }
}

