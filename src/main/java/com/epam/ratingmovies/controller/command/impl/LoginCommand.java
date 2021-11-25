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
import jakarta.servlet.http.Cookie;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoginCommand implements Command {

    private static final String PROFILE_PAGE_COMMAND = "/jsp/pages/profile.jsp" ;


    @Override
    public CommandResponse execute(RequestContext request) {
        String login = ParameterTaker.takeString(Parameter.LOGIN, requestContext);
        String pass = ParameterTaker.takeString(Parameter.PASSWORD, requestContext);
        LineHasher lineHasher = new LineHasher();
      //  String hashPass = lineHasher.hashingLine(pass);
        boolean isUserExist = service.isUserExistByLoginAndPassword(login, hashPass);
        if (isUserExist) {
            User user = service.findUserByLogin(login);
            if (user.getUserStatus() != UserStatus.BANNED) {
                long id = user.getUserId();
                UserRole role = user.getUserRole();
                requestContext.addSession(Attribute.USER_ID, id);
                requestContext.addSession(Attribute.ROLE, role);
                requestContext.addSession(Attribute.LOGIN, user.getLogin());
                ProfilePlayer profilePlayer = profilePlayerService.findProfilePlayerById(id);
                requestContext.addSession(Attribute.PHOTO, profilePlayer.getPhoto());
                //create jwt token
                Map<String, String> claims = new HashMap<>();
                claims.put(Attribute.USER_ID, String.valueOf(id));
                claims.put(Attribute.ROLE, String.valueOf(role));
                claims.put(Attribute.LOGIN, user.getLogin());
                String token = jwtProvider.generateToken(claims);
                Cookie cookie = new Cookie(Attribute.ACCESS_TOKEN, token);
                cookie.setHttpOnly(true);
                cookie.setMaxAge(TIMEZONE_GMT_PLUS_THREE +
                        (int) (TimeUnit.MINUTES.toSeconds(LIFE_TIME_COOKIE)));
                requestContext.addCookie(cookie);
                return CommandResponse.redirect(PROFILE_PAGE_COMMAND + id);
            }
            requestContext.addAttribute(Attribute.ERROR_MESSAGE, BANNED_USER_KEY);
        } else {
            requestContext.addAttribute(Attribute.ERROR_MESSAGE, INCORRECT_DATA_KEY);
        }
        requestContext.addAttribute(Attribute.SAVED_LOGIN, login);
        return CommandResponse.forward(PagePath.LOGIN);
    }
    }
}
