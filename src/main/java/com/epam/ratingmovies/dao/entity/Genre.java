package com.epam.ratingmovies.dao.entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Genre {
    THRILLER(0),
    COMEDY(1),
    ADVENTURE(2),
    DRAMA(3),
    CRIME(4),
    HORROR(5),
    FANTASY(6);


    private int id;

    Genre(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public static Genre getById(int id) {
        return Arrays.stream(Genre.values())
                .filter(genre -> genre.getId() == id)
                .findFirst().orElse(null);
    }
}
