package com.epam.ratingmovies.dao.entity;

import java.sql.Timestamp;
import java.util.Date;

public class User extends AbstractEntity<Long> {

    private  Timestamp createTime;
    private String name;
    private String login;
    private String password;
    private UserRole userRole;
    private Integer profilePictureId;
    private String email;
    private String telegramAccount;
    private UserStatus userStatus;

    //todo add yourself and change tip profile picture mb na string


    public User(String name, String login, String password,
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

    public User() {
    }


    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getDate() {
        return createTime;
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

    public static UserBuilder builder(){
        return new UserBuilder();
    }


    public static class UserBuilder {
        private User newUser;

        UserBuilder() {
            newUser = new User();
        }

        public UserBuilder setUserId(long userId) {
            newUser.setId(userId);
            return this;
        }

        public UserBuilder setLogin(String login) {
            newUser.setLogin(login);
            return this;
        }

        public UserBuilder setPassword(String password) {
            newUser.setPassword(password);
            return this;
        }


        public UserBuilder setName(String name) {
            newUser.setName(name);
            return this;
        }

        public UserBuilder setEmail(String email) {
            newUser.setEmail(email);
            return this;
        }


        public UserBuilder setUserRole(UserRole userRole) {
            newUser.setUserRole(userRole);
            return this;
        }

        public UserBuilder setTelegram(String telegram) {
            newUser.setTelegramAccount(telegram);
            return this;
        }
        public UserBuilder setCreateTime(Timestamp timestamp) {
            newUser.setCreateTime(timestamp);
            return this;
        }

        public UserBuilder setProfilePicture(Integer pictureId){
            newUser.setProfilePictureId(pictureId);
            return this;
        }

        public UserBuilder setUserStatus(UserStatus userStatus) {
            newUser.setUserStatus(userStatus);
            return this;
        }


        public User build() {
            return newUser;
        }
    }


}
    
