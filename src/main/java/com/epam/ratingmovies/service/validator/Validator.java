package com.epam.ratingmovies.service.validator;

import com.epam.ratingmovies.dao.entity.AbstractEntity;
import com.epam.ratingmovies.dao.entity.User;

public interface Validator<T extends AbstractEntity> {
    boolean isValid(User user);

}
