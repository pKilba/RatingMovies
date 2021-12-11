package com.epam.ratingmovies.service;

import com.epam.ratingmovies.dao.api.CommentDao;
import com.epam.ratingmovies.dao.entity.Comment;
import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.dao.exception.DaoException;
import com.epam.ratingmovies.dao.impl.CommentDaoImpl;
import org.apache.logging.log4j.Level;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentService {

   CommentDaoImpl commentDao = new CommentDaoImpl();

    public List findAll() {
        return commentDao.findAll();
    }
    public List findByMovieId( long id){
        return commentDao.findCommentByIdMovies(id);
    }

    public int findCommentsAmountByMovieId(long id){
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


    public int findCommentsAmount() {
        return commentDao.findCommentsAmount();
    }
    public List<Comment> findCommentsRange(int amountQuery, int size) {

        return commentDao.findCommentsRange(amountQuery, size);
    }

}
