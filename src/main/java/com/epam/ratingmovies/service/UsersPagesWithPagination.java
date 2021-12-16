package com.epam.ratingmovies.service;

import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.controller.command.util.Parameter;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.util.Attribute;
import java.util.List;

public class UsersPagesWithPagination {

    UserService userService = new UserService();


    static private UsersPagesWithPagination instance ;

    private UsersPagesWithPagination() {

    }

    public static UsersPagesWithPagination getInstance() {
        if (instance == null) {
            instance = new UsersPagesWithPagination();
        }
        return instance;
    }


    public void processCommandWithPagination(RequestContext requestContext) throws ServiceException {
        int page = ParameterTaker.takeNumber(Parameter.PAGE, requestContext);
        int size = ParameterTaker.takeNumber(Parameter.SIZE, requestContext);
        long amount = userService.findUsersAmount();
        long amountQuery = (page - 1) * size;
        if (amountQuery > amount) {
            throw new ServiceException("Parameter in query invalid");
        }
        if (amount < size) {
            size = (int) amount;
        }
        List<User> userList = buildUserList(page, size);
        requestContext.addAttribute(Attribute.USER_LIST, userList);
        requestContext.addAttribute(Attribute.CURRENT_PAGE, page);
        int maxPage = (int) (amount / size);
        if (amount % size != 0) {
            ++maxPage;
        }
        requestContext.addAttribute(Attribute.AMOUNT_OF_PAGE, size);
        requestContext.addAttribute(Attribute.MAX_PAGE, maxPage);
    }

    private List<User> buildUserList(int page, int size) throws ServiceException {
        int offset = (page - 1) * size;
        List<User> usersList = null;
        try {
            usersList = userService.findUsersRange(offset, size);
        } catch (ServiceException e) {
            //todo logger
            throw new ServiceException("Invalid page or size!");
        }

        return usersList;
    }


}
