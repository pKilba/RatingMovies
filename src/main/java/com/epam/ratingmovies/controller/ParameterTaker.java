package com.epam.ratingmovies.controller;

import com.epam.ratingmovies.controller.command.util.Parameter;
import com.epam.ratingmovies.controller.command.request.RequestContext;

public class ParameterTaker {
    public static String takeString(String parameterName, RequestContext requestContext) {
        return requestContext.getRequestParameter(parameterName);
    }

    public static long takeId(RequestContext requestContext) {
        String idStr = requestContext.getRequestParameter(Parameter.ID);
        return Long.parseLong(idStr);

    }

    public static long takeIdNow(RequestContext requestContext) {
        String idStr = requestContext.getRequestParameter("movieId");
        return Long.parseLong(idStr);

    }

    public static int takeNumber(String parameterName, RequestContext requestContext) {
        String numberStr = requestContext.getRequestParameter(parameterName);
        int number = -1;
        try {
            number = Integer.parseInt(numberStr);
        } catch (NumberFormatException e) {
        }
        if (number < 0) {
        }
        return number;
    }


}
