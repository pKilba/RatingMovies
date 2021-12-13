package com.epam.ratingmovies.dao.entity;

import java.sql.Timestamp;

public class Comment extends AbstractEntity<Long> {
    private String message;
    private long movieId;
    private long userId;
    private Timestamp createTimeComment;

    Comment() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTimeComment() {
        return createTimeComment;
    }

    public void setCreateTimeComment(Timestamp createTimeComment) {
        this.createTimeComment = createTimeComment;
    }

    public Comment(String message, long movieId, long userId, Timestamp createTimeComment) {
        this.message = message;
        this.movieId = movieId;
        this.userId = userId;
        this.createTimeComment = createTimeComment;
    }

    public static Comment.CommentBuilder builder() {
        return new Comment.CommentBuilder();
    }


    public static class CommentBuilder {
        private final Comment newComment;

        CommentBuilder() {
            newComment = new Comment();
        }

        public Comment.CommentBuilder setCommendId(long commentId) {
            newComment.setId(commentId);
            return this;
        }

        public Comment.CommentBuilder setMessage(String message) {
            newComment.setMessage(message);
            return this;
        }

        public Comment.CommentBuilder setMovie(long movieId) {
            newComment.setMovieId(movieId);
            return this;
        }

        public Comment.CommentBuilder setUser(long userId) {
            newComment.setUserId(userId);
            return this;
        }

        public Comment.CommentBuilder setCreateTime(Timestamp timestamp) {
            newComment.setCreateTimeComment(timestamp);
            return this;
        }


        public Comment build() {
            return newComment;
        }

    }
}
