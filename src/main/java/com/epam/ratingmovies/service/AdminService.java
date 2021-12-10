package com.epam.ratingmovies.service;

import com.epam.ratingmovies.Attribute;
import com.epam.ratingmovies.controller.ParameterTaker;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.entity.UserStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.protobuf.ServiceException;

public class AdminService {

//    private static final Logger LOGGER = LogManager.getLogger();
    private static AdminService instance = new AdminService();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static UserService userService = UserService.getInstance();

    public static AdminService getInstance() {
        if (instance == null) {
            instance = new AdminService();
        }
        return instance;
    }
    public String banUserById(RequestContext requestContext) throws ServiceException {
        long userId = -1;
        String response = null;
        ObjectNode objectNode = mapper.createObjectNode();
        try {
            userId = ParameterTaker.takeId(requestContext);
        } catch (Exception e) {
            //LOGGER.error("Action ban user, id not found. " + e);
            objectNode.put(Attribute.SUCCESS, false);
            response = String.valueOf(objectNode);
        }
        if (response == null) {
//            if (!userService.isBlockedById(userId)) {
//                objectNode.put(Attribute.SUCCESS, false);
//                //LOGGER.warn("User id=" + userId + " not found.");
//                throw new ServiceException("User id=" + userId + " not found.");
//            } else {
//                objectNode.put(Attribute.SUCCESS, true);
//           //     objectNode.put(Attribute.MESSAGE, String.valueOf(UserStatus.BANNED));
//            }
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
//            LOGGER.error("Action ban user, id not found. " + e);
            objectNode.put(Attribute.SUCCESS, false);
            response = String.valueOf(objectNode);
        }
        if (response == null) {
//            if (!userService.unblockById(userId)) {
//                objectNode.put(Attribute.SUCCESS, false);
//                //LOGGER.warn("User id=" + userId + " not found.");
//                throw new ServiceException("User id=" + userId + " not found.");
//            } else {
//                objectNode.put(Attribute.SUCCESS, true);
//               // objectNode.put(Attribute.MESSAGE, String.valueOf(UserStatus.ACTIVE));
//            }
            response = String.valueOf(objectNode);
        }
        return response;
    }


}
