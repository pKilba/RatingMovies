package com.epam.ratingmovies.dao.connectionpool.api;

import com.epam.ratingmovies.dao.exception.ConnectionPoolException;

import java.sql.Connection;

public interface ConnectionPool {

    boolean init() throws ConnectionPoolException;

    boolean shutDown();

    Connection takeConnection() ;

    void returnConnection(Connection connection);

}
