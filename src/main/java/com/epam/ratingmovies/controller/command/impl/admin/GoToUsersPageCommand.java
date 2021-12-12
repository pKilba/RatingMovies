package com.epam.ratingmovies.controller.command.impl.admin;

import com.epam.ratingmovies.exception.DaoException;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.util.Attribute;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.util.Parameter;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.service.UserService;

import java.util.List;

public class GoToUsersPageCommand implements Command {
    public static final String USERS = "/jsp/pages/users.jsp";
    UserService userService = new UserService();


    //todo мб вынести в отдельный класс
    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        int page = ParameterTaker.takeNumber(Parameter.PAGE, request);
        int size = ParameterTaker.takeNumber(Parameter.SIZE, request);
        int amount = userService.findUsersAmount();
        int amountQuery = (page) * size;
        if (amountQuery > amount) {
        }
        if (amount < size) {
            size = (int) amount;
        }
        List<User> userList = userService.findUsers();
        if (userList.size() > 10) {
            userList = userService.findUsersRange(amount - amountQuery + 1, size,userList);
        }
        int maxPage = (int) (amount / size);
        if (amount % size != 0) {
            ++maxPage;
        }
        request.addAttribute(Attribute.USER_LIST, userList);
        request.addAttribute(Attribute.CURRENT_PAGE, page);
        request.addAttribute(Attribute.AMOUNT_OF_PAGE, size);
        request.addAttribute(Attribute.MAX_PAGE, maxPage);
        request.addAttribute(Attribute.USER_LIST, userList);

        return CommandResponse.forward(USERS);
    }
}
