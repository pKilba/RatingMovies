package com.epam.ratingmovies.dao.impl;

import com.epam.ratingmovies.dao.api.MovieDao;
import com.epam.ratingmovies.dao.connectionpool.api.ConnectionPool;
import com.epam.ratingmovies.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.exception.DaoException;
import com.epam.ratingmovies.dao.mapper.api.RowMapper;
import com.epam.ratingmovies.dao.mapper.impl.MovieRowMapper;
import com.epam.ratingmovies.dao.mapper.impl.UserRowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDaoImpl implements MovieDao {

    private final RowMapper<Movie> mapper = new MovieRowMapper();

    private static final String SQL_SAVE_MOVIE = "INSERT INTO movies(poster,about," +
            "movie_release_date,amount_like,amount_dislike,genre_id,name,producer,duration,background)" +
            " values (?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_FIND_ALL_MOVIES = "SELECT movie_id, poster, about," +
            "movie_release_date,amount_like,amount_dislike," +
            "genre_id,name,producer,duration,background FROM movies";

    private static final String SQL_FIND_MOVIE_BY_ID =
            "SELECT movie_id, poster, about," +
                    "movie_release_date,amount_like,amount_dislike," +
                    "genre_id , name,producer,duration,background FROM movies WHERE movie_id = ?";


    private final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
    private long idMovie;

    @Override
    public Movie save(Movie movie) {
        Connection connection = connectionPool.takeConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_MOVIE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, movie.getPoster());
            preparedStatement.setString(2, movie.getAbout());
            preparedStatement.setTimestamp(3, movie.getReleaseDate());
            preparedStatement.setInt(4, movie.getAmount_like());
            preparedStatement.setInt(5, movie.getAmount_dislike());
            preparedStatement.setInt(6, movie.getGenre().getId());
            preparedStatement.setString(7, movie.getName());
            preparedStatement.setString(8, movie.getProducer());
            preparedStatement.setInt(9, movie.getDuration());
            preparedStatement.setString(10, movie.getBackground());

            int execute = preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    movie.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
                connectionPool.returnConnection(connection);
            }

            System.out.println(execute);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("что то совсем не так");
        }
        return movie;
    }

    @Override
    public Movie update(Movie movie) {
        return null;
    }

    @Override
    public void delete(Movie movie) {

    }

    @Override
    public void delete(Long id) {

    }


    @Override
    public List<Movie> findAll() {

        Connection connection = connectionPool.takeConnection();
        List<Movie> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_MOVIES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Movie movie = mapper.map(resultSet);
                result.add(movie);
            }
            preparedStatement.close();
connectionPool.returnConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<Movie> findById(Long idEntity) {
        return Optional.empty();
    }


    //todo изменить логику крч я сравниваю по айди а например в моей бд
    //были удалены юзеры с некоторыми айди поэтому не ровно выводит
    //todo!!!! obezatelno
    public List<Movie> findMoviesRange(int offset, int amount) throws DaoException {
        Connection connection = connectionPool.takeConnection();
        List<Movie> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_MOVIES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Movie movie = mapper.map(resultSet);
                if (offset <= movie.getId() && movie.getId() <= offset + amount) {
                    result.add(movie);
                }
            }
            preparedStatement.close();
            connectionPool.returnConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int findMoviesAmount() throws DaoException {
        Connection connection = connectionPool.takeConnection();
        int counter = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_MOVIES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                counter++;
            }
            preparedStatement.close();

            connectionPool.returnConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counter;
    }


    @Override
    public Optional<Movie> findMovieById(long idMovie) {
        this.idMovie = idMovie;
        ResultSet resultSet = null;
        Optional<Movie> movieOptional = null;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_MOVIE_BY_ID)) {
            statement.setLong(1, idMovie);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                movieOptional = Optional.of(mapper.map(resultSet));
            }

            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return movieOptional;
    }


    @Override
    public List<Movie> findMoviesByGenre(String genre) throws DaoException {
        return null;
    }
}
