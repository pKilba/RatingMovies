package com.epam.ratingmovies.controller;

import com.epam.ratingmovies.controller.command.request.RequestContext;

public class ParameterTaker {
    public static String takeString(String parameterName, RequestContext requestContext) {
        String paramValue = requestContext.getRequestParameter(parameterName);
        return paramValue;
    }

}
