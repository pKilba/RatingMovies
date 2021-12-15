package com.epam.ratingmovies.dao.entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.epam.ratingmovies.controller.command.CommandName.*;


import static com.epam.ratingmovies.controller.command.CommandName.*;

public enum UserRole {
    USER(MOVIES_PAGE,SIGN_UP_PAGE,
            REVIEWS_PAGE,MOVIE_PAGE,LOCALIZATION,ACCOUNT_SETTINGS_PAGE,PROFILE_PAGE,
            CHANGE_GENERAL_INFO,LOGOUT,LEAVE_COMMENT,CHANGE_PASSWORD),

    ADMIN(SIGN_UP_PAGE,ACCOUNT_SETTINGS_PAGE,
            MOVIES_PAGE,PROFILE_PAGE,CHANGE_GENERAL_INFO,LOGOUT,REVIEWS_PAGE,
            MOVIE_PAGE,LEAVE_COMMENT,CREATE_MOVIE_PAGE,
            USERS_PAGE,CHANGE_PASSWORD,ACTION_BAN_USER,ACTION_UNBAN_USER,
            LOCALIZATION,CREATE_MOVIE),

    GUEST(MOVIES_PAGE,SIGN_UP_PAGE,LOGIN_PAGE,
            REVIEWS_PAGE,MOVIE_PAGE,LOGIN,SIGN_UP,LOCALIZATION);


    private final Set<String> commandsName = new HashSet<>();


    UserRole(String... commandsName) {
        this.commandsName.addAll(Set.of(commandsName));
    }

    public boolean isExistCommandName(String command) {
        return this.commandsName.contains(command);
    }

}
