package com.epam.ratingmovies.dao.entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.epam.ratingmovies.controller.command.CommandName.*;



public enum UserRole {
    USER(0),
    ADMIN(1);

    private int id;

    UserRole(int id){
        this.id = id;
    }



    public int getId() {
        return id;
    }

    public static UserRole getById(int id){
        return Arrays.stream(UserRole.values())
                .filter(role ->role.getId() == id)
                .findFirst().orElse(null);
    }
}
