package com.epam.ratingmovies.dao.connectionpool.impl;

import com.epam.ratingmovies.dao.connectionpool.api.ConnectionPool;
import com.epam.ratingmovies.dao.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


//final ubrats?
public final class ConnectionPoolImpl implements ConnectionPool {

    private static final String DB_URL = "jdbc:mysql://c8u4r7fp8i8qaniw.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/cnrandw4tw0rd9pd?autoReconnect=true";
    private static final String USER = "nfjpip0zodjdbk23";
    private static final String PASS = "m0tm1u4yb9jefy5z";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static int INITIAL_PO0L_SIZE = 3;
    private static final AtomicBoolean IS_POOL_CREATED = new AtomicBoolean(false);
    private static final ReentrantLock INSTANCE_LOCKER = new ReentrantLock();
    //мб слева простая очередь и простой лист справа уже это реализация
    private final BlockingQueue<ProxyConnection> availableConnections;
    private final BlockingQueue<ProxyConnection> giveAwayConnections;

    private static final Logger logger = LogManager.getLogger(ConnectionPoolImpl.class);


    //мой синглтон потокобезопасный точно ?

    //мб надо инстенс большими
    private static ConnectionPoolImpl instance;

    private ConnectionPoolImpl() {
        availableConnections = new LinkedBlockingQueue();
        giveAwayConnections = new LinkedBlockingQueue();
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


    //мб не надо проверять на создание и тд и вообще нафиг будлеан возвращать
    //проверяется прст это всё выше
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

    private void closeConnections(BlockingQueue<ProxyConnection> connections) {
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
        ProxyConnection connection = availableConnections.poll();
        giveAwayConnections.add(connection);
        return connection;
    }


    //todo чекнуть мб тут не надо синхранайз
    //todo rolback setautocommit?
    //todo порабоать над этим методом
    @Override
    public synchronized void returnConnection(Connection connection) {
        if (giveAwayConnections.remove(connection)) {
            availableConnections.add((ProxyConnection) connection);
            notifyAll();
        } else {
            logger.warn("Failed to get the connection back ");
        }
    }

    private boolean initializeConnections(int amount) {
        try {
            for (int i = 0; i < amount; i++) {
                //FINAL&&&
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
