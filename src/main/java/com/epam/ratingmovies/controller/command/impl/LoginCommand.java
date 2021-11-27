package com.epam.ratingmovies.controller.command.impl;

import com.epam.ratingmovies.Attribute;
import com.epam.ratingmovies.LineHasher;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandName;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.Parameter;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.entity.UserRole;
import com.epam.ratingmovies.dao.entity.UserStatus;
import com.epam.ratingmovies.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class LoginCommand implements Command {

    private static final String PROFILE_PAGE_COMMAND = "ratingMovies?command=" + CommandName.PROFILE_PAGE + "&id=";
    private static final String INCORRECT_DATA_KEY = "incorrect";
    private static final String FREEZE_USER_KEY = "banned";
  //  private static final long LIFE_TIME_COOKIE = ConfigReaderJwt.getAccessTokenLifeTime();
    private static final UserService service = UserService.getInstance();
    private static final int TIMEZONE_GMT_PLUS_THREE = 60*60*3;
   // private static final JwtProvider jwtProvider = JwtProvider.getInstance();
    public static final String LOGIN = "/jsp/pages/login.jsp";


    @Override
    public CommandResponse execute(RequestContext request) {
        String login = ParameterTaker.takeString(Parameter.LOGIN, request);
        String pass = ParameterTaker.takeString(Parameter.PASSWORD, request);
        LineHasher lineHasher = new LineHasher();

        //todo пароль не солирую хуйня праоль

        String hashPass = lineHasher.hashingLine(pass);
        Optional<User> user = service.findUserByLoginAndPassword(login,pass);
        if (user.isPresent()) {
            if (user.get().getUserStatus() != UserStatus.Freeze) {
                long id = user.get().getId();
                UserRole role = user.get().getUserRole();

                request.addSession(Attribute.USER_ID, id);
                request.addSession(Attribute.ROLE, role);
                request.addSession(Attribute.LOGIN, user.get().getLogin());
               // ProfilePlayer profilePlayer = profilePlayerService.findProfilePlayerById(id);
                //requestContext.addSession(Attribute.PHOTO, profilePlayer.getPhoto());
                //create jwt token
               // Map<String, String> claims = new HashMap<>();
               // claims.put(Attribute.USER_ID, String.valueOf(id));
               // claims.put(Attribute.ROLE, String.valueOf(role));
               // claims.put(Attribute.LOGIN, user.get().getLogin());
                /*     String token = jwtProvider.generateToken(claims);
                Cookie cookie = new Cookie(Attribute.ACCESS_TOKEN, token);
                cookie.setHttpOnly(true);
                cookie.setMaxAge(TIMEZONE_GMT_PLUS_THREE +
                        (int) (TimeUnit.MINUTES.toSeconds(LIFE_TIME_COOKIE)));
                request.addCookie(cookie);
*/
                return CommandResponse.redirect(PROFILE_PAGE_COMMAND + id);
            }
            request.addAttribute(Attribute.ERROR_MESSAGE, FREEZE_USER_KEY);
        } else {
            request.addAttribute(Attribute.ERROR_MESSAGE, INCORRECT_DATA_KEY);
        }
        request.addAttribute(Attribute.SAVED_LOGIN, login);
        return CommandResponse.forward(LOGIN);
    }
    }

