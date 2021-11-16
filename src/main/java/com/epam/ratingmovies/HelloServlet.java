package com.epam.ratingmovies;

import java.io.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import com.epam.ratingmovies.dao.UserDaoImpl;
import com.epam.ratingmovies.dao.connectionpool.ConnectionPool;
import com.epam.ratingmovies.dao.connectionpool.ConnectionPoolImpl;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.entity.UserRole;
import com.epam.ratingmovies.dao.entity.UserStatus;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World00000!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        Date date = new Date();
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
        UserDaoImpl userDaoTest = new UserDaoImpl();
        userDaoTest.save(new User("PolKilbas","jsp123","jsp", UserRole.USER,
                123,"aaa","f",
                UserStatus.Active,new Timestamp(System.currentTimeMillis())));
        userDaoTest.delete(9L);

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
        throw new IOException("fdsfsd");
    }

    public void destroy() {
    }
}