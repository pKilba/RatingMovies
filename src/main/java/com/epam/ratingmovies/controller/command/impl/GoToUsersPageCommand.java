package com.epam.ratingmovies.controller.command.impl;

import com.epam.ratingmovies.Attribute;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.Parameter;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.impl.UserDaoImpl;
import com.google.protobuf.ServiceException;

import java.util.List;

public class GoToUsersPageCommand implements Command {
    public static final String USERS = "/jsp/pages/users.jsp";
    @Override
    public CommandResponse execute(RequestContext request) throws ServiceException {
        int page = ParameterTaker.takeNumber(Parameter.PAGE, request);
        int size = ParameterTaker.takeNumber(Parameter.SIZE, request);

        UserDaoImpl userDao = new UserDaoImpl();
     List<User> userList = userDao.findAll();
        request.addAttribute(Attribute.USER_LIST, userList);
userList.get(1).getLogin();
        System.out.println(page+"ooooo"+size);

        return CommandResponse.forward(USERS);
    }
}
