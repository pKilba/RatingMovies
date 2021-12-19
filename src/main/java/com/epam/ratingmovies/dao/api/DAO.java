package com.epam.ratingmovies.dao.api;

import com.epam.ratingmovies.dao.entity.AbstractEntity;
import com.epam.ratingmovies.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface DAO<T extends AbstractEntity<K>, K> {
    T save(T entity) throws DaoException;

    T update(T entity) throws DaoException;


    void delete(K id) throws DaoException;

    List<T> findAll() throws DaoException;

    Optional<T> findById(K idEntity) throws DaoException;

}
