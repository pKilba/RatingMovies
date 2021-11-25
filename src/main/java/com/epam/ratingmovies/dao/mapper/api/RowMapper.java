package com.epam.ratingmovies.dao.mapper.api;

import com.epam.ratingmovies.dao.entity.AbstractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends AbstractEntity> {
    T map(ResultSet resultSet) throws SQLException;
}
