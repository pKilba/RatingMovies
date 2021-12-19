SELECT movie_id, poster, about," +
                    " movie_release_date, amount_like, amount_dislike," +
                    "genre_id," +
                    " name, producer, duration, " +
                    "background FROM movies ORDER BY " +
                    "movie_release_date DESCLIMIT ?,?"