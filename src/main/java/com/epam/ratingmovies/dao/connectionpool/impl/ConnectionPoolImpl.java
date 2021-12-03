package com.epam.ratingmovies.dao.connectionpool.impl;

import com.epam.ratingmovies.dao.connectionpool.api.ConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


//final ubrats?
public final class ConnectionPoolImpl implements ConnectionPool {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASS = "jsp1977";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static int INITIAL_PO0L_SIZE = 50;
    private static final AtomicBoolean IS_POOL_CREATED = new AtomicBoolean(false);
    private static final ReentrantLock INSTANCE_LOCKER = new ReentrantLock();

    //мб слева простая очередь и простой лист справа уже это реализация
    private final BlockingQueue<ProxyConnection> availableConnections;
    private final BlockingQueue<ProxyConnection> giveAwayConnections;

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
            try {
                if (!IS_POOL_CREATED.get()) {
                    instance = new ConnectionPoolImpl();
                    IS_POOL_CREATED.set(true);
                }
            } finally {
                INSTANCE_LOCKER.unlock();
            }

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
                //todo impl logger and custom ex
                e.printStackTrace();
            }
            initializeConnections(INITIAL_PO0L_SIZE);
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
        } catch (SQLException E) {
            //todo impl log and custom ex

        }
    }

    private void closeConnections() {
        closeConnections(this.availableConnections);
        closeConnections(this.giveAwayConnections);
    }

    @Override
    public synchronized Connection takeConnection() {

        //todo мб сделать чтобы колво конекшенелов было динамическим а не статическим
        while (availableConnections.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                //todo implement logger and custom exception

                Thread.currentThread().interrupt();
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
         //rolback ?
            availableConnections.add((ProxyConnection) connection);
            notifyAll();
        } else {
            //log
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
            System.out.printf("vse good");
            return true;
        } catch (SQLException e) {
            //ubrats return nahui
            System.out.printf("problem");
            return false;
            //dopilits
            // throw new ConnectionPoolException();
        }
    }

}
