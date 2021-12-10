package com.epam.ratingmovies.controller;

import java.io.*;

import com.epam.ratingmovies.Attribute;
import com.epam.ratingmovies.controller.command.Command;
import com.epam.ratingmovies.controller.command.CommandName;
import com.epam.ratingmovies.controller.command.CommandResponse;
import com.epam.ratingmovies.controller.command.Parameter;
import com.epam.ratingmovies.controller.command.request.RequestContext;
import com.epam.ratingmovies.dao.connectionpool.api.ConnectionPool;
import com.epam.ratingmovies.dao.connectionpool.impl.ConnectionPoolImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet(urlPatterns = {"/ratingMovies"}, name = "mainServlet")

public class MainController extends HttpServlet {
    public static final String ERROR = "/jsp/pages/error/error.jsp";
    private static final String UTF_EIGHT = "UTF-8";
    private static final String JSON_CONTENT_TYPE = "application/json";


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("ffff");
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommandResponse commandResult;
        String commandParam = "";
        commandParam = request.getParameter(Parameter.COMMAND);
        Command command = Command.withName(commandParam);
        try {
            RequestContext requestContext = new RequestContext(request);
            commandResult = command.execute(requestContext);
            requestContext.fillData(request, response);
            dispatch(commandResult, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            //    LOGGER.error(e);
              handleException(request, response, e.getMessage());
        }
    }

    private void dispatch(CommandResponse commandResult,
                          HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        // if (!commandResult.isTypeResponseJson()) {
        if (commandResult.getPage().equals("json")) {
            response.setContentType(JSON_CONTENT_TYPE);
            response.setCharacterEncoding(UTF_EIGHT);
            response.getWriter().write(commandResult.getLine());
        } else {
            String page = commandResult.getPage();
            if (page == null) {
                System.out.println("123748uurhfrfrf38");
                response.sendRedirect("?command=" + CommandName.HOME_PAGE);
            } else {
                if (commandResult.isRedirect()) {
                    response.sendRedirect(page);
                } else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(page);
                    dispatcher.forward(request, response);
                }
            }
        }
        /*} else {
            response.setContentType(JSON_CONTENT_TYPE);
            response.setCharacterEncoding(UTF_EIGHT);
            response.getWriter().write(commandResult.getJsonResponse());
        }*/
    }

      /*  CommandResponse commandResult;
        RequestContext requestContext = requestContextCreator.create(request);
        String requestParameter = request.getParameter(Parameter.COMMAND);
        Command command = Command.withName(requestParameter);
        commandResult = command.execute(requestContext);
        String page = commandResult.getPath();
        requestFiller.fillData(request, requestContext);
        if (page == null) {
            page = PagePath.ERROR;
            response.sendRedirect(request.getContextPath() + page);
        }
        if (commandResult.isRedirect()) {
            response.sendRedirect(request.getContextPath() + page);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }*/


    private void handleException(HttpServletRequest req, HttpServletResponse resp, String errorMessage)
            throws IOException {
        req.setAttribute(Attribute.ERROR_MESSAGE, errorMessage);
        RequestDispatcher dispatcher = req.getRequestDispatcher(ERROR);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            //LOGGER.error(e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }



    public void destroy() {
        ConnectionPool pool = ConnectionPoolImpl.getInstance();
        pool.shutDown();
    }
    /*public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        Date date = new Date();
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
        UserDaoImpl userDaoTest = new UserDaoImpl();
        User user = new User("PolKilbas", "jsp123", "jsp", UserRole.USER,
                123, "aaa", "f",
                UserStatus.Active, new Timestamp(System.currentTimeMillis()));
        userDaoTest.save(user);
        userDaoTest.delete(9L);

        userDaoTest.findAll();


        //todo не работает апдейт
        userDaoTest.update(user);
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
        throw new IOException("fdsfsd");
    }
*/
}