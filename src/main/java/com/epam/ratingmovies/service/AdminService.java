package com.epam.ratingmovies.service;

import com.epam.ratingmovies.util.Attribute;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.entity.UserStatus;
import com.epam.ratingmovies.dao.impl.UserDaoImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class AdminService {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private static AdminService instance = new AdminService();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static UserService userService = UserService.getInstance();

    public static AdminService getInstance() {
        if (instance == null) {
            instance = new AdminService();
        }
        return instance;
    }




    public boolean isBlockedById(long id) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        Optional<User> user = userDao.findUserById(id);
        if (user.isPresent()) {

            return user.get().getUserStatus() == UserStatus.BANNED;

        }

        return false;
    }


    public String banUserById(RequestContext requestContext) throws ServiceException {
        long userId = -1;
        String response = null;
        ObjectNode objectNode = mapper.createObjectNode();
        try {
            userId = ParameterTaker.takeId(requestContext);
        } catch (Exception e) {
         logger.error("Action ban user, id not found. " + e);
            objectNode.put(Attribute.SUCCESS, false);
            response = String.valueOf(objectNode);
        }
        if (response == null) {
            if (!userService.blockedById(userId)) {
                objectNode.put(Attribute.SUCCESS, false);
                logger.warn("User id=" + userId + " not found.");
               throw new ServiceException("User id=" + userId + " not found.");

            } else {
                objectNode.put(Attribute.SUCCESS, true);
                     objectNode.put(Attribute.MESSAGE, String.valueOf(UserStatus.BANNED));
            }
            objectNode.put(Attribute.SUCCESS, true);
            objectNode.put("message", "BANNED");
            response = String.valueOf(objectNode);
        }
        return response;
    }

    public String unbanUserById(RequestContext requestContext) throws ServiceException {
        long userId = -1;
        String response = null;
        ObjectNode objectNode = mapper.createObjectNode();
        try {
            userId = ParameterTaker.takeId(requestContext);
        } catch (Exception e) {
            logger.error("Action ban user, id not found. " + e);
            objectNode.put(Attribute.SUCCESS, false);
            response = String.valueOf(objectNode);
        }
        if (response == null) {
            if (!userService.unblockById(userId)) {
                objectNode.put(Attribute.SUCCESS, false);
                logger.warn("User id=" + userId + " not found.");
                throw new ServiceException("User id=" + userId + " not found.");
            } else {
                objectNode.put(Attribute.SUCCESS, true);
                objectNode.put(Attribute.MESSAGE, String.valueOf(UserStatus.ACTIVE));
            }
            response = String.valueOf(objectNode);
        }
        return response;
    }


}
