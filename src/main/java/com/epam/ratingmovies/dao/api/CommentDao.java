package com.epam.ratingmovies.dao.api;

import com.epam.ratingmovies.dao.entity.Comment;
import com.epam.ratingmovies.exception.DaoException;

import java.util.List;

public interface CommentDao extends DAO<Comment, Long> {

    /**
     * @param offset (current comment let's start the countdown)
     * @param amount amount comments
     * @return list comments by range
     * @throws DaoException if database errors occurs
     */
    List<Comment> findCommentsRange(int offset, int amount) throws DaoException;

    /**
     * @param id movie id
     * @return amount comments by movie id
     * @throws DaoException if database errors occurs
     */
    int findCommentsAmountByMovieId(long id) throws DaoException;

    /**
     * @return amount all comments
     * @throws DaoException if database errors occurs
     */
    int findCommentsAmount() throws DaoException;

    /**
     * @param id movie id
     * @return list comments by movie id
     * @throws DaoException if database errors occurs
     */
    List<Comment> findCommentByIdMovies(long id) throws DaoException;


}
