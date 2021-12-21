package com.epam.ratingmovies.dao.api;

import com.epam.ratingmovies.dao.entity.AbstractEntity;
import com.epam.ratingmovies.exception.DaoException;

import java.util.List;

public interface DAO<T extends AbstractEntity<K>, K> {

    /**
     * @param entity
     * @return save entity
     * @throws DaoException if database errors occurs
     */
    T save(T entity) throws DaoException;


    /**
     * @return list all entity
     * @throws DaoException if database errors occurs
     */
    List<T> findAll() throws DaoException;


}
