package com.epam.ratingmovies.dao.connectionpool.api;

import java.sql.Connection;

public interface ConnectionPool {

    boolean init() ;

    boolean shutDown();

    Connection takeConnection() ;

    void returnConnection(Connection connection);

}
