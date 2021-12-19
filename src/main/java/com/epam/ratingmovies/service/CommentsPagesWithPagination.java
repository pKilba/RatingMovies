package com.epam.ratingmovies.service;

import com.epam.ratingmovies.util.ParameterTaker;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.controller.command.util.Parameter;
import com.epam.ratingmovies.dao.entity.Comment;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.util.Attribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class CommentsPagesWithPagination {

    private static final CommentService commentService = CommentService.getInstance();
    private static final UserService userService = UserService.getInstance();
    private static final String INVALID_PARAMETER = "Parameter in query invalid";
    private static final String INVALID_PAGE_OR_SIZE = "Invalid page or size!";
    private static final String ATTRIBUTE_LEAVE_COMMENT = "commentUserList";
    private static final Logger logger = LogManager.getLogger();
    static private CommentsPagesWithPagination instance;

    private CommentsPagesWithPagination() {

    }

    public static CommentsPagesWithPagination getInstance() {
        if (instance == null) {
            instance = new CommentsPagesWithPagination();
        }
        return instance;
    }


    public void processCommandWithPagination(RequestContext requestContext) throws ServiceException {

        long id = ParameterTaker.takeIdNow(requestContext);
        requestContext.addAttribute(Attribute.ID, id);
        int page = ParameterTaker.takeNumber(Parameter.PAGE, requestContext);
        int size = ParameterTaker.takeNumber(Parameter.SIZE, requestContext);
        long amount = commentService.findCommentsAmountByMovieId(id);
        long amountQuery = (page - 1) * size;
        if (amountQuery > amount) {
            logger.warn(INVALID_PARAMETER);
            throw new ServiceException(INVALID_PARAMETER);
        }
        if (amount < size) {
            size = (int) amount;
        }
        List<Comment> commentList = buildCommentList(page, size);
        requestContext.addAttribute(Attribute.COMMENT_LIST, commentList);


        List<User> users = new ArrayList<>();
        User user;
        for (Comment comment : commentList) {
            user = userService.findUserById(comment.getUserId());
            users.add(user);
        }
        Map<Comment, User> commentUserMap
                = IntStream.range(0, Math.min(commentList.size(), users.size()))
                .boxed()
                .collect(Collectors.toMap(commentList::get, users::get));

        requestContext.addAttribute(ATTRIBUTE_LEAVE_COMMENT, commentUserMap);
        requestContext.addAttribute(Attribute.CURRENT_PAGE, page);
        int maxPage;
        if (amount != 0 && size != 0) {
            maxPage = (int) (amount / size);
            if (amount % size != 0) {
                ++maxPage;
            }
        } else {
            maxPage = 1;
        }
        requestContext.addAttribute(Attribute.AMOUNT_OF_PAGE, size);
        requestContext.addAttribute(Attribute.MAX_PAGE, maxPage);
    }

    private List<Comment> buildCommentList(int page, int size) throws ServiceException {
        int offset = (page - 1) * size;
        List<Comment> commentsList;
        try {
            commentsList = commentService.findCommentRange(offset, size);
        } catch (ServiceException e) {
            logger.warn(INVALID_PAGE_OR_SIZE + e);
            throw new ServiceException(INVALID_PAGE_OR_SIZE);
        }

        return commentsList;
    }


}
