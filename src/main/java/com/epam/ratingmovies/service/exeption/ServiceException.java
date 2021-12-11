package com.epam.ratingmovies.service.exeption;

public class ServiceException extends RuntimeException {

    public ServiceException() {
        super();
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message) {
        super(message);
    }
}
