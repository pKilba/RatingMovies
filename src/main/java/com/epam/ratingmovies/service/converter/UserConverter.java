package com.epam.ratingmovies.service.converter;

import com.epam.ratingmovies.dao.entity.User;
import com.epam.ratingmovies.dao.entity.UserRole;
import com.epam.ratingmovies.service.dto.UserDto;

public class UserConverter implements Converter<UserDto, User>{

    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setUserRole(UserRole.getById(user.getUserRole().getId()));
        return userDto;
    }

    @Override
    public User convert(UserDto userDto) {
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        return user;
    }

}
