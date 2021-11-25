package com.epam.ratingmovies.dao.api;

import com.epam.ratingmovies.dao.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface DAO <T extends AbstractEntity<K>, K> {
    //todo или назвать креейт
    public  T save(T entity);

    public  T update(T entity);

    //todo tut mb K peredavats tipi id or entity
    public  void delete(T entity);

    public void delete (K id);

    public  List<T> findAll();

    public Optional<T> findById(K idEntity);

}
