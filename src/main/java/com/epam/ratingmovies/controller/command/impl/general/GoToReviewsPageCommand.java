package com.epam.ratingmovies.controller.command.impl.general;

import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.service.*;
import com.epam.ratingmovies.util.Attribute;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.util.Parameter;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.Comment;
import com.epam.ratingmovies.dao.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GoToReviewsPageCommand implements Command {

    public static final String REVIEWS = "/jsp/pages/reviews.jsp";

    CommentService commentService = new CommentService();
    UserService userService = new UserService();
    MovieService movieService = new MovieService();

    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {


        CommentsPagesWithPagination commentsPagesWithPagination = new CommentsPagesWithPagination();
        commentsPagesWithPagination.processCommandWithPagination(request);


        return CommandResponse.forward(REVIEWS);


//        long id = ParameterTaker.takeIdNow(request);
//        request.addAttribute(Attribute.ID, id);
//        List<Comment> comments;
//        int page = ParameterTaker.takeNumber(Parameter.PAGE, request);
//        //todo разобраться с page and size wtf отрицательное
//        if (page < 0)
//            page = 1;
//        int size = ParameterTaker.takeNumber(Parameter.SIZE, request);
//        if (size < 0)
//            size = 10;
//
//        int amount = commentService.findCommentsAmountByMovieId(id);
//
//
//        int amountQuery = (page - 1) * size;
//        if (amountQuery > amount) {
//        }
//        if (amount < size) {
//            size = amount;
//        }
//        List commentsById = commentService.findByMovieId(id);
//        comments = commentService.findCommentByRange(amountQuery, size, commentsById);
//        request.addAttribute(Attribute.CURRENT_PAGE, page);
//        int maxPage = 1;
//        if (amount != 0) {
//            maxPage = amount / size;
//            if (amount % size != 0) {
//                ++maxPage;
//            }
//        }
//        request.addAttribute(Attribute.AMOUNT_OF_PAGE, size);
//        request.addAttribute(Attribute.MAX_PAGE, maxPage);
//        List<User> users = new ArrayList<>();
//        User user = new User();
//        for (Comment comment : comments) {
//            user = userService.findUserById(comment.getUserId());
//            users.add(user);
//        }
//        Map<Comment, User> commentUserMap
//                = IntStream.range(0, Math.min(comments.size(), users.size()))
//                .boxed()
//                .collect(Collectors.toMap(comments::get, users::get));
//
//
//        request.addAttribute("commentUserList", commentUserMap);


    }
}
