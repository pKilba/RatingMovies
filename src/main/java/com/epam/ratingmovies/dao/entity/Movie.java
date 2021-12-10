package com.epam.ratingmovies.dao.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Movie extends AbstractEntity<Long> {
    private String name;
    private String poster;
    private String about;
    private Timestamp releaseDate;
    private int amount_like;
    private int amount_dislike;
    private Genre genre;
    private String producer;
    private int duration;
    private String background;

    public String getName() {
        return name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Movie(String name, String poster, String about, Timestamp releaseDate, int amount_like, int amount_dislike, Genre genre, String producer, int duration, String background)
    {
        this.name = name;
        this.poster = poster;
        this.about = about;
        this.releaseDate = releaseDate;
        this.amount_like = amount_like;
        this.amount_dislike = amount_dislike;
        this.genre = genre;
        this.producer = producer;
        this.duration = duration;
        this.background = background;
    }




    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }


    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getAmount_like() {
        return amount_like;
    }

    public void setAmount_like(int amount_like) {
        this.amount_like = amount_like;
    }

    public int getAmount_dislike() {
        return amount_dislike;
    }

    public void setAmount_dislike(int amount_dislike) {
        this.amount_dislike = amount_dislike;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public static Movie.MovieBuilder builder() {
        return new Movie.MovieBuilder();
    }

    public Movie() {
    }


    public static class MovieBuilder {
        private Movie newMovie;

        MovieBuilder() {
            newMovie = new Movie();
        }

        public Movie.MovieBuilder setMovieProducer(String producer) {
            newMovie.setProducer(producer);
            return this;
        }
        public Movie.MovieBuilder setMovieDuration(int duration) {
            newMovie.setDuration(duration);
            return this;
        }

        public Movie.MovieBuilder setMovieBackground(String background) {
            newMovie.setBackground(background);
            return this;
        }
        public Movie.MovieBuilder setMovieName(String name) {
            newMovie.setName(name);
            return this;
        }

        public Movie.MovieBuilder setMovieId(long movieId) {
            newMovie.setId(movieId);
            return this;
        }

        public Movie.MovieBuilder setPoster(String poster) {
            newMovie.setPoster(poster);
            return this;
        }


        public Movie.MovieBuilder setAbout(String about) {
            newMovie.setAbout(about);
            return this;
        }


        public Movie.MovieBuilder setReleaseTime(Timestamp timestamp) {
            newMovie.setReleaseDate(timestamp);
            return this;
        }

        public Movie.MovieBuilder setAmountLike(Integer like) {
            newMovie.setAmount_like(like);
            return this;
        }


        public Movie.MovieBuilder setAmountDislike(Integer dislike) {
            newMovie.setAmount_dislike(dislike);
            return this;
        }

        public Movie.MovieBuilder setMovieGenre(Genre genre) {
            newMovie.setGenre(genre);
            return this;
        }


        public Movie build() {
            return newMovie;
        }


    }

}
