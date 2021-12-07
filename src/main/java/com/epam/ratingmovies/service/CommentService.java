package com.epam.ratingmovies.service;

import com.epam.ratingmovies.dao.api.CommentDao;
import com.epam.ratingmovies.dao.entity.Comment;
import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.dao.impl.CommentDaoImpl;

import java.util.List;

public class CommentService {

   CommentDaoImpl commentDao = new CommentDaoImpl();

    public List findAll() {
        return commentDao.findAll();
    }
    public List findByMovieId( long id){
        return commentDao.findCommendByIdMovies(id);
    }
    public int findCommentsAmount() {
        return commentDao.findCommentsAmount();
    }
    public List<Comment> findCommentsRange(int amountQuery, int size) {

        return commentDao.findCommentsRange(amountQuery, size);
    }

}
