package com.epam.ratingmovies.controller.command.impl.user;

import com.epam.ratingmovies.util.Attribute;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.CommandName;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.util.Parameter;
import com.epam.ratingmovies.controller.command.request.RequestContext;

public class LocalizationCommand implements Command {
    private static final String EN = "en";
    private static final String RU = "ru";
    private static final String EN_LOCALE = "en_US";
    private static final String RU_LOCALE = "ru_RU";
    private static final String PARAMETER_SPLITERATOR = "&";
    private static final String LOGIN_PAGE = "ratingMovies?command=" + CommandName.LOGIN_PAGE;
    private static final String SIGN_UP_PAGE = "ratingMovies?command=" + CommandName.SIGN_UP_PAGE;
    public static final String MOVIES = "/jsp/pages/movies.jsp";
    @Override
    public CommandResponse execute(RequestContext requestContext)  {
        String language = ParameterTaker.takeString(Parameter.LANGUAGE, requestContext);
        String locale = getLocaleByLanguage(language);
        requestContext.addSession(Attribute.LANGUAGE, locale);
        String page = requestContext.getHeader();
        if (page != null) {
            String prevCommand = extractCommand(page);
            if (CommandName.LOGIN.equals(prevCommand) || CommandName.SIGN_UP.equals(prevCommand)) {
                page = changeCommandToCommandPage(prevCommand);
            }
        }
        return CommandResponse.redirect(page);
    }

    private String getLocaleByLanguage(String language) {
        return switch (language) {
            case RU -> RU_LOCALE;
            default -> EN_LOCALE;
        };
    }

    private String changeCommandToCommandPage(String prevCommand) {
        return switch (prevCommand) {
            case CommandName.LOGIN -> LOGIN_PAGE;
            case CommandName.SIGN_UP -> SIGN_UP_PAGE;
            default -> MOVIES;
        };
    }

    private String extractCommand(String url) {
        int commandIndex = url.indexOf(Parameter.COMMAND) + Parameter.COMMAND.length() + 1;
        int lastCommandIndex = url.indexOf(PARAMETER_SPLITERATOR);
        if (lastCommandIndex == -1) {
            return url.substring(commandIndex);
        } else {
            return url.substring(commandIndex, lastCommandIndex);
        }
    }
}
