package com.epam.ratingmovies.dao.connectionpool.impl;

import com.epam.ratingmovies.dao.connectionpool.api.ConnectionPool;
import com.epam.ratingmovies.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public final class ConnectionPoolImpl implements ConnectionPool {

    private static final String DB_URL = "jdbc:mysql://c8u4r7fp8i8qaniw.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/cnrandw4tw0rd9pd?autoReconnect=true";
    private static final String USER = "nfjpip0zodjdbk23";
    private static final String PASS = "m0tm1u4yb9jefy5z";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final int INITIAL_PO0L_SIZE = 3;
    private static final AtomicBoolean IS_POOL_CREATED = new AtomicBoolean(false);
    private static final ReentrantLock INSTANCE_LOCKER = new ReentrantLock();

    private final LinkedList availableConnections;
    private final LinkedList giveAwayConnections;

    private static final Logger logger = LogManager.getLogger(ConnectionPoolImpl.class);

    private static ConnectionPoolImpl instance;

    private ConnectionPoolImpl() {
        availableConnections = new LinkedList();
        giveAwayConnections = new LinkedList();
        init();
    }

    public static ConnectionPool getInstance() {
        if (!IS_POOL_CREATED.get()) {
            INSTANCE_LOCKER.lock();
            if (!IS_POOL_CREATED.get()) {
                instance = new ConnectionPoolImpl();
                IS_POOL_CREATED.set(true);
            }
            INSTANCE_LOCKER.unlock();
        }
        return instance;
    }


    @Override
    public boolean init() {
        if (!IS_POOL_CREATED.get()) {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                logger.throwing(Level.WARN, e);
                throw new ConnectionPoolException(e);
            }

            initializeConnections(INITIAL_PO0L_SIZE);
            logger.traceExit("pool initialized by amount:" + availableConnections.size());
            return true;
        }
        return false;
    }

    @Override
    public boolean shutDown() {
        if (IS_POOL_CREATED.get()) {
            closeConnections();
            IS_POOL_CREATED.set(false);
            return true;
        }
        return false;
    }

    private void closeConnections(LinkedList<ProxyConnection> connections) {
        for (ProxyConnection connection : connections) {
            closeConnection(connection);

        }
    }

    private void closeConnection(ProxyConnection connection) {
        try {
            connection.realClose();
        } catch (SQLException e) {
            logger.throwing(Level.WARN, e);
            throw new ConnectionPoolException(e);
        }

    }

    private void closeConnections() {
        closeConnections(this.availableConnections);
        closeConnections(this.giveAwayConnections);
    }

    @Override
    public synchronized Connection takeConnection() {

        while (availableConnections.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                logger.throwing(Level.WARN, e);
                throw new ConnectionPoolException(e);

            }
        }
        ProxyConnection connection = (ProxyConnection) availableConnections.poll();
        giveAwayConnections.add(connection);
        return connection;
    }

    @Override
    public synchronized void returnConnection(Connection connection) {
        if (giveAwayConnections.remove(connection)) {
            availableConnections.add(connection);
            notifyAll();
        } else {
            logger.warn("Failed to get the connection back ");
        }
    }

    private boolean initializeConnections(int amount) {
        try {
            for (int i = 0; i < amount; i++) {
                Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                ProxyConnection proxyConnection = new ProxyConnection(connection, this);
                availableConnections.add(proxyConnection);
            }
            return true;
        } catch (SQLException e) {
            logger.throwing(Level.WARN, e);
            throw new ConnectionPoolException(e);
        }
    }
}
