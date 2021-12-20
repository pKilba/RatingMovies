package com.epam.ratingmovies.dao.api;

import com.epam.ratingmovies.dao.entity.AbstractEntity;
import com.epam.ratingmovies.exception.DaoException;

import java.util.List;

public interface DAO<T extends AbstractEntity<K>, K> {
    T save(T entity) throws DaoException;


    List<T> findAll() throws DaoException;


}
