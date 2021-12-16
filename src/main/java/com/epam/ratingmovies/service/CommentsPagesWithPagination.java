package com.epam.ratingmovies.service;

import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.controller.command.util.Parameter;
import com.epam.ratingmovies.dao.entity.Comment;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.util.Attribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.epam.ratingmovies.controller.command.impl.user.AddCommentCommand.userService;

public class CommentsPagesWithPagination {

 private   final CommentService commentService = new CommentService();


    public void processCommandWithPagination(RequestContext requestContext) throws ServiceException {

        long id = ParameterTaker.takeIdNow(requestContext);
        requestContext.addAttribute(Attribute.ID, id);
        int page = ParameterTaker.takeNumber(Parameter.PAGE, requestContext);
        int size = ParameterTaker.takeNumber(Parameter.SIZE, requestContext);
        long amount = commentService.findCommentsAmountByMovieId(id);
        long amountQuery = (page - 1) * size;
        if (amountQuery > amount) {
            throw new ServiceException("Parameter in query invalid");
        }
        if (amount < size) {
            size = (int) amount;
        }
        List<Comment> commentList = buildCommentList(page, size);
        requestContext.addAttribute(Attribute.COMMENT_LIST, commentList);



        List<User> users = new ArrayList<>();
        User user = new User();
        for (Comment comment : commentList) {
            user = userService.findUserById(comment.getUserId());
            users.add(user);
        }
        Map<Comment, User> commentUserMap
                = IntStream.range(0, Math.min(commentList.size(), users.size()))
                .boxed()
                .collect(Collectors.toMap(commentList::get, users::get));


        requestContext.addAttribute("commentUserList", commentUserMap);













        requestContext.addAttribute(Attribute.CURRENT_PAGE, page);
       int maxPage;
        if (amount!=0 &&size!=0) {
            maxPage = (int) (amount / size);
           if (amount % size != 0) {
               ++maxPage;
           }
       }
       else {
           maxPage = 1 ;
       }
        requestContext.addAttribute(Attribute.AMOUNT_OF_PAGE, size);
        requestContext.addAttribute(Attribute.MAX_PAGE, maxPage);
    }

    private List<Comment> buildCommentList(int page, int size) throws ServiceException {
        int offset = (page - 1) * size;
        List<Comment> commentsList ;
        try {
            commentsList = commentService.findCommentRange(offset, size);
        } catch (ServiceException e) {
            //todo logger
            throw new ServiceException("Invalid page or size!");
        }

        return commentsList;
    }




}
