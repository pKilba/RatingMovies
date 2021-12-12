package com.epam.ratingmovies.service;

import com.epam.ratingmovies.dao.entity.Comment;
import com.epam.ratingmovies.dao.impl.CommentDaoImpl;
import com.epam.ratingmovies.exception.DaoException;

import java.util.ArrayList;
import java.util.List;

public class CommentService {

   CommentDaoImpl commentDao = new CommentDaoImpl();

    public List findAll() throws DaoException {
        return commentDao.findAll();
    }
    public List findByMovieId( long id) throws DaoException {
        return commentDao.findCommentByIdMovies(id);
    }

    public int findCommentsAmountByMovieId(long id) throws DaoException {
        return commentDao.findCommentsAmountByMovieId( id);
    }

    public List findCommentByRange(int offset, int amount, List<Comment> comments){
        int count = 0;

        //todo проверить правильно или не!!!!
        
        List<Comment> result = new ArrayList<>();

               for (Comment comment :comments){
                   if (offset < count && count <= offset + amount) {
                       result.add(comment);
                   }
                   count++;
               }

        return result;

    }


    public int findCommentsAmount() throws DaoException {
        return commentDao.findCommentsAmount();
    }
    public List<Comment> findCommentsRange(int amountQuery, int size) throws DaoException {

        return commentDao.findCommentsRange(amountQuery, size);
    }

}
