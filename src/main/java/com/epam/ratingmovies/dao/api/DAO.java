package com.epam.ratingmovies.dao.api;

import com.epam.ratingmovies.dao.entity.AbstractEntity;
import com.epam.ratingmovies.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface DAO <T extends AbstractEntity<K>, K> {
    //todo или назвать креейт
    public  T save(T entity) throws DaoException;

    public  T update(T entity) throws DaoException;



    public void delete (K id) throws DaoException;

    public  List<T> findAll() throws DaoException;

    public Optional<T> findById(K idEntity) throws DaoException;

}
