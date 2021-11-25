package com.epam.ratingmovies.controller;

import com.epam.ratingmovies.controller.command.Parameter;
import com.epam.ratingmovies.controller.command.request.RequestContext;

public class ParameterTaker {
    public static String takeString(String parameterName, RequestContext requestContext) {
        String paramValue = requestContext.getRequestParameter(parameterName);
        return paramValue;
    }
    public static long takeId(RequestContext requestContext)
           {
        String idStr = requestContext.getRequestParameter(Parameter.ID);
            return Long.parseLong(idStr);

    }


}
