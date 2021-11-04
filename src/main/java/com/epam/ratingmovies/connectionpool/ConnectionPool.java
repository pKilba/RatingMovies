package com.epam.ratingmovies.connectionpool;

import java.sql.Connection;

public interface ConnectionPool {

    boolean init() ;

    boolean shutDown();

    Connection takeConnection() ;

    void returnConnection(Connection connection);

}
