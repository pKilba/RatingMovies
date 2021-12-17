package com.epam.ratingmovies.controller.command.impl.general;

import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.service.CommentsPagesWithPagination;

public class GoToReviewsPageCommand implements Command {

    private static final String REVIEWS = "/jsp/pages/reviews.jsp";
    private static final CommentsPagesWithPagination commentsPagesWithPagination = CommentsPagesWithPagination.getInstance();


    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {

        commentsPagesWithPagination.processCommandWithPagination(request);
        return CommandResponse.forward(REVIEWS);
    }
}
