package com.epam.ratingmovies.service.converter;

import com.epam.ratingmovies.dao.entity.AbstractEntity;
import com.epam.ratingmovies.service.dto.Dto;

public interface Converter <D extends Dto, E extends AbstractEntity>{


    D convert(E e);


    E convert(D d);
}