package com.epam.ratingmovies.controller;

import com.epam.ratingmovies.controller.command.CommandName;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.api.Command;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.controller.command.util.Parameter;
import com.epam.ratingmovies.dao.connectionpool.api.ConnectionPool;
import com.epam.ratingmovies.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.ratingmovies.exception.ServiceException;
import com.epam.ratingmovies.util.Attribute;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(urlPatterns = {"/ratingMovies"}, name = "mainServlet")

public class MainController extends HttpServlet {
    private static final String ERROR = "/jsp/pages/error/error.jsp";
    private static final String UTF_EIGHT = "UTF-8";
    private static final String JSON_CONTENT_TYPE = "application/json";

    private static final Logger logger = LogManager.getLogger();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandResponse commandResult;
        String commandParam = "";
        commandParam = request.getParameter(Parameter.COMMAND);
        Command command = Command.withName(commandParam);
        try {
            RequestContext requestContext = new RequestContext(request);
            commandResult = command.execute(requestContext);
            requestContext.fillData(request, response);
            dispatch(commandResult, request, response);
        } catch (ServiceException e) {
            handleException(request, response, e.getMessage());
        }
    }

    private void dispatch(CommandResponse commandResult,
                          HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        if (commandResult.getPage().equals("json")) {
            response.setContentType(JSON_CONTENT_TYPE);
            response.setCharacterEncoding(UTF_EIGHT);
            response.getWriter().write(commandResult.getLine());
        } else {
            String page = commandResult.getPage();
            if (page == null) {
                response.sendRedirect("?command=" + CommandName.MOVIE_PAGE);
            } else {
                if (commandResult.isRedirect()) {
                    response.sendRedirect(page);
                } else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(page);
                    dispatcher.forward(request, response);
                }
            }
        }

    }


    private void handleException(HttpServletRequest req, HttpServletResponse resp, String errorMessage) throws IOException {
        req.setAttribute(Attribute.ERROR_MESSAGE, errorMessage);
        RequestDispatcher dispatcher = req.getRequestDispatcher(ERROR);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            logger.error(e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    public void destroy() {
        ConnectionPool pool = ConnectionPoolImpl.getInstance();
        pool.shutDown();
    }
}