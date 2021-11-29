package com.epam.ratingmovies.dao.entity;

import java.sql.Timestamp;

public class Comment extends AbstractEntity<Long> {
    private String message;
    private Movie movie;
    private User user;
    private Timestamp createTimeComment;

    Comment() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreateTimeComment() {
        return createTimeComment;
    }

    public void setCreateTimeComment(Timestamp createTimeComment) {
        this.createTimeComment = createTimeComment;
    }

    public Comment(String message, Movie movie, User user, Timestamp createTimeComment) {
        this.message = message;
        this.movie = movie;
        this.user = user;
        this.createTimeComment = createTimeComment;
    }

    public static Comment.CommentBuilder builder() {
        return new Comment.CommentBuilder();
    }


    public static class CommentBuilder {
        private Comment newComment;

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

        public Comment.CommentBuilder setMovie(Movie movie) {
            newComment.setMovie(movie);
            return this;
        }

        public Comment.CommentBuilder setUser(User user) {
            newComment.setUser(user);
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
