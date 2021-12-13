package com.epam.ratingmovies.dao.entity;

import java.sql.Timestamp;
import java.util.Date;

public class User extends AbstractEntity<Long> {

    private Timestamp createTime;
    private String name;
    private String login;
    private String password;
    private UserRole userRole;
    private String profilePicture;
    private String email;
    private String telegramAccount;
    private UserStatus userStatus;

     public User(String login, String password) {
        this.login = login;
        this.password = password;
    }


    public User(String name, String login, String password,
                UserRole userRole, String profilePicture, String email,
                String telegramAccount, UserStatus userStatus, Timestamp createTime) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.userRole = userRole;
        this.profilePicture = profilePicture;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
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

    public static UserBuilder builder() {
        return new UserBuilder();
    }


    public static class UserBuilder {
        private final User newUser;

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

        public UserBuilder setProfilePicture(String picture) {
            newUser.setProfilePicture(picture);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getCreateTime() != null ? !getCreateTime().equals(user.getCreateTime()) : user.getCreateTime() != null)
            return false;
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getUserRole() != user.getUserRole()) return false;
        if (getProfilePicture() != null ? !getProfilePicture().equals(user.getProfilePicture()) : user.getProfilePicture() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        if (getTelegramAccount() != null ? !getTelegramAccount().equals(user.getTelegramAccount()) : user.getTelegramAccount() != null)
            return false;
        return getUserStatus() == user.getUserStatus();
    }

    @Override
    public int hashCode() {
        int result = getCreateTime() != null ? getCreateTime().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getUserRole() != null ? getUserRole().hashCode() : 0);
        result = 31 * result + (getProfilePicture() != null ? getProfilePicture().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getTelegramAccount() != null ? getTelegramAccount().hashCode() : 0);
        result = 31 * result + (getUserStatus() != null ? getUserStatus().hashCode() : 0);
        return result;
    }



}
    
