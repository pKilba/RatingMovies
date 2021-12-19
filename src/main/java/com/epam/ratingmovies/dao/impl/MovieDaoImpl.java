package com.epam.ratingmovies.dao.impl;

import com.epam.ratingmovies.dao.api.MovieDao;
import com.epam.ratingmovies.dao.connectionpool.api.ConnectionPool;
import com.epam.ratingmovies.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.ratingmovies.dao.entity.Movie;
import com.epam.ratingmovies.dao.mapper.api.RowMapper;
import com.epam.ratingmovies.dao.mapper.impl.MovieRowMapper;
import com.epam.ratingmovies.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDaoImpl implements MovieDao {

    private final RowMapper<Movie> mapper = new MovieRowMapper();

    private static final String SQL_SAVE_MOVIE = "INSERT INTO movies(poster,about," +
            "movie_release_date,amount_like,amount_dislike,genre_id,name,producer,duration,background)" +
            " values (?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_FIND_ALL_MOVIES = "SELECT movie_id, poster, about,movie_release_date,amount_like,amount_dislike, genre_id,name,producer,duration,background FROM movies";

    private static final String SQL_FIND_MOVIE_BY_ID =
            "SELECT movie_id, poster, about," +
                    "movie_release_date,amount_like,amount_dislike," +
                    "genre_id , name,producer,duration,background FROM movies WHERE movie_id = ?";


    private static final String SQL_FIND_MOVIES_RANGE =
            "SELECT movie_id, poster, about," +
                    " movie_release_date, amount_like, amount_dislike," +
                    "genre_id," +
                    " name, producer, duration, " +
                    "background FROM movies ORDER BY " +
                    "movie_release_date DESC LIMIT ?,?";


    private final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();


    private static MovieDaoImpl instance;

    private MovieDaoImpl() {
    }

    public static MovieDaoImpl getInstance() {
        if (instance == null) {
            instance = new MovieDaoImpl();
        }
        return instance;
    }


    @Override
    public Movie save(Movie movie) throws DaoException {
        Connection connection = connectionPool.takeConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_MOVIE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, movie.getPoster());
            preparedStatement.setString(2, movie.getAbout());
            preparedStatement.setTimestamp(3, movie.getReleaseDate());
            preparedStatement.setInt(4, movie.getamountLike());
            preparedStatement.setInt(5, movie.getamountDislike());
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
                    throw new DaoException("Creating user failed, no ID obtained.");
                }
                preparedStatement.close();
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.returnConnection(connection);

        }
        return movie;
    }

    @Override
    public List<Movie> findAll() throws DaoException {

        Connection connection = connectionPool.takeConnection();
        List<Movie> result = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_MOVIES);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            preparedStatement.close();
            while (resultSet.next()) {
                Movie movie = mapper.map(resultSet);
                result.add(movie);

            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {

            connectionPool.returnConnection(connection);
        }
        return result;
    }


    @Override
    public List<Movie> findMoviesRange(int offset, int amount) throws DaoException {
        Connection connection = connectionPool.takeConnection();
        List<Movie> result = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_MOVIES_RANGE)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, amount);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Movie movie = mapper.map(resultSet);
                result.add(movie);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return result;
    }

    @Override
    public int findMoviesAmount() throws DaoException {
        Connection connection = connectionPool.takeConnection();
        int counter = 0;
        try (

                PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_MOVIES);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                counter++;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.returnConnection(connection);

        }
        return counter;
    }


    @Override
    public Optional<Movie> findById(Long idMovie) throws DaoException {
        ResultSet resultSet = null;
        Optional<Movie> movieOptional = Optional.empty();
        Connection connection = connectionPool.takeConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_MOVIE_BY_ID)) {
            statement.setLong(1, idMovie);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                movieOptional = Optional.of(mapper.map(resultSet));
            }

            connectionPool.returnConnection(connection);

        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.returnConnection(connection);

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }

        }
        return movieOptional;
    }


}
