package com.epam.ratingmovies.dao.entity;

public final class ColumnName {
    private ColumnName(){}
    //Table "users"
    public static final String USER_ID = "user_id";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE = "role_id";
    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "mail";
    public static final String USER_TELEGRAM = "account_telegram";
    public static final String USER_STATUS = "status_id";
    public static final String USER_CREATE_TIME = "create_time";
    public static final String USER_PROFILE_PICTURE = "profile_picture_id";

    //Table movies
    public static final String MOVIE_ID = "movie_id";
    public static final String MOVIE_POSTER = "poster";
    public static final String MOVIE_ABOUT = "about";
    public static final String MOVIE_RELEASE_DATE = "movie_release_date";
    public static final String MOVIE_AMOUNT_LIKE = "amount_like";
    public static final String MOVIE_AMOUNT_DISLIKE = "amount_dislike";
    public static final String MOVIE_GENRE_ID = "genre_id";

}
