package com.epam.ratingmovies.dao.entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.epam.ratingmovies.controller.command.CommandName.*;


import static com.epam.ratingmovies.controller.command.CommandName.*;

public enum UserRole {
    USER(),
    ADMIN(ACCOUNT_SETTINGS_PAGE),
    GUEST();


    private final Set<String> commandsName = new HashSet<>();


    UserRole(String... commandsName) {
        this.commandsName.addAll(Set.of(commandsName));
    }

    public boolean isExistCommandName(String command) {
        return this.commandsName.contains(command);
    }

//    private int id;
//
//    UserRole(int id){
//        this.id = id;
//    }
//
//
//
//    public int getId() {
//        return id;
//    }
//
//    public static UserRole getById(int id){
//        return Arrays.stream(UserRole.values())
//                .filter(role ->role.getId() == id)
//                .findFirst().orElse(null);
//    }
}
