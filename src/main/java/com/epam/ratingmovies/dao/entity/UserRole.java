package com.epam.ratingmovies.dao.entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.epam.ratingmovies.controller.command.CommandName.*;

import static com.epam.ratingmovies.controller.command.CommandName.HOME_PAGE;

public enum UserRole {
    USER(0,HOME_PAGE),
    ADMIN(1,HOME_PAGE);

    private int id;
    private final Set<String> commandsName = new HashSet<>();

    UserRole(int id,String ... commandsName){
        this.id = id;
    }

    public Set<String> getRole() {
        return commandsName;
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
