package com.epam.ratingmovies.dao.entity;

import java.util.Arrays;

public enum UserStatus {
    Active(1),
    Asleep(2),
    Freeze(3);
    private int id;

    UserStatus(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static UserStatus getById(int id){
        return Arrays.stream(UserStatus.values())
                .filter(status ->status.getId() == id)
                .findFirst().orElse(null);
    }
}
