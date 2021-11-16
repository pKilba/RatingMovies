package com.epam.ratingmovies.dao.connectionpool;

import java.sql.Connection;

public interface ConnectionPool {

    boolean init() ;

    boolean shutDown();

    Connection takeConnection() ;

    void returnConnection(Connection connection);

}
