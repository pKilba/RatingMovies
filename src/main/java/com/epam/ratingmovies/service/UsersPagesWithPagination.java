package com.epam.ratingmovies.service;

import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.controller.command.util.Parameter;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.util.Attribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UsersPagesWithPagination {

    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = UserService.getInstance();
    private static final String INVALID_PAGE_OR_SIZE = "Invalid page or size!";
    private static final String INVALID_PARAMETER = "Parameter in query invalid";
    private static UsersPagesWithPagination instance;

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
            logger.warn(INVALID_PARAMETER);
            throw new ServiceException(INVALID_PARAMETER);
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
        List<User> usersList;
        try {
            usersList = userService.findUsersRange(offset, size);
        } catch (ServiceException e) {
            logger.warn(INVALID_PAGE_OR_SIZE + e);
            throw new ServiceException(INVALID_PAGE_OR_SIZE);
        }

        return usersList;
    }


}
