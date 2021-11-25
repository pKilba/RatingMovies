package com.epam.ratingmovies.service.dto;

import com.epam.ratingmovies.dao.entity.UserRole;
import com.epam.ratingmovies.dao.entity.UserStatus;

import java.sql.Timestamp;

public class UserDto implements Dto{

    //todo сделать билдер



    private Timestamp createTime;
    private String name;
    private String login;
    private String password;
    private UserRole userRole;
    private Integer profilePictureId;
    private String email;
    private String telegramAccount;
    private UserStatus userStatus;

    public UserDto(){}

    public UserDto(String name, String login, String password,
                UserRole userRole, Integer profilePictureId, String email,
                String telegramAccount, UserStatus userStatus, Timestamp createTime) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.userRole = userRole;
        this.profilePictureId = profilePictureId;
        this.email = email;
        this.telegramAccount = telegramAccount;
        this.userStatus = userStatus;
        this.createTime = createTime;
    }


    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Integer getProfilePictureId() {
        return profilePictureId;
    }

    public void setProfilePictureId(Integer profilePictureId) {
        this.profilePictureId = profilePictureId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelegramAccount() {
        return telegramAccount;
    }

    public void setTelegramAccount(String telegramAccount) {
        this.telegramAccount = telegramAccount;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
