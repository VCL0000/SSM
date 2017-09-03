package com.vcl0000.data.model;

import org.springframework.stereotype.Repository;

/**
 * Created by vcl0000 on 17-7-26.
 */
@Repository
public class UserAndInfo {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private User user;
    private UserInfo userInfo;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "UserAndInfo{" +
                "user=" + user +
                ", userInfo=" + userInfo +
                '}';
    }
}
