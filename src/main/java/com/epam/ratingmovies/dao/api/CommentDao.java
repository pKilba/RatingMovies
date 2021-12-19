package com.epam.ratingmovies.dao.api;

import com.epam.ratingmovies.dao.entity.Comment;
import com.epam.ratingmovies.exception.DaoException;

import java.util.List;

public interface CommentDao extends DAO<Comment, Long> {

    List<Comment> findCommentsRange(int offset, int amount) throws DaoException;

    int findCommentsAmountByMovieId(long id) throws DaoException;

    int findCommentsAmount() throws DaoException;

    List<Comment> findCommentByIdMovies(long id) throws DaoException;


}
